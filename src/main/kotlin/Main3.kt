import Utils.*

data class Cotxe(
    val model: String,
    val sostreElevableElectric: Boolean,
    val cuinaIntegrada: Boolean,
    val llitConvertible: Boolean,
    val pantallaTactil: Boolean,
    val taulaICadires: Boolean,
    val assistentsConduccio: Boolean,
    val places: Int,
    val panellSolar: Boolean,
    val calefaccio: Boolean,
    val preuBase: Int
)

fun main() {
    // Definir los modelos de coches disponibles
    val cotxes = listOf(
        Cotxe("California Beach Tour",
            sostreElevableElectric = false,
            cuinaIntegrada = false,
            llitConvertible = true,
            pantallaTactil = false,
            taulaICadires = true,
            assistentsConduccio = false,
            places = 5,
            panellSolar = false,
            calefaccio = false,
            preuBase = 56000
        ),
        Cotxe("California Beach",
            sostreElevableElectric = false,
            cuinaIntegrada = false,
            llitConvertible = true,
            pantallaTactil = false,
            taulaICadires = true,
            assistentsConduccio = false,
            places = 4,
            panellSolar = false,
            calefaccio = false,
            preuBase = 58000
        ),
        Cotxe("California Beach Camper",
            sostreElevableElectric = false,
            cuinaIntegrada = true,
            llitConvertible = true,
            pantallaTactil = false,
            taulaICadires = true,
            assistentsConduccio = false,
            places = 4,
            panellSolar = false,
            calefaccio = true,
            preuBase = 63000
        ),
        Cotxe("California Ocean",
            sostreElevableElectric = true,
            cuinaIntegrada = true,
            llitConvertible = true,
            pantallaTactil = true,
            taulaICadires = true,
            assistentsConduccio = true,
            places = 4,
            panellSolar = true,
            calefaccio = true,
            preuBase = 76000
        )
    )

    // Definir los extras y sus respectivos costos
    val extres = mapOf(
        "Sostre elevable elèctric" to Pair(Cotxe::sostreElevableElectric, 3500),
        "Cuina integrada" to Pair(Cotxe::cuinaIntegrada, 3000),
        "Pantalla tàctil" to Pair(Cotxe::pantallaTactil, 1000),
        "Assistents de conducció" to Pair(Cotxe::assistentsConduccio, 1500),
        "Panell solar" to Pair(Cotxe::panellSolar, 1500),
        "Calefacció estacionària" to Pair(Cotxe::calefaccio, 2000)
    )

    // Mensaje de bienvenida
    println("**********************************************")
    println(BLUE_UNDERLINED + "Molt bones! T'ajudarem a escollir el model de furgoneta més adequat." + RESET)
    println("**********************************************")

    // Obtener el número de plazas mínimas necesarias
    val placesNecessaries = readInt(
        "Quantes places mínimes necessites ?",
        "Introdueix un numero siusplau",
        "El numero minim es 0 i el maxim 4 no fiqui numeros superiors o inferiors",
        0,
        5
    )

    // Preguntar sobre cada extra
    val extresSeleccionats = extres.keys.mapIndexed { _, extra ->
        val resposta = readopcio("Vols afegir el extra '$extra'? (si/no)", "Resposta invàlida. Per favor, respon amb 'si' o 'no'.")
        extra to resposta
    }.filter { it.second }.map { it.first }

    // Filtrar modelos que cumplan con el número de plazas necesarias
    val modelsAdients = cotxes.filter { cotxe ->
        when (placesNecessaries) {
            5 -> cotxe.places == 5 && cotxe.llitConvertible // Solo 2 personas podrán dormir en el techo elevable
            4 -> cotxe.places == 4 && cotxe.llitConvertible // Los 4 pueden dormir en camas dobles
            else -> false
        }
    }

    if (modelsAdients.isEmpty()) {
        println("No hi ha cap model que compleixi els requisits de places.")
        return
    }

    // Calcular los precios finales para todos los modelos adecuados
    val modelsAmbPreus = modelsAdients.map { cotxe ->
        val costExtresNoInclosos = extresSeleccionats.sumOf { extra ->
            val (propietat, cost) = extres[extra]!!
            if (!propietat.get(cotxe)) cost else 0 // Solo sumar si no está incluido
        }
        val preuFinal = cotxe.preuBase + costExtresNoInclosos
        cotxe to preuFinal
    }

    // Encontrar el modelo más adecuado (el de menor precio)
    val millorOpcio =
        modelsAmbPreus.minByOrNull { it.second } ?: modelsAmbPreus.minByOrNull { (cotxe, _) ->
            extresSeleccionats.sumOf { extra ->
                val (propietat, cost) = extres[extra]!!
                if (!propietat.get(cotxe)) cost else 0 // Solo sumamos si no está incluido
            }
        } ?: modelsAmbPreus.first()

    // Mostrar todos los modelos compatibles
    println("\n--- Models compatibles ---")
    modelsAmbPreus.forEach { (cotxe, preu) ->
        println("${cotxe.model}: $preu € (Preu base: ${cotxe.preuBase} €, Cost extres: ${preu - cotxe.preuBase} €)")
    }

    // Mostrar el modelo recomendado
    println("\nLa millor opció és el model: ${millorOpcio.first.model}")
    println("Preu base: ${millorOpcio.first.preuBase} €")
    println("Cost dels extres seleccionats: ${millorOpcio.second - millorOpcio.first.preuBase} €")
    println("Preu total: ${millorOpcio.second} €")
}

/**
 * Funció per llegir una resposta 'si' o 'no' del usuari.
 * Aquesta funció es crida quan volem saber si l'usuari vol afegir un extra o no.
 *
 * @param pMessageIn Missatge que es mostra per demanar l'entrada.
 * @param pMessageErrorDT Missatge d'error en cas de resposta invàlida.
 * @return Un valor booleà: true si la resposta és 'si', false si és 'no'.
 */
fun readopcio(pMessageIn: String, pMessageErrorDT: String): Boolean {
    var outputValue = false

    do {
        println(pMessageIn)
        val correctDataType = readlnOrNull()?.trim()?.lowercase()
        outputValue = when (correctDataType) {
            "si" -> true
            "no" -> false
            else -> {
                println(RED_BACKGROUND_BRIGHT + pMessageErrorDT + RESET)
                continue
            }
        }
    } while (correctDataType != "si" && correctDataType != "no")

    return outputValue
}

