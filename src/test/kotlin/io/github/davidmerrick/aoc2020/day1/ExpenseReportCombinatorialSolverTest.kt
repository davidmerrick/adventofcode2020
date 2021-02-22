package io.github.davidmerrick.aoc2020.day1

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val NUM_OPERANDS = 3
private const val GOAL_SUM = 2020

class ExpenseReportCombinatorialSolverTest {

    @Test
    fun `Solve test input`(){
        val expenses = getInputs("day1.txt")
        val solver = ExpensesCombinatorialSolver(GOAL_SUM, NUM_OPERANDS)
        val solution = solver.solve(expenses)
        solution shouldBe 241_861_950
    }

    @Test
    fun `Solve full input`(){
        val expenses = getInputs("day1-full.txt")
        val solver = ExpensesCombinatorialSolver(GOAL_SUM, NUM_OPERANDS)
        val solution = solver.solve(expenses)
        solution shouldBe 73_616_634
    }

    @Test
    fun `Solve full input with 2 operands`(){
        val expenses = getInputs("day1-full.txt")
        val solver = ExpensesCombinatorialSolver(GOAL_SUM, 2)
        val solution = solver.solve(expenses)
        solution shouldBe 121_396
    }

    private fun getInputs(fileName: String): List<Int> {
        return TestUtil.readLines(this::class, fileName)
            .map { it.toInt() }
    }
}
