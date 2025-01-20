### 1. **Uso de funciones básicas y estructuras de control**
   
   **Concepto:** Las funciones básicas permiten estructurar tu programa en bloques más pequeños y manejables. `when` es una forma de realizar un "switch" o "case" en Kotlin. Es útil para manejar múltiples opciones.

   **Ejemplo:**
   ```kotlin
   fun muestraMenu() {
       println("1. Sumar")
       println("2. Restar")
       println("3. Multiplicar")
       println("4. Dividir")
   }

   fun triaOpcioMenu(): Int {
       println("Selecciona una opción:")
       return readlnOrNull()?.toIntOrNull() ?: 0
   }

   fun main() {
       while (true) {
           muestraMenu()
           when (triaOpcioMenu()) {
               1 -> println("Has seleccionado la opción de sumar.")
               2 -> println("Has seleccionado la opción de restar.")
               3 -> println("Has seleccionado la opción de multiplicar.")
               4 -> println("Has seleccionado la opción de dividir.")
               else -> println("Opción no válida.")
           }
       }
   }
   ```
   **Explicación:** Este código muestra un menú y espera la elección del usuario. Dependiendo de la opción seleccionada, ejecuta una acción específica usando `when`. Si se elige una opción no válida, se muestra un mensaje de error.

---

### 2. **Trabajar con listas mutables (`MutableList`)**
   
   **Concepto:** Una `MutableList` permite modificar la lista después de que se haya creado. Puedes agregar, eliminar o modificar elementos dentro de ella.

   **Ejemplo:**
   ```kotlin
   val personajes = mutableListOf("Mario", "Luigi", "Yoshi")

   // Agregar un nuevo personaje
   personajes.add("Princess Peach")

   // Eliminar un personaje
   personajes.remove("Luigi")

   // Modificar un personaje
   personajes[0] = "Toad"

   // Mostrar los personajes actuales
   println(personajes) // Output: [Toad, Yoshi, Princess Peach]
   ```

   **Explicación:** Aquí se crea una `MutableList` de personajes. Agregamos un personaje con `add()`, eliminamos con `remove()` y modificamos un personaje usando el índice de la lista. Luego, imprimimos el contenido final de la lista.

---

### 3. **Trabajar con mapas (`MutableMap`)**
   
   **Concepto:** Un `MutableMap` es una colección de pares clave-valor. Puedes añadir, eliminar y modificar valores asociados a una clave específica.

   **Ejemplo:**
   ```kotlin
   val habitacionesOcupadas = mutableMapOf<String, String>()
   
   // Asignar habitaciones a personajes
   habitacionesOcupadas["Mario"] = "Primera Planta"
   habitacionesOcupadas["Luigi"] = "Segunda Planta"

   // Acceder a las habitaciones ocupadas
   println(habitacionesOcupadas["Mario"]) // Output: Primera Planta

   // Eliminar un personaje de las habitaciones
   habitacionesOcupadas.remove("Luigi")

   // Mostrar el mapa actualizado
   println(habitacionesOcupadas) // Output: {Mario=Primera Planta}
   ```

   **Explicación:** Usamos un `MutableMap` para asignar personajes a habitaciones. Se agregan y eliminan pares clave-valor, y se puede acceder a los valores con su clave.

---

### 4. **Uso de `data class`**
   
   **Concepto:** Un `data class` es una clase diseñada para almacenar datos, donde Kotlin genera automáticamente funciones como `toString()`, `equals()`, y `hashCode()` para que no tengas que escribirlas manualmente.

   **Ejemplo:**
   ```kotlin
   data class Castillo(val nombre: String, val estrellas: Int)

   val castillo1 = Castillo("Castillo Azul", 5)
   val castillo2 = Castillo("Castillo Rojo", 3)

   // Mostrar el contenido del data class
   println(castillo1) // Output: Castillo(nombre=Castillo Azul, estrellas=5)
   ```

   **Explicación:** Usamos `data class` para representar un castillo con nombre y estrellas. Las instancias de esta clase se muestran de manera clara usando `println()` gracias al método `toString()` generado automáticamente.

---

### 5. **Condiciones y Bucles**
   
   **Concepto:** Los bucles y las condiciones permiten iterar sobre colecciones y tomar decisiones basadas en ciertas condiciones.

   **Ejemplo:**
   ```kotlin
   val habitacionesDelCastillo = listOf("Jardín", "Sótano", "Primera Planta", "Segunda Planta")
   val habitacionesOcupadas = mutableListOf("Sótano", "Primera Planta")

   for (habitacion in habitacionesDelCastillo) {
       if (habitacion in habitacionesOcupadas) {
           println("$habitacion está ocupada.")
       } else {
           println("$habitacion está disponible.")
       }
   }
   ```

   **Explicación:** Usamos un bucle `for` para recorrer las habitaciones. Dentro del bucle, se verifica si la habitación está ocupada mediante `if` y el operador `in`. Esto es útil para mostrar el estado de cada habitación.

---

### 6. **Recursividad**

   **Concepto:** La recursividad es cuando una función se llama a sí misma. Es útil en problemas que pueden dividirse en subproblemas similares.

   **Ejemplo:**
   ```kotlin
   fun factorial(n: Int): Int {
       return if (n == 0) 1 else n * factorial(n - 1)
   }

   println(factorial(5)) // Output: 120
   ```

   **Explicación:** La función `factorial` se llama a sí misma hasta llegar al caso base `n == 0`. Esto es un ejemplo clásico de recursividad, donde el problema original se reduce a subproblemas más simples.

---

### 7. **Funciones con parámetros y retorno**
   
   **Concepto:** Las funciones pueden aceptar parámetros y devolver un valor. Esto permite modularizar tu código.

   **Ejemplo:**
   ```kotlin
   fun suma(a: Int, b: Int): Int {
       return a + b
   }

   println(suma(5, 7)) // Output: 12
   ```

   **Explicación:** La función `suma` recibe dos parámetros `a` y `b`, y devuelve su suma. Este tipo de función es muy útil para realizar cálculos dentro del programa.

---

### 8. **Uso de randomización**
   
   **Concepto:** Puedes usar la clase `Random` para generar números aleatorios, lo que es útil para simulaciones, juegos, y más.

   **Ejemplo:**
   ```kotlin
   val random = kotlin.random.Random
   val numeroAleatorio = random.nextInt(1, 10)
   println("Número aleatorio entre 1 y 10: $numeroAleatorio")
   ```

   **Explicación:** En este ejemplo, se genera un número aleatorio entre 1 y 10 utilizando `Random.nextInt()`. Esto es común en programas de juegos o simulaciones donde necesitas variabilidad.

---

### 9. **Entrada y Validación de Datos**

   **Concepto:** La validación de datos es crucial para evitar errores o entradas incorrectas del usuario. Puedes usar `readlnOrNull()` y verificar si el dato es válido.

   **Ejemplo:**
   ```kotlin
   fun leerNumero(): Int {
       println("Introduce un número:")
       return readlnOrNull()?.toIntOrNull() ?: 0
   }

   println("Número ingresado: ${leerNumero()}")
   ```

   **Explicación:** La función `leerNumero` pide al usuario que ingrese un número. Si la entrada no es válida, se devuelve `0` por defecto.

---

### 10. **Manejo de excepciones y validación de datos**

   **Concepto:** El manejo de excepciones te permite capturar errores y continuar ejecutando el programa sin que se detenga abruptamente.

   **Ejemplo:**
   ```kotlin
   fun dividir(a: Int, b: Int): Int {
       return try {
           a / b
       } catch (e: ArithmeticException) {
           println("No se puede dividir entre cero.")
           0
       }
   }

   println(dividir(10, 0)) // Output: No se puede dividir entre cero. 0
   ```

   **Explicación:** En este ejemplo, se maneja una excepción `ArithmeticException` que ocurre cuando intentas dividir entre cero. En lugar de que el programa se detenga, se maneja el error y se devuelve `0`.

---

### 11. **Algoritmos de Búsqueda y Filtrado**

   **Concepto:** El filtrado y la búsqueda de elementos en colecciones son tareas comunes. Puedes usar métodos como `filter` y `maxByOrNull`.

   **Ejemplo:**
   ```kotlin
   val habitacionesDelCastillo = listOf(
       Castillo("Jardín", 1),
       Castillo("Primera Planta", 5),
       Castillo("

Sótano", 3)
   )

   val habitacionesDisponibles = habitacionesDelCastillo.filter { it.estrellas >= 3 }
   println(habitacionesDisponibles) // Output: [Castillo(nombre=Primera Planta, estrellas=5), Castillo(nombre=Sótano, estrellas=3)]
   ```

   **Explicación:** Se utiliza `filter` para filtrar las habitaciones con 3 estrellas o más. El resultado es una lista de castillos que cumplen con la condición.

### **Ejercicio 1: Completar una función para alojar un personaje en una habitación**
Te proporcionan una función incompleta que debe alojar a un personaje en una habitación del castillo, pero la lógica de asignación de la habitación no está completa. Debes completarla.

#### Código incompleto:
```kotlin
fun alojarUnPersonaje(personaje: String) {
    // Debes alojar un personaje en una habitación disponible
    val habitacionDisponible = habitaciones.filter { it.habitaciones > habitacionesOcupadas.count() }
    // Lógica faltante: asignar la habitación al personaje
}
```

#### Tarea:
- Completa la función para que asigne correctamente a un personaje una habitación disponible. Si no hay habitaciones disponibles, debes indicar que el personaje debe acampar en el jardín.

**Solución esperada:**
```kotlin
fun alojarUnPersonaje(personaje: String) {
    val habitacionDisponible = habitacionesDelCastillo
        .filter { it.habitaciones > habitacionesOcupadas.values.count { it.pisos == it.pisos } }
        .maxByOrNull { it.estrellas }

    if (habitacionDisponible != null) {
        habitacionesOcupadas[personaje] = habitacionDisponible
        println("$personaje ha sido alojado en ${habitacionDisponible.pisos} (${habitacionDisponible.estrellas}★).")
    } else {
        println("No hay habitaciones disponibles. $personaje puede acampar en el jardín.")
    }
}
```

---

### **Ejercicio 2: Crear una función recursiva para calcular el factorial**
Crea una función recursiva que calcule el factorial de un número. Recuerda que el factorial de un número \( n \) es \( n! = n \times (n - 1) \times ... \times 1 \), y se define recursivamente como:

\[
n! = n \times (n - 1)!
\]

#### Especificaciones:
- La función debe ser recursiva.
- Si el número es 0 o 1, el factorial es 1.

**Solución esperada:**
```kotlin
fun factorialRecursivo(n: Int): Int {
    return if (n <= 1) 1 else n * factorialRecursivo(n - 1)
}
```

---

### **Ejercicio 3: Función para calcular la suma de elementos en una lista de enteros**
Te dan una lista de enteros, y debes crear una función que calcule la suma de todos los elementos. Usa una función estándar de Kotlin para realizar esta operación. 

#### Especificaciones:
- La función debe aceptar una lista de enteros.
- Debe devolver la suma de los elementos de la lista.

**Solución esperada:**
```kotlin
fun sumaElementos(lista: List<Int>): Int {
    return lista.sum()
}
```

---

### **Ejercicio 4: Crear una función para ordenar las habitaciones de un castillo por estrellas**
Crea una función que reciba la lista de habitaciones y devuelva las habitaciones ordenadas por el número de estrellas en orden descendente. Usa el concepto de `data class` que ya has usado en el código.

#### Especificaciones:
- La función debe recibir una lista de habitaciones (`List<Castillo>`).
- Debe devolver la lista ordenada por el número de estrellas de mayor a menor.

**Solución esperada:**
```kotlin
fun ordenarHabitacionesPorEstrellas(habitaciones: List<Castillo>): List<Castillo> {
    return habitaciones.sortedByDescending { it.estrellas }
}
```

---

### **Ejercicio 5: Completar una función que verifique si un personaje ya está alojado en el castillo**
Te proporcionan una función incompleta que verifica si un personaje ya está alojado en el castillo. Debes completar la lógica para buscar al personaje en el mapa de habitaciones ocupadas.

#### Código incompleto:
```kotlin
fun estaAlojado(personaje: String): Boolean {
    // Lógica faltante: verifique si el personaje está alojado en alguna habitación
    return habitacionesOcupadas.containsKey(personaje) // Esto está incompleto
}
```

#### Tarea:
- Completa la función para que devuelva `true` si el personaje ya está alojado en una habitación, y `false` si no lo está.

**Solución esperada:**
```kotlin
fun estaAlojado(personaje: String): Boolean {
    return habitacionesOcupadas.containsKey(personaje)
}
```
