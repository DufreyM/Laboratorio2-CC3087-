import org.junit.Assert.*
import org.junit.Test

class CalculatorTest {

    private val calculator = ScientificCalculator()

    @Test
    fun testAddition() {
        val result = calculator.evaluate("2 + 3")
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testSubtraction() {
        val result = calculator.evaluate("5 - 3")
        assertEquals(2.0, result, 0.0)
    }

    @Test
    fun testMultiplication() {
        val result = calculator.evaluate("2 * 3")
        assertEquals(6.0, result, 0.0)
    }

    @Test
    fun testDivision() {
        val result = calculator.evaluate("6 / 3")
        assertEquals(2.0, result, 0.0)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testDivisionByZero() {
        calculator.evaluate("6 / 0")
    }

    @Test
    fun testExponentiation() {
        val result = calculator.evaluate("2 ^ 3")
        assertEquals(8.0, result, 0.0)
    }
}
