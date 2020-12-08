package io.github.davidmerrick.aoc2020.day1

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

const val NUM_OPERANDS = 3
const val GOAL_SUM = 2020

class ExpenseReportSolverPart2Test {

    @Test
    fun `Solve test input`(){
        val expenses = getInputs("day1.txt")
        val solver = ExpenseReportPart2Solver(GOAL_SUM, NUM_OPERANDS)
        val solution = solver.solve(expenses)
        solution shouldBe 241_861_950
    }

    @Test
    fun `Solve full input`(){
        val expenses = getInputs("day1-full.txt")
        val solver = ExpenseReportPart2Solver(GOAL_SUM, NUM_OPERANDS)
        val solution = solver.solve(expenses)
        solution shouldBe 73_616_634
    }

    private fun getInputs(fileName: String): List<Int> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .map { it.toInt() }
    }
}
