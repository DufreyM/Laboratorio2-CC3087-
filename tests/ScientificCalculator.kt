/**
 * Nombre del programa: Ejercicio 02
 * Descripción: Creación de una calculadora cientifíca con la capacidad de resolver potencias y raices usando postfix. 
 * Proyecto realizado para la clase de programación de plataformas móviles
 * Autor: Leonardo Dufrey Mejía Mejía, Maria José Girón Isidro, Cristian Sebastián Tunchez. 
 * Fecha de creación: 30 de julio de 2024
 * Fecha de última modificación: 30 de julio de 2024
 * Fuentes de información: [crédito a toda fuente de información que haya aportado al desarrollo del programa]
 * HoodLab. (2022, May 11). Lets code a calculator app in Android Studio| InFix to post fix [Video]. YouTube. https://www.youtube.com/watch?v=vepeiRB31mU
 */

import java.util.Stack
import kotlin.math.*

class ScientificCalculator {

    //Función que evalua la expresión del usuario y la tokeniza. 

    fun evaluate(expression: String): Double {
    //Se reemplazan los espacios para ir creando los tokens.
        val tokens = expression.replace(" ", "").toCharArray()
        val values = Stack<Double>()
        val ops = Stack<Char>()
        
        var i = 0
        while (i < tokens.size) {
            if (tokens[i].isDigit() || tokens[i] == '.') {
                val sb = StringBuilder()
                while (i < tokens.size && (tokens[i].isDigit() || tokens[i] == '.')) {
                    sb.append(tokens[i++])
                }
                values.push(sb.toString().toDouble())
                i--
            } else if (tokens[i] == '(') {
                ops.push(tokens[i])
            } else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.pop()
            } else if (tokens[i] == '√') {
                while (ops.isNotEmpty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(tokens[i])
            } else if (tokens[i] in listOf('+', '-', '*', '/', '^')) {
                while (ops.isNotEmpty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(tokens[i])
            }
            i++
        }
        
        while (ops.isNotEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))
        }
        
        return values.pop()
    }

    //Indica que operación se debe manejar según lo que indica el token, principalmente por la raiz y la potencia que no usan el mismo signo. 
    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                //Manejo del error de la división sobre 0. 
                if (b == 0.0) throw UnsupportedOperationException("Cannot divide by zero")
                a / b
            }
            '^' -> a.pow(b)
            '√' -> sqrt(a)
            //Manejo del error si se ingresa algún signo diferente. 
            else -> throw UnsupportedOperationException("Unsupported operation")
        }
    }
    //Función para prioridad entre las operaciones

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/' || op1 == '^' || op1 == '√') && (op2 == '+' || op2 == '-')) return false
        return true
    }

}

fun main() {
    val calculator = ScientificCalculator()
    val expression = "(454 + ( 34 / 2 ) ^ 3 ) + 5"
    try {
        val result = calculator.evaluate(expression)
        println("The result of the expression is: $result")
    } catch (e: Exception) {
        println("Error evaluating expression: ${e.message}")
    }
}
