import Utils.*

/**
 * Programa principal que calcula el precio final de reventa de una figura Otaku,
 * utilizando validaciones de entrada y estilos personalizados para los mensajes.
 */
fun main() {
    println(BLUE_UNDERLINED + "Benvingut Wallapop per vendre figures Otaku de Tokio" + RESET)

    // Solicitar el precio de la figura
    var preuFigure: Double
    do {
        val preuFigureStr = readWord(
            pMessageIn = YELLOW_UNDERLINED + "Introdueix el preu de la figure (numèric):" + RESET,
            pMessageErrorDT = "El preu no pot estar buit ni ser invàlid."
        )
        preuFigure = preuFigureStr.toDoubleOrNull() ?: -1.0
        if (preuFigure <= 0) {
            println(RED_BACKGROUND_BRIGHT + "ERROR: El preu ha de ser un número positiu." + RESET)
        }
    } while (preuFigure <= 0) // Repite hasta que se introduzca un número positivo.

    // Solicitar el estado de la figura
    var estatFigure: Boolean?  // Inicialización explícita.
    do {
        val estatFigureStr = readWord(
            pMessageIn = YELLOW_UNDERLINED + "La figure està en un bon estat? (si/no):" + RESET,
            pMessageErrorDT = "Has de respondre 'si' o 'no'."
        )
        estatFigure = when (estatFigureStr.trim().lowercase()) {
            "si" -> true
            "no" -> false
            else -> {
                println(RED_BACKGROUND_BRIGHT + "ERROR: Resposta no vàlida, només 'si' o 'no' són acceptats." + RESET)
                null // Vuelve al inicio del bucle si la respuesta no es válida.
            }
        }
    } while (estatFigure == null) // Repite hasta que se proporcione una entrada válida.

    // Calcular y mostrar el precio final
    val preuFinal = calcularPreuFinal(preuFigure, estatFigure)
    println(GREEN_UNDERLINED + "El preu final a la que pots revendre és de: ${"%.2f".format(preuFinal)} €" + RESET)
}

/**
 * Calcula el precio final de reventa de una figura.
 *
 * @param Preufigure El precio original de la figura.
 * @param EstatFigure Indica si la figura está en buen estado (true) o en mal estado (false).
 * @return El precio de reventa, calculado según las siguientes reglas:
 *         - Si la figura está en buen estado, se aplica un incremento del 25%.
 *         - Si la figura no está en buen estado, se aplica un incremento del 10%.
 */
fun calcularPreuFinal(Preufigure: Double, EstatFigure: Boolean): Double =
    if (EstatFigure) {
        Preufigure * 1.25 // Incremento del 25% si la figura está en buen estado.
    } else {
        Preufigure * 1.10 // Incremento del 10% si la figura no está en buen estado.
    }

/**
 * Solicita al usuario introducir una palabra, verificando que no esté vacía.
 *
 * @param pMessageIn Mensaje que se muestra al usuario solicitando la entrada de datos.
 * @param pMessageErrorDT Mensaje de error que se muestra si el usuario no introduce un valor válido.
 * @return La palabra introducida por el usuario, garantizando que no es una cadena vacía.
 */
fun readWord(pMessageIn: String, pMessageErrorDT: String): String {
    var outputValue: String
    do {
        // Mostrar el mensaje de entrada.
        println(pMessageIn)
        outputValue = readlnOrNull() ?: "" // Leer la entrada del usuario.
        if (outputValue.isEmpty()) {
            // Mostrar mensaje de error si la entrada está vacía.
            println(RED_BACKGROUND_BRIGHT + "ERROR: " + pMessageErrorDT + RESET)
        }
    } while (outputValue.isEmpty()) // Repetir hasta que el usuario introduzca un valor no vacío.

    return outputValue // Devolver la palabra válida introducida por el usuario.
}
