import org.junit.Assert.*
import org.junit.Test

class CalculadoraTest {

    private val calculadora = ScientificCalculator()

    @Test
    fun testAddition() {
        val result = calculadora.evaluate("2 + 3")
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testSubtraction() {
        val result = calculadora.evaluate("5 - 3")
        assertEquals(2.0, result, 0.0)
    }

    @Test
    fun testMultiplication() {
        val result = calculadora.evaluate("2 * 3")
        assertEquals(6.0, result, 0.0)
    }

    @Test
    fun testDivision() {
        val result = calculadora.evaluate("6 / 3")
        assertEquals(2.0, result, 0.0)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testDivisionByZero() {
        calculadora.evaluate("6 / 0")
    }

    @Test
    fun testExponentiation() {
        val result = calculadora.evaluate("2 ^ 3")
        assertEquals(8.0, result, 0.0)
    }
}