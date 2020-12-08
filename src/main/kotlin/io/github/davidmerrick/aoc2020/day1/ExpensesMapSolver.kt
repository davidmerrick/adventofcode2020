package io.github.davidmerrick.aoc2020.day1

class ExpensesMapSolver(
    private val goalSum: Int
) : ExpenseReportSolver {

    override fun solve(expenses: List<Int>): Int {
        val inputs = getInputs(expenses)
        return inputs.reduce { a, b -> a * b }
    }

    private fun getInputs(expenses: List<Int>): List<Int> {
        val expenseMap = expenses.groupingBy { it }.eachCount()
        for(key in expenseMap.keys){
            val toCheck = goalSum - key
            val containsOperand = copyMapDecrementValue(expenseMap, key)
                .any { it.key == toCheck && it.value > 0 }
            if(containsOperand){
                return listOf(key, toCheck)
            }
        }

        throw RuntimeException("Operands not found summing to $goalSum")
    }

    private fun copyMapDecrementValue(expenseMap: Map<Int, Int>, toDecrement: Int): Map<Int, Int> {
        val newMap = expenseMap.toMutableMap()
        newMap[toDecrement] = expenseMap.getValue(toDecrement) - 1
        return newMap.toMap()
    }
}
