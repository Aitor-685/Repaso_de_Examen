import Utils.*
import java.util.*

val scan: Scanner = Scanner(System.`in`)

fun main() {
    val salariBase = 1250
    val preuHoresExtras = 15
    val preuHoresExtrasMes = 12
    val plusRisc = 250
    val plusNocturnitat = 6

    println("**********************************************")
    println(BLUE_UNDERLINED + "Molt bones! Calcularem el teu sou d'aquest mes" + RESET)
    println("**********************************************")

    // Preguntar al treballador quantes hores extra ha fet
    val horesExtras = readInt(
        "Quantes hores extra has fet aquest mes?",
        "Resposta invàlida. Per favor, escriu un número vàlid d'hores extra.",
        "Les hores extra han de ser entre 0 i 30.",
        0,
        30
    )
    println("**********************************************")

    // Preguntar si treballa en un sector de risc
    val sectorRisc = readYesNo(
        "Estàs en un sector de risc (exposat a malalties contagioses)?",
        "Resposta invàlida. Per favor, respon amb 'si' o 'no'."
    )
    println("**********************************************")

    // Preguntar quantes hores extra nocturnes ha fet
    val horesNocturnes = readHours(
        "Quantes hores extra nocturnes has fet aquest mes?",
        "Resposta invàlida. Per favor, escriu un número vàlid d'hores extra nocturnes (màxim 30).",
        30
    )
    println("**********************************************")

    // Calcular el salari final amb els ajustaments (hores extres, plusos)
    val salariFinal = calcularSalariFinal(salariBase, horesExtras, sectorRisc, horesNocturnes, preuHoresExtras, preuHoresExtrasMes, plusRisc, plusNocturnitat)

    // Mostrar el resultat final
    println(GREEN_UNDERLINED + "El teu sou final aquest mes és de: ${"%.2f".format(salariFinal)} €" + RESET)
}

/**
 * Funció que calcula el salari final d'un treballador en funció de les hores extres,
 * el sector de risc, les hores extres nocturnes i altres ajustaments.
 *
 * @param salariBase Salari base del treballador.
 * @param horesExtras Nombre d'hores extres realitzades.
 * @param sectorRisc Si el treballador està en un sector de risc.
 * @param horesNocturnes Nombre d'hores extra nocturnes realitzades.
 * @param preuHoresExtras Preu per hora extra normal.
 * @param preuHoresExtrasMes Preu per hora extra després de 5 hores.
 * @param plusRisc Plus per treballar en un sector de risc.
 * @param plusNocturnitat Plus per hores extra nocturnes.
 * @return El salari final del treballador després de tots els ajustaments.
 */
fun calcularSalariFinal(
    salariBase: Int,
    horesExtras: Int,
    sectorRisc: Boolean,
    horesNocturnes: Int,
    preuHoresExtras: Int,
    preuHoresExtrasMes: Int,
    plusRisc: Int,
    plusNocturnitat: Int
): Double {
    var salariFinal = salariBase.toDouble()

    // Calcular el cost de les hores extres
    if (horesExtras > 0) {
        salariFinal += if (horesExtras <= 5) {
            horesExtras * preuHoresExtras
        } else {
            5 * preuHoresExtras + (horesExtras - 5) * preuHoresExtrasMes
        }
    }

    // Afegir plus de risc si el treballador està exposat a malalties contagioses
    if (sectorRisc) {
        salariFinal += plusRisc
        salariFinal += horesExtras * 5  // Afegir 5€ per cada hora extra realitzada
    }

    // Afegir plus de nocturnitat per les hores nocturnes realitzades
    if (horesNocturnes > 0) {
        salariFinal += horesNocturnes * (preuHoresExtras + plusNocturnitat)
    }

    return salariFinal
}

/**
 * Funció que llegeix un enter des de la consola i valida que estigui dins d'un rang determinat.
 *
 * @param pMessageIn Missatge que es mostra per demanar l'entrada.
 * @param pMessageErrorDT Missatge d'error si l'entrada no és un número vàlid.
 * @param pMessageErrorDV Missatge d'error si el número està fora del rang vàlid.
 * @param pMin Valor mínim permès per l'entrada.
 * @param pMax Valor màxim permès per l'entrada.
 * @return El valor enter introduït per l'usuari dins del rang especificat.
 */
fun readInt(pMessageIn: String
            , pMessageErrorDT: String
            , pMessageErrorDV: String
            , pMin: Int
            , pMax: Int
): Int{

    var outputValue = 0
    var correctDataType: Boolean

    do {
        println(pMessageIn)
        correctDataType = scan.hasNextInt()

        if (!correctDataType) {
            println(RED_BACKGROUND_BRIGHT + "ERROR: " + pMessageErrorDT + RESET)
        } else {
            outputValue = scan.nextInt()

            // Validar que l'entrada estigui dins del rang permès
            if (outputValue < pMin || outputValue > pMax) {
                println(YELLOW_BOLD_BRIGHT + "WARNING: " + pMessageErrorDV + RESET)
                correctDataType = false
            }
        }
        scan.nextLine()
    } while (!correctDataType)

    return outputValue
}

/**
 * Funció que llegeix una resposta de 'si' o 'no' des de la consola i valida que sigui vàlida.
 *
 * @param pMessageIn Missatge que es mostra per demanar l'entrada.
 * @param pMessageErrorDT Missatge d'error si l'entrada no és vàlida.
 * @return Un valor booleà que indica si la resposta va ser 'si' o 'no'.
 */
fun readYesNo(pMessageIn: String, pMessageErrorDT: String): Boolean {
    var outputValue: Boolean? = null
    var correctDataType = false

    do {
        println(pMessageIn)
        val respuesta = readlnOrNull()?.trim()?.lowercase()

        when (respuesta) {
            "si" -> {
                outputValue = true
                correctDataType = true
            }
            "no" -> {
                outputValue = false
                correctDataType = true
            }
            else -> {
                println(RED_BACKGROUND_BRIGHT + pMessageErrorDT + RESET)
            }
        }
    } while (!correctDataType)

    return outputValue!!
}

/**
 * Funció que llegeix el nombre d'hores extra nocturnes i valida que estiguin dins del rang permès.
 *
 * @param pMessageIn Missatge que es mostra per demanar l'entrada.
 * @param pMessageErrorDT Missatge d'error si l'entrada no és vàlida.
 * @param pMax Valor màxim permès per les hores extres nocturnes.
 * @return El nombre d'hores extra nocturnes introduït.
 */
fun readHours(pMessageIn: String, pMessageErrorDT: String, pMax: Int): Int {
    var outputValue = 0
    var correctDataType = false

    do {
        println(pMessageIn)
        val resposta = readlnOrNull()?.trim()?.toIntOrNull()

        if (resposta == null || resposta < 0 || resposta > pMax) {
            println(RED_BACKGROUND_BRIGHT + pMessageErrorDT + RESET)
        } else {
            outputValue = resposta
            correctDataType = true
        }
    } while (!correctDataType)

    return outputValue
}
