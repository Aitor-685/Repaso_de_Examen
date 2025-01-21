package Utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.test.assertEquals
import kotlin.test.assertArrayEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertNotSame
import kotlin.test.assert


class CastilloTest {

    @Test
    fun `test inicialitzarInventari`() {
        // Limpiar lista para asegurar prueba aislada
        lista_de_objectes.clear()
        
        // Ejecutar la inicialización
        inicialitzarInventari()

        // Verificar que la lista tiene 4 objetos
        assertEquals(4, lista_de_objectes.size)

        // Verificar los datos del primer objeto
        val primerObjeto = lista_de_objectes[0]
        assertEquals("Espada mágica", primerObjeto.nom)
        assertEquals(50, primerObjeto.punts)
        assertTrue(primerObjeto.especial)
    }

    @Test
    fun `test afegir_objectes`() {
        // Limpiar lista para asegurar prueba aislada
        lista_de_objectes.clear()

        // Simular agregar un objeto
        val nuevoObjeto = Castillo("Varita mágica", 40, true)
        lista_de_objectes.add(nuevoObjeto)

        // Verificar que el objeto se agregó correctamente
        assertEquals(1, lista_de_objectes.size)
        assertEquals("Varita mágica", lista_de_objectes[0].nom)
        assertEquals(40, lista_de_objectes[0].punts)
        assertTrue(lista_de_objectes[0].especial)
    }

    @Test
    fun `test calcularPuntsRetorn`() {
        // Limpiar lista y agregar objetos de prueba
        lista_de_objectes.clear()
        lista_de_objectes.add(Castillo("Objeto 1", 10, false))
        lista_de_objectes.add(Castillo("Objeto 2", 20, true))

        // Calcular puntos acumulados
        val totalPunts = calcularPuntsRetorn()

        // Verificar que los puntos son correctos
        assertEquals(30, totalPunts)
    }

    @Test
    fun `test recompteEspecialsRetorn`() {
        // Limpiar lista y agregar objetos de prueba
        lista_de_objectes.clear()
        lista_de_objectes.add(Castillo("Objeto 1", 10, true))
        lista_de_objectes.add(Castillo("Objeto 2", 20, false))
        lista_de_objectes.add(Castillo("Objeto 3", 30, true))

        // Contar objetos especiales
        val especialsCount = recompteEspecialsRetorn()

        // Verificar que el recuento de objetos especiales es correcto
        assertEquals(2, especialsCount)
    }

    @Test
    fun `test Mostrar_el_inventari`() {
        // Limpiar lista y agregar objetos de prueba
        lista_de_objectes.clear()
        lista_de_objectes.add(Castillo("Objeto 1", 10, true))
        lista_de_objectes.add(Castillo("Objeto 2", 20, false))

        // Capturar la salida estándar
        val output = captureOutput {
            Mostrar_el_inventari()
        }

        // Verificar que la salida contiene los datos esperados
        assertTrue(output.contains("Nombre: Objeto 1, Puntos: 10, Especial: true"))
        assertTrue(output.contains("Nombre: Objeto 2, Puntos: 20, Especial: false"))
        assertTrue(output.contains("Puntos acumulados: 30"))
        assertTrue(output.contains("Objetos especiales: 1"))
    }
}

/**
 * Captura la salida estándar durante la ejecución de un bloque de código.
 */
fun captureOutput(block: () -> Unit): String {
    val originalOut = System.out
    val outputStream = java.io.ByteArrayOutputStream()
    System.setOut(java.io.PrintStream(outputStream))

    try {
        block()
    } finally {
        System.setOut(originalOut)
    }
    return outputStream.toString()
}

/**
 * Clase de prueba que contiene diferentes ejemplos de pruebas unitarias en Kotlin.
 * Cada método está documentado con el propósito de su prueba.
 */
class EjemplosDePruebas {

    /**
     * Prueba que verifica que un valor es falso.
     */
    @Test
    fun testValorFalso() {
        val esPar = { numero: Int -> numero % 2 == 0 }
        assertFalse(esPar(3), "El número 3 no es par, debería ser falso")
    }

    /**
     * Prueba que verifica que un valor es verdadero.
     */
    @Test
    fun testValorVerdadero() {
        val lista = listOf(1, 2, 3)
        assertTrue(lista.contains(2), "La lista debería contener el número 2")
    }

    /**
     * Prueba que verifica que dos valores no son iguales.
     */
    @Test
    fun testNoIguales() {
        val resultado = 5 * 3
        assertNotEquals(10, resultado, "El resultado no debería ser igual a 10")
    }

    /**
     * Prueba que verifica que dos valores son iguales.
     */
    @Test
    fun testIguales() {
        val suma = 3 + 2
        assertEquals(5, suma, "La suma de 3 y 2 debería ser 5")
    }

    /**
     * Prueba que verifica que dos arreglos son iguales.
     */
    @Test
    fun testArraysIguales() {
        val esperado = arrayOf(1, 2, 3)
        val calculado = arrayOf(1, 2, 3)
        assertArrayEquals(esperado, calculado, "Los arreglos deberían ser iguales")
    }

    /**
     * Prueba que verifica que un valor es nulo.
     */
    @Test
    fun testEsNulo() {
        val valor: String? = null
        assertNull(valor, "El valor debería ser nulo")
    }

    /**
     * Prueba que verifica que un valor no es nulo.
     */
    @Test
    fun testNoEsNulo() {
        val valor: String? = "Kotlin"
        assertNotNull(valor, "El valor no debería ser nulo")
    }

    /**
     * Prueba que verifica que dos referencias apuntan al mismo objeto.
     */
    @Test
    fun testMismoObjeto() {
        val objeto1 = "Kotlin"
        val objeto2 = objeto1
        assertSame(objeto1, objeto2, "Deberían apuntar al mismo objeto")
    }

    /**
     * Prueba que verifica que dos referencias no apuntan al mismo objeto.
     */
    @Test
    fun testObjetosDiferentes() {
        val objeto1 = "Kotlin"
        val objeto2 = "Kotlin"
        assertNotSame(objeto1, objeto2, "Deberían ser objetos diferentes por referencia")
    }

    /**
     * Prueba que verifica una condición personalizada con un mensaje en caso de fallo.
     */
    @Test
    fun testCondicionPersonalizada() {
        val valor = 5
        assert(valor > 0) { "El valor debería ser positivo, pero es $valor" }
    }

    /**
     * Prueba que valida un cálculo matemático simple.
     */
    @Test
    fun testCalculo() {
        val resultado = 10 / 2
        assertEquals(5, resultado, "La división de 10 entre 2 debería ser 5")
        assertNotEquals(6, resultado, "El resultado no debería ser 6")
    }

    /**
     * Prueba que valida si una lista contiene ciertos elementos.
     */
    @Test
    fun testLista() {
        val lista = listOf("a", "b", "c")
        assertTrue(lista.contains("a"), "La lista debería contener 'a'")
        assertFalse(lista.contains("z"), "La lista no debería contener 'z'")
        assertEquals(listOf("a", "b", "c"), lista, "La lista debería ser igual a la esperada")
    }

    /**
     * Prueba que verifica la igualdad y referencias de objetos.
     */
    @Test
    fun testPersona() {
        data class Persona(val nombre: String, val edad: Int)

        val persona1 = Persona("Juan", 30)
        val persona2 = Persona("Juan", 30)
        val persona3 = persona1

        assertEquals(persona1, persona2, "Los objetos deberían ser iguales por contenido")
        assertSame(persona1, persona3, "Los objetos deberían ser el mismo por referencia")
        assertNotSame(persona1, persona2, "Los objetos no deberían ser el mismo por referencia")
    }

    /**
     * Prueba que valida si una cadena es nula o no.
     */
    @Test
    fun testCadenas() {
        val cadenaNula: String? = null
        val cadenaLlena: String? = "Hola, Kotlin"

        assertNull(cadenaNula, "La cadena debería ser nula")
        assertNotNull(cadenaLlena, "La cadena no debería ser nula")
    }
}

/**
 * Aquest fitxer conté proves unitàries per a les següents funcions:
 * ## Com executar:
 * - Aquest fitxer està pensat per ser usat amb `kotlin.test` i una eina de testing com JUnit.
 * - Desa el fitxer en el directori de tests del projecte (p. ex., `src/test/kotlin`).
 * - Executa els tests amb la teva IDE o línia de comandes.
 */

class FuncionsTests {

    // TESTS PER A FACTORIAL
    @Test
    fun testFactorial() {
        // Casos base
        assertEquals(1, factorial(0))
        assertEquals(1, factorial(1))

        // Casos regulars
        assertEquals(120, factorial(5))
        assertEquals(3628800, factorial(10))

        // Casos límit
        assertTrue(factorial(12) > 0)

        // Casos invàlids
        val exception = assertFailsWith<IllegalArgumentException> {
            factorial(-1)
        }
        assertTrue(exception.message!!.contains("ha de ser natural"))
    }

    // TESTS PER A DOBLE FACTORIAL
    @Test
    fun testDobleFactorial() {
        // Números parells
        assertEquals(2, dobleFactorial(2))
        assertEquals(8, dobleFactorial(4))
        assertEquals(384, dobleFactorial(8))

        // Números imparells
        assertEquals(1, dobleFactorial(1))
        assertEquals(15, dobleFactorial(5))
        assertEquals(945, dobleFactorial(9))

        // Zero
        assertEquals(1, dobleFactorial(0))

        // Casos invàlids
        val exception = assertFailsWith<IllegalArgumentException> {
            dobleFactorial(-2)
        }
        assertTrue(exception.message!!.contains("ha de ser natural"))
    }

    // TESTS PER A COMPTAR XIFRES
    @Test
    fun testComptarXifres() {
        // Números positius
        assertEquals(1, comptarXifres(5))
        assertEquals(2, comptarXifres(42))
        assertEquals(5, comptarXifres(12345))

        // Números negatius
        assertEquals(1, comptarXifres(-5))
        assertEquals(2, comptarXifres(-42))
        assertEquals(5, comptarXifres(-12345))

        // Zero
        assertEquals(1, comptarXifres(0))
    }

    // TESTS PER A INVERTIR NUMERO
    @Test
    fun testInvertirNumero() {
        // Números positius
        assertEquals(5, invertirNumero(5))
        assertEquals(24, invertirNumero(42))
        assertEquals(54321, invertirNumero(12345))

        // Números negatius
        assertEquals(-5, invertirNumero(-5))
        assertEquals(-24, invertirNumero(-42))
        assertEquals(-54321, invertirNumero(-12345))

        // Zero
        assertEquals(0, invertirNumero(0))
    }

    // TESTS PER A FIBONACCI
    @Test
    fun testFibonacci() {
        // Casos base
        assertEquals(0, fibonacci(0))
        assertEquals(1, fibonacci(1))

        // Casos regulars
        assertEquals(5, fibonacci(5))
        assertEquals(21, fibonacci(8))

        // Casos límit
        assertTrue(fibonacci(15) > 0)

        // Casos invàlids
        val exception = assertFailsWith<IllegalArgumentException> {
            fibonacci(-1)
        }
        assertTrue(exception.message!!.contains("ha de ser natural"))
    }

    // TESTS PER A ES CREIXENT
    @Test
    fun testEsCreixent() {
        // Números creixents
        assertTrue(esCreixent(123))
        assertTrue(esCreixent(112233))
        assertTrue(esCreixent(5))

        // Números no creixents
        assertFalse(esCreixent(321))
        assertFalse(esCreixent(54321))
        assertFalse(esCreixent(4311))
    }

    // TESTS PER A REDUCCIO DE DIGITS
    @Test
    fun testReduccioDigits() {
        // Un sol dígit
        assertEquals(7, reduccioDigits(7))
        assertEquals(0, reduccioDigits(0))

        // Múltiples dígits
        assertEquals(6, reduccioDigits(123)) // 1+2+3 = 6
        assertEquals(9, reduccioDigits(999)) // 9+9+9 = 27 -> 2+7 = 9
        assertEquals(2, reduccioDigits(38))  // 3+8 = 11 -> 1+1 = 2

        // Nombre gran
        assertEquals(1, reduccioDigits(987654321)) // Redueix fins a 1
    }
}
