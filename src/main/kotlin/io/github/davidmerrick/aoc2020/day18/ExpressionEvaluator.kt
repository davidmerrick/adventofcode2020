package io.github.davidmerrick.aoc2020.day18

import java.util.Stack


/**
 * Evaluates expression based on
 * http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm
 */
object ExpressionEvaluator {

    /**
     * Converts expression to postfix notation
     */
    fun toPostFix(input: List<Char>): List<Char> {
        val stack = Stack<Char>()
        val output = mutableListOf<Char>()
        for (i in input) {
            if (i.isDigit()) {
                output.add(i)
                continue
            }
            when (i) {
                '(' -> stack.push(i)
                ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        val stackTop = stack.pop()
                        if (stackTop == '(') break
                        output.add(stackTop)
                    }
                }
                '+', '*' -> {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else {
                        while (!stack.isEmpty()) {
                            val stackTop = stack.pop()
                            if (stackTop == '(') break
                            output.add(stackTop)
                        }
                    }
                    stack.push(i)
                }
            }
        }
        while (!stack.isEmpty()) {
            val stackTop = stack.pop()
            if (stackTop == '(') throw RuntimeException("Unbalanced parens")
            output.add(stackTop)
        }
        return output
    }

    fun evaluatePostFix(input: List<Char>): Int {
        val stack = Stack<Int>()
        for (i in input) {
            if (i.isDigit()) {
                stack.push(Character.getNumericValue(i))
                continue
            }
            if (i in listOf('*', '+')) {
                val a = stack.pop()
                val b = stack.pop()
                val result = if (i == '*') {
                    a * b
                } else a + b
                stack.push(result)
            }
        }

        return stack.pop()
    }
}
