package io.github.davidmerrick.aoc2020.day1

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val GOAL_SUM = 2020

class ExpensesMapSolverTest {

    @Test
    fun `Solve test input with 2 operands`(){
        val expenses = getInputs("day1.txt")
        val solver = ExpensesMapSolver(GOAL_SUM)
        val solution = solver.solve(expenses)
        solution shouldBe 514579
    }

    @Test
    fun `Solve full input with 2 operands`(){
        val expenses = getInputs("day1-full.txt")
        val solver = ExpensesMapSolver(GOAL_SUM)
        val solution = solver.solve(expenses)
        solution shouldBe 121_396
    }


    private fun getInputs(fileName: String): List<Int> {
        return TestUtil.readLines(this::class, fileName)
            .map { it.toInt() }
    }
}
