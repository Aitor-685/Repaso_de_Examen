/**
 * Programa principal para controlar al robot Wall-e.
 * Este programa permite al usuario controlar el movimiento y las acciones del robot a través de instrucciones.
 * El ciclo principal recibe comandos de texto, los interpreta y ejecuta las acciones correspondientes.
 *
 * @author Aitor Smith Obeso Grados
 * @since 05/01/2025
 *
 * @param while este ciclo se utiliza para seguir leyendo instrucciones hasta que el usuario ingrese "END".
 *              Dependiendo de la instrucción proporcionada, el robot realizará diferentes acciones como moverse,
 *              acelerar, frenar, mostrar la posición, etc. Si la instrucción no es válida, se mostrará un mensaje de error.
 *
 * @return El ciclo sigue ejecutándose hasta que el usuario decide finalizar.
 *         El robot ejecuta las acciones solicitadas y muestra resultados en la consola.
 */
fun main() {
    val robot = Robot()
    println("Benvingut al control del robot Wall-e! Escriu les instruccions en majúscules.")
    println("Opcions disponibles:")
    println("DALT - Moure's amunt")
    println("BAIX - Moure's avall")
    println("DRETA - Moure's a la dreta")
    println("ESQUERRA - Moure's a l'esquerra")
    println("ACCELERAR - Accelerar el robot")
    println("FRENAR - Desacelerar el robot")
    println("POSICIO - Mostrar la posició actual")
    println("VELOCITAT - Mostrar la velocitat actual")
    println("MOSTRAR - Mostrar el plànol del taulell")
    println("REINICIAR - Reiniciar el robot")
    println("END - Finalitzar el programa")
    while (true) {
        print("\n>>> ")
        val input = readlnOrNull()?.trim() ?: ""

        when (input) {
            "DALT" -> robot.moveUp()
            "BAIX" -> robot.moveDown()
            "DRETA" -> robot.moveRight()
            "ESQUERRA" -> robot.moveLeft()
            "ACCELERAR" -> robot.accelerate()
            "FRENAR" -> robot.brake()
            "POSICIO" -> robot.printPosition()
            "VELOCITAT" -> robot.printSpeed()
            "MOSTRAR" -> {
                println("\nPlànol del taulell:")
                robot.showGrid()
            }
            "REINICIAR" -> robot.reset()
            "END" -> {
                println("Finalitzant el programa. Adéu!")
                break
            }
            else -> println("Instrucció no reconeguda.")
        }
    }
}

/**
 * Enum representa todos los tipos de casillas del tablero.
 * Cada casilla puede estar vacia o contener al robot.
 * @author Aitor Smith Obeso Grados
 * @since 05/01/2025
 */
enum class Casella {
    BUIT, // Casillas bacias
    ROBOT // Casilla donde se encuentra el robot
}

/**
 * Este robot tiene una posición definida por las coordenadas `x` y `y`, una velocidad inicial que puede
 * cambiar dentro de un rango, y una cuadrícula donde puede moverse.
 *
 * @property x Coordenada horizontal del robot que por defecto sera 0.
 * @property y Coordenada vertical del robot que por defecto sera 0.
 * @property speed Velocitat actual por defecto del robot y la velocidad puede ajustarse entre un mínimo de 0 y un máximo de 5.
 */
data class Robot(var x: Int = 0, var y: Int = 0, var speed: Int = 1) {
    private val maxSpeed = 5 // Velocitat máxima.
    private val minSpeed = 0 // Velocitat mínima.
    private val gridSize = 20 // Dimensiones del tablero.
    private val grid = Array(gridSize) { Array(gridSize) { Casella.BUIT } }

    /**
     * Inicia el tablero colocando el robot en la posición de inicio, que es (0, 0).
     * Esta acción coloca al robot en la casilla correspondiente dentro de la cuadrícula, marcándola
     * como una casilla ocupada por el robot.
     */
    init {
        grid[y][x] = Casella.ROBOT
    }

    /**
     * Actualiza el tablero, limpiando todas las casillas y colocando el robot en su nueva posición.
     * Este método recorre todas las celdas del tablero y las marca como vacías, luego coloca al
     * robot en la casilla correspondiente según las coordenadas actuales (x, y).
     */
    private fun updateGrid() {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                grid[i][j] = Casella.BUIT
            }
        }
        grid[y][x] = Casella.ROBOT
    }

    /**
     * Mueve al robot hacia arriba según su velocidad actual.
     * Incrementa la coordenada vertical (`y`) del robot en función de su velocidad.
     *
     * @param moveUp Aumenta la coordenada `y` del robot por su velocidad. Se utiliza
     * `coerceAtMost(gridSize - 1)` para asegurar que el robot no se mueva fuera de los límites del tablero.
     *
     * @return Llama a `updateGrid()` para actualizar el estado del tablero y reflejar la nueva posición del robot.
     */
    fun moveUp() {
        y = (y + speed).coerceAtMost(gridSize - 1)
        updateGrid()
    }

    /**
     * Mueve al robot hacia abajo según su velocidad actual.
     * Decrementa la coordenada vertical (`y`) del robot en función de su velocidad.
     *
     * @param moveDown Decrementa la coordenada `y` del robot por su velocidad. Se utiliza
     * `coerceAtLeast(0)` para asegurar que el robot no se mueva fuera de los límites del tablero por la parte inferior.
     *
     * @return Llama a `updateGrid()` para actualizar el estado del tablero y reflejar la nueva posición del robot.
     */
    fun moveDown() {
        y = (y - speed).coerceAtLeast(0)
        updateGrid()
    }

    /**
     * Mueve al robot hacia la derecha según su velocidad actual.
     * Incrementa la coordenada horizontal (`x`) del robot en función de su velocidad.
     *
     * @param moveRight Incrementa la coordenada `x` del robot por su velocidad. Se utiliza
     * `coerceAtMost(gridSize - 1)` para asegurar que el robot no se mueva fuera de los límites del tablero por el lado derecho.
     *
     * @return Llama a `updateGrid()` para actualizar el estado del tablero y reflejar la nueva posición del robot.
     */
    fun moveRight() {
        x = (x + speed).coerceAtMost(gridSize - 1)
        updateGrid()
    }

    /**
     * Mueve al robot hacia la izquierda según su velocidad actual.
     * Decrementa la coordenada horizontal (`x`) del robot en función de su velocidad.
     *
     * @param moveLeft Decrementa la coordenada `x` del robot por su velocidad. Se utiliza
     * `coerceAtLeast(0)` para asegurar que el robot no se mueva fuera de los límites del tablero por el lado izquierdo.
     *
     * @return Llama a `updateGrid()` para actualizar el estado del tablero y reflejar la nueva posición del robot.
     */
    fun moveLeft() {
        x = (x - speed).coerceAtLeast(0)
        updateGrid()
    }

    /**
     * Incrementa la velocidad del robot en 1, hasta alcanzar la velocidad máxima de 5.
     * La propiedad `speed` se incrementa de uno en uno y se utiliza la función
     * `coerceAtMost(maxSpeed)` para asegurarse de no superar el límite de velocidad.
     *
     * @return No devuelve ningún valor. Solo modifica la propiedad `speed` del robot.
     * La velocidad se incrementa en 1, y no superará el valor de `maxSpeed`.
     */
    fun accelerate() {
        speed = (speed + 1).coerceAtMost(maxSpeed)
    }

    /**
     * Disminuye la velocidad del robot en 1, hasta alcanzar la velocidad mínima de 0.
     * La propiedad `speed` se decrementa de uno en uno y se utiliza la función
     * `coerceAtLeast(minSpeed)` para asegurarse de no bajar de la velocidad mínima.
     *
     * @return No devuelve ningún valor. Solo modifica la propiedad `speed` del robot.
     * La velocidad se reduce en 1, y no será inferior a `minSpeed`.
     */
    fun brake() {
        speed = (speed - 1).coerceAtLeast(minSpeed)
    }

    /**
     * Reinicia al robot a su posición inicial (0, 0) y establece la velocidad por defecto a 1.
     * La propiedad `x`, `y` y `speed` son restablecidas a sus valores predeterminados.
     *
     * @return Utiliza el método `updateGrid` para reflejar el
     * nuevo estado del tablero después de restablecer las propiedades del robot.
     */
    fun reset() {
        x = 0
        y = 0
        speed = 1
        updateGrid()
    }

    /**
     * Muestra la posición actual del robot en el formato (x, y).
     *
     * @return Solo imprime las coordenadas actuales del robot.
     */
    fun printPosition() {
        println("Posició actual: ($x, $y)")
    }

    /**
     * Muestra la velocidad actual del robot en metros por segundo (m/s).
     *
     * @return Solo imprime la velocidad actual del robot.
     */
    fun printSpeed() {
        println("Velocitat actual: $speed m/s")
    }

    /**
     * Muestra el tablero con la posición actual del robot representada en él.
     *
     * Las casillas vacías se representan con el emoji 🧱 y la posición del robot con 🤖.
     *
     * @return Solo imprime el estado del tablero en la consola.
     */
    fun showGrid() {
        for (i in gridSize - 1 downTo 0) {
            for (j in 0 until gridSize) {
                when (grid[i][j]) {
                    Casella.BUIT -> print("\uD83E\uDDF1 ") // Casilla vacia
                    Casella.ROBOT -> print("\uD83E\uDD16 ") // Robot
                }
            }
            println() // Nueva linia despues de cada fila
        }
    }
}