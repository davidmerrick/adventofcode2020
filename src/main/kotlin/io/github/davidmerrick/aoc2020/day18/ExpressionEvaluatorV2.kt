package io.github.davidmerrick.aoc2020.day18

import java.util.Stack


/**
 * Based on
 * https://runestone.academy/runestone/books/published/pythonds/BasicDS/InfixPrefixandPostfixExpressions.html#:~:text=The%20addition%20operator%20then%20appears,precedence%2C%20with%20%2B%20coming%20after.
 */
object ExpressionEvaluatorV2 {

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
                    while (!stack.isEmpty()) {
                        val stackTop = stack.pop()
                        if (stackTop == '(') break
                        output.add(stackTop)
                    }
                }
                '*' -> {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else {
                        // Pop any operators that have higher or equal precedence,
                        // add to output
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            val stackTop = stack.pop()
                            output.add(stackTop)
                        }
                    }
                    stack.push(i)
                }
                '+' -> {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else {
                        // Pop any operators that have higher or equal precedence,
                        // add to output
                        while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != '*') {
                            val stackTop = stack.pop()
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

    fun evaluatePostFix(input: List<Char>): Long {
        return ExpressionEvaluator.evaluatePostFix(input)
    }
}
