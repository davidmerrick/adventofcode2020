package io.github.davidmerrick.aoc2020.day1

class ExpenseReportSolver {

    fun solve(expenses: List<Int>, goalSum: Int): Int {
        val filteredExpenses = expenses.filter { it <= goalSum }
        val inputs = getInputs(filteredExpenses, goalSum)
        return inputs.first * inputs.second
    }

    private fun getInputs(expenses: List<Int>, goalSum: Int): Pair<Int, Int> {
        for(i in expenses.indices){
            val operandA = expenses[i]
            val testList = expenses.toMutableList()
            testList.removeAt(i)
            for(operandB in testList){
                if(operandA + operandB == goalSum){
                    return Pair(operandA, operandB)
                }
            }
        }
        throw RuntimeException("Operands adding up to $goalSum not found")
    }
}
