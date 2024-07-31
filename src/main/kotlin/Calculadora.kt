package uvg.edu.gt
/**
 * Laboratorio 2 - Programación de Plataformas Móviles
 * Descripción: Creación de una calculadora científica con la capacidad de resolver potencias y raíces usando postfix.
 * Autores: Leonardo Mejía; Maria José Girón; Cristian Túnchez
 * Fecha: 30 de julio de 2024
 * Fuentes: HoodLab. (11 de mayo de 2022). Lets code a calculator app in Android Studio| InFix to post fix (Video). YouTube. https://www.youtube.com/watch?v=vepeiRB31mU
 */
import java.util.Stack
import kotlin.math.*

class Calculadora {

    fun evaluar(expresion: String): Double {
        val tokens = expresion.replace(" ", "").toCharArray()
        val valores = Stack<Double>()
        val ops = Stack<Char>()

        var i = 0
        while (i < tokens.size) {
            if (tokens[i].isDigit() || tokens[i] == '.') {
                val sb = StringBuilder()
                while (i < tokens.size && (tokens[i].isDigit() || tokens[i] == '.')) {
                    sb.append(tokens[i++])
                }
                valores.push(sb.toString().toDouble())
                i--
            } else if (tokens[i] == '(') {
                ops.push(tokens[i])
            } else if (tokens[i] == ')') {
                while (ops.isNotEmpty() && ops.peek() != '(') {
                    valores.push(aplicarOperacion(ops.pop(), valores.pop(), valores.pop()))
                }
                if (ops.isNotEmpty()) ops.pop()
            } else if (tokens[i] == '√') {
                ops.push(tokens[i])
            } else if (tokens[i] in listOf('+', '-', '*', '/', '^')) {
                while (ops.isNotEmpty() && tienePrecedencia(tokens[i], ops.peek())) {
                    valores.push(aplicarOperacion(ops.pop(), valores.pop(), valores.pop()))
                }
                ops.push(tokens[i])
            }
            i++
        }

        while (ops.isNotEmpty()) {
            valores.push(aplicarOperacion(ops.pop(), valores.pop(), valores.pop()))
        }

        return if (valores.isNotEmpty()) valores.pop() else 0.0
    }

    private fun aplicarOperacion(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException("No se puede dividir por cero")
                a / b
            }
            '^' -> a.pow(b)
            '√' -> sqrt(b)
            else -> throw UnsupportedOperationException("Operación no soportada")
        }
    }

    private fun tienePrecedencia(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/' || op1 == '^' || op1 == '√') && (op2 == '+' || op2 == '-')) return false
        return true
    }
}

fun main() {
    val calculadora = Calculadora()
    val expresion = "(454+(34/2)^3)+5"
    try {
        val resultado = calculadora.evaluar(expresion)
        println("El resultado de la expresión es: $resultado")
    } catch (e: Exception) {
        println("Error al evaluar la expresión: ${e.message}")
    }
}