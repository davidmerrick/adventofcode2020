package io.github.davidmerrick.aoc2020.day1

class ExpenseReportPart2Solver {

    fun solve(expenses: List<Int>, goalSum: Int): Int {
        val filteredExpenses = expenses.filter { it <= goalSum }
        val inputs = getInputs(filteredExpenses, goalSum)
        return inputs.reduce { a, b -> a * b }
    }

    private fun getInputs(expenses: List<Int>, goalSum: Int): List<Int> {
        for(i in expenses.indices){
            val operandA = expenses[i]
            val jExpenses = withIndexRemoved(expenses, i)
            for(j in jExpenses.indices){
                val operandB = jExpenses[j]
                val kExpenses = withIndexRemoved(jExpenses, j)
                for(k in kExpenses.indices){
                    val operandC = kExpenses[k]
                    if((operandA + operandB + operandC) == goalSum){
                        return listOf(operandA, operandB, operandC)
                    }
                }
            }
        }
        throw RuntimeException("Operands adding up to $goalSum not found")
    }

    private fun withIndexRemoved(inputList: List<Int>, removeAt: Int): List<Int> {
        val newList = inputList.toMutableList()
        newList.removeAt(removeAt)
        return newList
    }
}
