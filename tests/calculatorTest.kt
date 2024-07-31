import org.junit.Assert.*
import org.junit.Test

class CalculatorTest {
    private val calculator = ScientificCalculator()

    @Test
    fun testSuma(){
        val result = calculator.evaluate("2 + 3")
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testResta() {
        val result = calculator.evaluate("5 - 3")
        assertEquals(2.0, result, 0.0)
    }
}