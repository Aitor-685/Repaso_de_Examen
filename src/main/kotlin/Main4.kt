import Utils.*  // Importación de las utilidades definidas en otro archivo (no se especifica en el código proporcionado)
import kotlin.math.sqrt  // Importación de la función sqrt para calcular raíces cuadradas

/**
 * Muestra el menú de opciones disponibles para la calculadora.
 * El menú contiene las operaciones básicas como sumar, restar, multiplicar, dividir,
 * así como operaciones adicionales como calcular el cuadrado, la raíz cuadrada y el factorial.
 */
fun mostraMenu() {
    println("***********************")
    println(BLUE_BOLD_BRIGHT + "Menú de la calculadora:" + RESET)
    println(YELLOW_BOLD_BRIGHT + "1. Sumar" + RESET)
    println(YELLOW_BOLD_BRIGHT + "2. Restar" + RESET)
    println(YELLOW_BOLD_BRIGHT + "3. Multiplicar" + RESET)
    println(YELLOW_BOLD_BRIGHT + "4. Dividir" + RESET)
    println(YELLOW_BOLD_BRIGHT + "5. Quadrat" + RESET)
    println(YELLOW_BOLD_BRIGHT + "6. Arrel quadrada" + RESET)
    println(YELLOW_BOLD_BRIGHT + "7. Factorial" + RESET)
    println(YELLOW_BOLD_BRIGHT + "8. Sortir" + RESET)
}

/**
 * Solicita al usuario seleccionar una opción del menú y devuelve la opción seleccionada.
 * Si el usuario ingresa algo que no sea un número válido, devuelve `0` como valor predeterminado.
 *
 * @return Un número entero que representa la opción seleccionada por el usuario.
 */
fun triaOpcioMenu1(): Int {
    print(RED_BOLD_BRIGHT + "Tria una opció del menú: " + RESET)
    return readlnOrNull()?.toIntOrNull() ?: 0  // Si la entrada no es válida, devuelve 0
}

/**
 * Solicita al usuario que ingrese un número de tipo `Double`.
 * Si el valor ingresado no es un número válido, devuelve `0.0`.
 *
 * @param missatge El mensaje que se mostrará al usuario para pedir el número.
 * @return Un número de tipo `Double` introducido por el usuario.
 */
fun llegirUnElement(missatge: String = "Introdueix un número: "): Double {
    print(missatge)
    return readlnOrNull()?.toDoubleOrNull() ?: 0.0  // Devuelve el valor introducido o 0.0 si no es válido
}

/**
 * Solicita al usuario que ingrese dos números de tipo `Double`.
 *
 * @param missatge1 El mensaje que se mostrará al usuario para pedir el primer número.
 * @param missatge2 El mensaje que se mostrará al usuario para pedir el segundo número.
 * @return Un `Pair` de tipo `Double` con los dos números introducidos por el usuario.
 */
fun llegirDosElements(missatge1: String = "Introdueix el primer número: ", missatge2: String = "Introdueix el segon número: "): Pair<Double, Double> {
    val num1 = llegirUnElement(missatge1)
    val num2 = llegirUnElement(missatge2)
    return Pair(num1, num2)  // Devuelve un par con los dos números
}

/**
 * Realiza la operación de suma entre dos números.
 *
 * @param a El primer número a sumar.
 * @param b El segundo número a sumar.
 * @return El resultado de la suma de los dos números.
 */
fun suma(a: Double, b: Double) = a + b

/**
 * Realiza la operación de resta entre dos números.
 *
 * @param a El número del cual se restará.
 * @param b El número que se restará de `a`.
 * @return El resultado de la resta de los dos números.
 */
fun resta(a: Double, b: Double) = a - b

/**
 * Realiza la operación de multiplicación entre dos números.
 *
 * @param a El primer número a multiplicar.
 * @param b El segundo número a multiplicar.
 * @return El resultado de la multiplicación de los dos números.
 */
fun multiplicacio(a: Double, b: Double) = a * b

/**
 * Realiza la operación de división entre dos números.
 * Si el segundo número (denominador) es 0, devuelve `Double.NaN` para indicar que la división no es válida.
 *
 * @param a El numerador de la división.
 * @param b El denominador de la división.
 * @return El resultado de la división de los dos números, o `Double.NaN` si la división no es válida.
 */
fun divisio(a: Double, b: Double) = if (b != 0.0) a / b else Double.NaN

/**
 * Calcula el cuadrado de un número.
 *
 * @param a El número al cual se le calculará el cuadrado.
 * @return El cuadrado del número.
 */
fun quadrat(a: Double) = a * a

/**
 * Calcula la raíz cuadrada de un número.
 * Si el número es negativo, devuelve `Double.NaN` porque no se puede calcular la raíz cuadrada de números negativos en los números reales.
 *
 * @param a El número del cual se calculará la raíz cuadrada.
 * @return La raíz cuadrada del número, o `Double.NaN` si el número es negativo.
 */
fun arrelQuadrada(a: Double) = if (a >= 0) sqrt(a) else Double.NaN

/**
 * Calcula el factorial de un número entero.
 * Si el número es negativo, se considera el resultado como `0` porque no se puede calcular el factorial de números negativos.
 *
 * @param a El número entero cuyo factorial se desea calcular.
 * @return El factorial de `a` como un entero.
 */
fun factorial(a: Int): Int = if (a >= 0) (1..a).fold(1) { acc, i -> acc * i } else 0

/**
 * Función principal que ejecuta la calculadora.
 * Presenta el menú de opciones y solicita al usuario que seleccione una operación. Luego realiza la operación
 * correspondiente y muestra el resultado. El ciclo se repite hasta que el usuario elige salir.
 */
fun main() {
    // Ciclo infinito para permitir que el usuario realice múltiples operaciones hasta que decida salir.
    while (true) {
        mostraMenu()  // Muestra el menú de opciones
        when (triaOpcioMenu1()) {  // Solicita al usuario elegir una opción del menú
            1 -> {  // Opción 1: Sumar
                val (a, b) = llegirDosElements()  // Solicita dos números
                println(GREEN_BOLD_BRIGHT + "Resultat: ${suma(a, b)}" + RESET)  // Muestra el resultado de la suma
            }
            2 -> {  // Opción 2: Restar
                val (a, b) = llegirDosElements()  // Solicita dos números
                println(GREEN_BOLD_BRIGHT + "Resultat: ${resta(a, b)}" + RESET)  // Muestra el resultado de la resta
            }
            3 -> {  // Opción 3: Multiplicar
                val (a, b) = llegirDosElements()  // Solicita dos números
                println(GREEN_BOLD_BRIGHT + "Resultat: ${multiplicacio(a, b)}" + RESET)  // Muestra el resultado de la multiplicación
            }
            4 -> {  // Opción 4: Dividir
                val (a, b) = llegirDosElements()  // Solicita dos números
                println(GREEN_BOLD_BRIGHT + "Resultat: ${divisio(a, b)}" + RESET)  // Muestra el resultado de la división
            }
            5 -> {  // Opción 5: Quadrat
                val a = llegirUnElement()  // Solicita un número
                println(GREEN_BOLD_BRIGHT + "Resultat: ${quadrat(a)}" + RESET)  // Muestra el resultado del cuadrado
            }
            6 -> {  // Opción 6: Arrel cuadrada
                val a = llegirUnElement()  // Solicita un número
                println(GREEN_BOLD_BRIGHT + "Resultat: ${arrelQuadrada(a)}" + RESET)  // Muestra el resultado de la raíz cuadrada
            }
            7 -> {  // Opción 7: Factorial
                val a = llegirUnElement("Introdueix un número enter per al factorial: ").toInt()  // Solicita un número entero
                println(GREEN_BOLD_BRIGHT + "Resultat: ${factorial(a)}" + RESET)  // Muestra el resultado del factorial
            }
            8 -> {  // Opción 8: Salir
                println("Sortint de l'aplicació. Adéu!")  // Mensaje de despedida
                break  // Sale del ciclo y termina el programa
            }
            else -> println("Opció no vàlida, torna-ho a intentar.")  // Si el usuario ingresa una opción no válida
        }
    }
}
