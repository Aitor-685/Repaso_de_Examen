¡Entendido! A continuación te muestro cómo podría quedar el archivo `README.md` con ejemplos de código para cada función:

---

# Proyecto del Castillo

Este proyecto simula un sistema de alojamiento de personajes en un castillo. El castillo tiene varias plantas, y los personajes pueden alojarse en habitaciones disponibles de acuerdo con ciertos criterios. El objetivo es gestionar las habitaciones, mostrar su estado y permitir que los personajes se alojen en el castillo.

## Descripción de las Funciones

### 1. `main()`

La función principal del programa. Es responsable de iniciar el programa y gestionar el ciclo de interacción con el usuario. Llama a otras funciones según la opción que elija el usuario desde el menú.

```kotlin
fun main() {
    while (true) {
        mostraMenu()  // Muestra el menú de opciones
        when (triaOpcioMenu()) {  // Captura la opción seleccionada por el usuario
            1 -> alojarUnPersonaje()  // Aloja un personaje
            2 -> visualizarEstadoHabitaciones()  // Muestra el estado de las habitaciones
            3 -> mostrarPersonajesAlojados()  // Muestra los personajes alojados
            4 -> {
                println("Sortint de l'aplicació. Adéu!")  // Finaliza el programa
                break
            }
            else -> println("Opció no vàlida, torna-ho a intentar.")  // En caso de opción inválida
        }
    }
}
```

### 2. `mostraMenu()`

Esta función muestra el menú de opciones del castillo. Es utilizada dentro de la función `main()` para que el usuario elija qué acción realizar.

```kotlin
fun mostraMenu() {
    println("-----------------------------------------------------------")
    println(" __  __            _         _                   ")
    println(" |  \\/  | __ _ _ __(_) ___   | |__  _ __ ___  ___ ")
    println(" | |\\/| |/ _` | '__| |/ _ \\  | '_ \\| '__/ _ \\/ __|")
    println(" | |  | | (_| | |  | | (_) | | |_) | | | (_) \\__ \\")
    println(" |_|  |_|\\__,_|_|  |_|\\___/  |_.__/|_|  \\___/|___/")
    println("-----------------------------------------------------------")
    println(BLUE_BOLD_BRIGHT + "Menú del Castillo:" + RESET)
    println(YELLOW_BOLD_BRIGHT + "1. Quieres alojarte en el castillo" + RESET)
    println(YELLOW_BOLD_BRIGHT + "2. Visualizar el estado de las habitaciones" + RESET)
    println(YELLOW_BOLD_BRIGHT + "3. Quienes las ocupan" + RESET)
    println(YELLOW_BOLD_BRIGHT + "4. Salir" + RESET)
}
```

### 3. `triaOpcioMenu()`

Esta función se encarga de capturar la opción que el usuario elige del menú.

```kotlin
fun triaOpcioMenu(): Int {
    print(RED_BOLD_BRIGHT + "Tria una opció del menú: " + RESET)
    return readlnOrNull()?.toIntOrNull() ?: 0  // Si la entrada no es válida, retorna 0
}
```

### 4. `visualizarEstadoHabitaciones()`

Esta función muestra el estado actual de las habitaciones del castillo. Para cada planta, muestra cuántas habitaciones están disponibles y cuántas están ocupadas.

```kotlin
fun visualizarEstadoHabitaciones() {
    println("Estado actual del castillo:")
    habitacionesDelCastillo.forEach { planta ->
        val ocupadas = habitacionesOcupadas.values.count { it.pisos == planta.pisos }
        val disponibles = planta.habitaciones - ocupadas
        println("Planta: ${planta.pisos} | Estrellas: ${planta.estrellas} | Disponibles: $disponibles | Ocupadas: $ocupadas")
    }
}
```

#### Ejemplo de salida:
```
Estado actual del castillo:
Planta: Jardín | Estrellas: 1 | Disponibles: 1 | Ocupadas: 0
Planta: Sótano | Estrellas: 2 | Disponibles: 4 | Ocupadas: 1
Planta: Primera planta | Estrellas: 4 | Disponibles: 4 | Ocupadas: 3
...
```

### 5. `mostrarPersonajesAlojados()`

Esta función muestra qué personajes están actualmente alojados en el castillo y en qué planta se encuentran.

```kotlin
fun mostrarPersonajesAlojados() {
    if (habitacionesOcupadas.isEmpty()) {
        println("No hay personajes alojados en el castillo.")
    } else {
        println("Personajes alojados:")
        habitacionesOcupadas.forEach { (personaje, habitacion) ->
            println("  - $personaje en ${habitacion.pisos} (${habitacion.estrellas}★)")
        }
    }
}
```

#### Ejemplo de salida:
```
Personajes alojados:
  - Mario en Primera planta (4★)
  - Wario en Tercera planta (5★)
```

### 6. `alojarUnPersonaje()`

Esta función permite alojar a un personaje en una de las habitaciones disponibles del castillo.

```kotlin
fun alojarUnPersonaje() {
    if (personajes.isEmpty()) {
        println("No quedan personajes pendientes de alojamiento.")
        return
    }

    println("Personajes pendientes: ${personajes.joinToString(", ")}")
    println("Introduce el nombre del personaje que quieres alojar:")
    val nombre = readlnOrNull()?.trim()

    if (nombre == null || !personajes.contains(nombre)) {
        println("Personaje no válido o no está pendiente de alojamiento.")
        return
    }

    val random = kotlin.random.Random
    var contadorEstelar: Int = 0
    for (x in 1..5) {
        val num0 = (1..2).random()
        val num1 = (0..500).random()
        val num2 = (0..500).random()
        if (num0 == 1) {
            var resultSum = readInt("dime cuanto es $num1 + $num2", "Solo Introduce números enteros.")
            if (num1 + num2 == resultSum) {
                contadorEstelar++
                println("Correcta")
            } else
                println("Incorrecta, el número correcto era: ${num1 + num2}")
        } else {
            var resultRest = readInt("dime cuanto es $num1 - $num2", "Solo Introduce números enteros.")
            if (num1 - num2 == resultRest) {
                contadorEstelar++
                println("Correcta")
            } else
                println("Incorrecta, el número correcto era: ${num1 - num2}")
        }
    }

    val habitacionDisponible = habitacionesDelCastillo
        .filter { planta -> planta.habitaciones > habitacionesOcupadas.values.count { it.pisos == planta.pisos }
                && planta.estrellas <= contadorEstelar}
        .maxByOrNull { it.estrellas }

    if (habitacionDisponible != null) {
        habitacionesOcupadas[nombre] = habitacionDisponible
        personajes.remove(nombre)
        println("$nombre ha sido alojado en la planta ${habitacionDisponible.pisos} (${habitacionDisponible.estrellas}★).")
    } else {
        println("No quedan habitaciones disponibles. $nombre puede acampar en el jardín.")
        personajes.remove(nombre)
    }
}
```

#### Ejemplo de salida:
```
Personajes pendientes: Mario, Luigi, Peach
Introduce el nombre del personaje que quieres alojar:
Mario
dime cuanto es 231 + 345: 576
Correcta
dime cuanto es 121 - 45: 76
Correcta
Mario ha sido alojado en la planta Primera planta (4★).
```

### 7. `readInt(prompt: String, errorMessage: String): Int`

Esta función personalizada se utiliza para leer un número entero de la entrada del usuario, mostrando un mensaje de solicitud (prompt) y validando que la entrada sea un número.

```kotlin
fun readInt(prompt: String, errorMessage: String): Int {
    var result: Int?
    do {
        println(prompt)
        val input = readlnOrNull()
        result = input?.toIntOrNull()
        if (result == null) {
            println(errorMessage)
        }
    } while (result == null)
    return result
}
```

#### Ejemplo de salida:
```
dime cuanto es 231 + 345: abc
Solo Introduce números enteros.
dime cuanto es 231 + 345: 576
Correcta
```

## Estructura de Datos

### `Castillo`

La clase `Castillo` representa una planta o área del castillo. Cada planta tiene:

- `pisos`: El nombre o descripción de la planta (por ejemplo, "Jardín", "Primera planta").
- `estrellas`: El número de estrellas de la planta, que indica la calidad de las habitaciones.
- `habitaciones`: El número total de habitaciones en la planta.

```kotlin
data class Castillo(
    val pisos: String,
    val estrellas: Int,
    val habitaciones: Int
)
```

### `habitacionesDelCastillo`

Una lista mutable (`mutableListOf`) que contiene los datos de las habitaciones del castillo, representadas por instancias de la clase `Cast

illo`.

### `habitacionesOcupadas`

Un mapa mutable (`mutableMapOf`) que mantiene un registro de las habitaciones ocupadas y por qué personajes.
