/**
 * Funció entry point per a l'execució dels mètodes que calculen el factorial
 * @author Aitor Smith Obeso Grados
 */
fun main() {
    // Creació de dos arbres amb nodes
    val arbre1 = Node(
        1, listOf(
            Node(3, listOf(Node(31), Node(33), Node(35))),
            Node(5, listOf(Node(31), Node(53))),
            Node(7, listOf(Node(71))),
            Node(9, listOf(Node(31), Node(93), Node(95))),
        )
    )
    println("Arbre 1: Cerca en arbre n-ari")
    print("Introdueix el número que vols cercar: ")
    val numACercar = readlnOrNull()?.toIntOrNull() ?: run {
        println("Entrada no vàlida.")
        return
    }

    println("El número $numACercar existeix: ${cercaEnArbreNari(arbre1, numACercar)}")
    println("Número de cops que apareix el $numACercar: ${qCopsNumeroEnArbreNari(arbre1, numACercar)}")

    println("Suma de tots els valors de l'arbre: ${valorArbreNari(arbre1)}")
}

/**
 * Funció recursiva per cercar un número dins un arbre n-ari
 * @param node utils.Node inicial de l'arbre
 * @param num Número a cercar
 * @return Retorna true si el número es troba, false en cas contrari
 */
fun cercaEnArbreNari(node: Node, num: Int): Boolean {
    if (node.valor == num) return true
    for (fill in node.fills) {
        if (cercaEnArbreNari(fill, num)) return true
    }
    return false
}

/**
 * Funció recursiva per comptar quants cops apareix un número en un arbre n-ari
 * @param node utils.Node inicial de l'arbre
 * @param num Número a cercar
 * @return Retorna el nombre de vegades que el número apareix dins l'arbre
 */
fun qCopsNumeroEnArbreNari(node: Node, num: Int): Int {
    var count = if (node.valor == num) 1 else 0
    for (fill in node.fills) {
        count += qCopsNumeroEnArbreNari(fill, num)
    }
    return count
}

/**
 * Funció recursiva per calcular la suma dels valors de tots els nodes de l'arbre
 * @param node utils.Node inicial de l'arbre
 * @return Retorna la suma de tots els valors de l'arbre
 */
fun valorArbreNari(node: Node): Int {
    var suma = node.valor
    for (fill in node.fills) {
        suma += valorArbreNari(fill)
    }
    return suma
}

/**
 * Classe utils.Node que representa un node de l'arbre
 * @param valor Valor que conté el node
 * @param fills Llista de nodes fills d'aquest node
 */
data class Node(val valor: Int, val fills: List<Node> = emptyList())