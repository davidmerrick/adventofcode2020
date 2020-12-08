package io.github.davidmerrick.aoc2020.day1

import com.github.shiguruikai.combinatoricskt.combinations

class ExpenseReportPart2Solver(
    private val goalSum: Int,
    private val numOperands: Int
) {

    fun solve(expenses: List<Int>): Int {
        val inputs = getInputs(expenses)
        return inputs.reduce { a, b -> a * b }
    }

    private fun getInputs(expenses: List<Int>): List<Int> {
        return expenses.combinations(numOperands)
            .first { it.sum() == goalSum }
    }
}
