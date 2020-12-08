package io.github.davidmerrick.aoc2020.day1

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class ExpenseReportSolverPart2Test {

    @Test
    fun `Solve test input`(){
        val expenses = getInputs("day1.txt")
        val solver = ExpenseReportPart2Solver()
        val solution = solver.solve(expenses, 2020)
        solution shouldBe 241_861_950
    }

    @Test
    fun `Solve full input`(){
        val expenses = getInputs("day1-full.txt")
        val solver = ExpenseReportPart2Solver()
        val solution = solver.solve(expenses, 2020)
        println(solution)
    }

    private fun getInputs(fileName: String): List<Int> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .map { it.toInt() }
    }
}
