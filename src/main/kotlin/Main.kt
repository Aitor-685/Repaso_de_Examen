/**
 * Función principal del programa.
 * Este programa calcula el precio final de una compra en función de:
 * - Si el cliente es VIP.
 * - Si es la semana de Black Friday.
 * - El importe de la compra.
 */
fun main() {
    println("Benvingut a The bike House!") // Mensaje de bienvenida.

    // Determinar si el cliente es VIP.
    var esVIP = false
    do {
        println("El client es VIP(si/no): ")
        val respuestaVIP = readlnOrNull()?.trim()?.lowercase()
        esVIP = when (respuestaVIP) {
            "si" -> true
            "no" -> false
            else -> {
                println("Respuesta inválida. Por favor, responde con 'si' o 'no'.")
                continue
            }
        }
    } while (respuestaVIP != "si" && respuestaVIP != "no")

    // Solicitar el importe de la compra.
    var importCompra: Double
    do {
        println("Introdueix l'import de la compra:")
        val respuestaImporte = readlnOrNull()
        importCompra = respuestaImporte?.toDoubleOrNull() ?: -1.0
        if (importCompra <= 0) {
            println("Por favor, introduce un importe válido (número positivo).")
        }
    } while (importCompra <= 0)

    // Determinar si es la semana de Black Friday.
    var esBlackFriday = false
    do {
        print("És la setmana de Black Friday? (si/no): ")
        val respuestaBlackFriday = readlnOrNull()?.trim()?.lowercase()
        esBlackFriday = when (respuestaBlackFriday) {
            "si" -> true
            "no" -> false
            else -> {
                println("Respuesta inválida. Por favor, responde con 'si' o 'no'.")
                continue
            }
        }
    } while (respuestaBlackFriday != "si" && respuestaBlackFriday != "no")

    // Calcular y mostrar el precio final de la compra.
    val preuFinal = calcularPreuFinal(importCompra, esVIP, esBlackFriday)
    println("El preu final de la teva compra és: ${"%.2f".format(preuFinal)} €")
}

/**
 * Calcula el precio final de una compra según las condiciones del cliente.
 *
 * @param importCompra El importe inicial de la compra.
 * @param esVIP Indica si el cliente es VIP.
 * @param esBlackFriday Indica si es la semana de Black Friday.
 * @return El precio final de la compra tras aplicar los descuentos correspondientes:
 *         - Si es Black Friday: se aplica un 50% de descuento.
 *         - Si el cliente es VIP o el importe supera los 200 €, se aplica un 20% de descuento.
 *         - En cualquier otro caso, no hay descuento y el precio final es igual al importe inicial.
 */
fun calcularPreuFinal(importCompra: Double, esVIP: Boolean, esBlackFriday: Boolean): Double =
    when {
        esBlackFriday -> importCompra * 0.5 // 50% de descuento si es Black Friday.
        esVIP || importCompra > 200 -> importCompra * 0.8 // 20% de descuento si es VIP o la compra > 200€.
        else -> importCompra // Sin descuento.
    }