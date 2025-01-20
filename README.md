 * Calcula el precio final de una compra según las condiciones del cliente.
 *
 * @param importCompra El importe inicial de la compra.
 * @param esVIP Indica si el cliente es VIP.
 * @param esBlackFriday Indica si es la semana de Black Friday.
 * @return El precio final de la compra tras aplicar los descuentos correspondientes:
 *         - Si es Black Friday: se aplica un 50% de descuento.
 *         - Si el cliente es VIP o el importe supera los 200 €, se aplica un 20% de descuento.
 *         - En cualquier otro caso, no hay descuento y el precio final es igual al importe inicial.
 *     
fun calcularPreuFinal(importCompra: Double, esVIP: Boolean, esBlackFriday: Boolean): Double =
    when {
        esBlackFriday -> importCompra * 0.5 // 50% de descuento si es Black Friday.
        esVIP || importCompra > 200 -> importCompra * 0.8 // 20% de descuento si es VIP o la compra > 200€.
        else -> importCompra // Sin descuento.
    }
