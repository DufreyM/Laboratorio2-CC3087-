import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import uvg.edu.gt.Calculadora

class CalculadoraTest {

    private val calculadora = Calculadora()

    @Test
    fun testEvaluarSuma() {
        val resultado = calculadora.evaluar("2 + 3")
        assertEquals(5.0, resultado)
    }

    @Test
    fun testEvaluarResta() {
        val resultado = calculadora.evaluar("5 - 2")
        assertEquals(3.0, resultado)
    }

    @Test
    fun testEvaluarMultiplicacion() {
        val resultado = calculadora.evaluar("3 * 4")
        assertEquals(12.0, resultado)
    }

    @Test
    fun testEvaluarDivision() {
        val resultado = calculadora.evaluar("8 / 2")
        assertEquals(4.0, resultado)
    }

    @Test
    fun testEvaluarPotencia() {
        val resultado = calculadora.evaluar("2 ^ 3")
        assertEquals(8.0, resultado)
    }

    @Test
    fun testEvaluarExpresionCompleja() {
        val resultado = calculadora.evaluar("(454 + (34 / 2) ^ 3) + 5")
        assertEquals(5372.0, resultado, 0.001)
    }

    @Test
    fun testEvaluarExpresionCompleja2() {
        val resultado = calculadora.evaluar("(200 + (300 / 3) ^ 2) + 10")
        assertEquals(10210.0, resultado, 0.001)
    }

    @Test
    fun testEvaluarDivisionPorCero() {
        val exception = assertThrows<UnsupportedOperationException> {
            calculadora.evaluar("5 / 0")
        }
        assertEquals("No se puede dividir por cero", exception.message)
    }
}