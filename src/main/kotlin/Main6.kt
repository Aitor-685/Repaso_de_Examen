fun main() {
    while (true) {
        println("Menú:")
        println("1. Calcular el Factorial (amb càlculs parcials)")
        println("2. Calcular el Doble Factorial")
        println("3. Comptar xifres d'un número")
        println("4. Invertir número")
        println("5. Fibonacci: Element n de la successió")
        println("6. Determinar si un número és creixent")
        println("7. Reducció de dígits")
        println("8. Sortir")
        print("Selecciona una opció (1-8): ")

        when (readln().toInt()) {
            1 -> {
                print("Introdueix un número per calcular el factorial: ")
                val num = readln().toInt()
                println("Resultat: ${factorial1(num)}")
            }
            2 -> {
                print("Introdueix un número per calcular el doble factorial: ")
                val num = readln().toInt()
                println("Resultat: ${dobleFactorial(num)}")
            }
            3 -> {
                print("Introdueix un número per comptar les seves xifres: ")
                val num = readln().toInt()
                println("Xifres: ${comptarXifres(num)}")
            }
            4 -> {
                print("Introdueix un número per invertir-lo: ")
                val num = readln().toInt()
                println("Invertit: ${invertirNumero(num)}")
            }
            5 -> {
                print("Introdueix un número n per obtenir el nèssim element de Fibonacci: ")
                val num = readln().toInt()
                println("Fibonacci($num) = ${fibonacci(num)}")
            }
            6 -> {
                print("Introdueix un número per determinar si és creixent: ")
                val num = readln().toInt()
                println("És creixent? ${esCreixent(num)}")
            }
            7 -> {
                print("Introdueix un número per calcular la reducció de dígits: ")
                val num = readln().toInt()
                println("Reducció de dígits: ${reduccioDigits(num)}")
            }
            8 -> {
                println("Fins aviat!")
                break
            }
            else -> println("Opció no vàlida. Torna a intentar-ho.")
        }

        println() // Espai en blanc després de cada opció
    }
}
/**
 * Calcula el factorial d'un número de manera recursiva.
 * Inclou càlculs parcials per a cada pas.
 *
 * @param n Número enter natural (>= 0).
 * @return El factorial de n (n!).
 */
fun factorial1(n: Int): Int {
    require(n >= 0) { "El número ha de ser natural (>= 0)" }
    if (n == 0) return 1
    val result = n * factorial(n - 1)
    println("Calculant: $n! = $result")
    return result
}

/**
 * Calcula el doble factorial d'un número de manera recursiva.
 * El doble factorial es defineix com el producte dels enters positius amb la mateixa paritat
 * (parells o imparells) fins a arribar a 1 o 0.
 *
 * @param n Número enter natural (>= 0).
 * @return El doble factorial de n (n!!).
 */
fun dobleFactorial(n: Int): Int {
    require(n >= 0) { "El número ha de ser natural (>= 0)" }
    if (n <= 1) return 1
    return n * dobleFactorial(n - 2)
}

/**
 * Compta el nombre de xifres d'un número.
 *
 * @param n Número enter.
 * @return El nombre de xifres del número.
 */
fun comptarXifres(n: Int): Int {
    return n.toString().length
}

/**
 * Inverteix l'ordre de les xifres d'un número.
 *
 * @param n Número enter.
 * @return El número amb les xifres invertides.
 */
fun invertirNumero(n: Int): Int {
    return n.toString().reversed().toInt()
}

/**
 * Calcula l'element n de la successió de Fibonacci de manera recursiva.
 * La successió de Fibonacci es defineix com:
 * - fib(0) = 0
 * - fib(1) = 1
 * - fib(n) = fib(n-1) + fib(n-2) per n > 1
 *
 * @param n Número enter natural (>= 0).
 * @return L'element n de la successió de Fibonacci.
 */
fun fibonacci(n: Int): Int {
    require(n >= 0) { "El número ha de ser natural (>= 0)" }
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
}

/**
 * Determina si un número és creixent.
 * Un número és creixent si totes les seves xifres estan ordenades de manera no decreixent.
 *
 * @param n Número enter.
 * @return `true` si el número és creixent, `false` altrament.
 */
fun esCreixent(n: Int): Boolean {
    val digits = n.toString()
    for (i in 0 until digits.length - 1) {
        if (digits[i] > digits[i + 1]) return false
    }
    return true
}

/**
 * Redueix els dígits d'un número de manera recursiva.
 * Consisteix a sumar els dígits del número fins a obtenir un sol dígit.
 *
 * @param n Número enter natural (>= 0).
 * @return La reducció dels dígits del número.
 */
fun reduccioDigits(n: Int): Int {
    if (n < 10) return n // un sol dígit
    val suma = n.toString().map { it.digitToInt() }.sum() // Suma dels dígits
    return reduccioDigits(suma) // Torna a reduir fins a tenir un sol dígit
}
