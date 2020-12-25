package io.github.davidmerrick.aoc2020.day16

class Day16Solver(
    private val fields: Map<String, Set<Int>>,
    private val yourTicket: List<Int>,
    private val tickets: List<List<Int>>
) {

    private val valid by lazy {
        fields.values.reduce { a, b -> a.union(b) }
    }

    /**
     * Columns of ticket field values
     */
    private val ticketFields by lazy {
        tickets
            .filter { isValid(it) }
            .fold(mutableMapOf<Int, List<Int>>()) { acc, ticket ->
                ticket.forEachIndexed { index, value ->
                    acc.compute(index) { _, v ->
                        val list = v?.toMutableList() ?: mutableListOf()
                        list.add(value)
                        list
                    }
                }
                acc
            }
    }

    fun solve(): Long {
        // Get field list, map it to a list of indices
        // that start with "departure"
        return solveFields()
            .filter { it.key.startsWith("departure") }
            .values
            .map { yourTicket[it].toLong() }
            .reduce { a, b -> a * b }
    }

    // Note: Some of these columns will match multiple
    // possible field values
    // So we need to sort first by the columns that only match one
    fun solveFields(): Map<String, Int> {
        val mutableFields = fields.toMutableMap()
        return ticketFields
            .entries
            .sortedBy { entry -> mutableFields.values.filter { it.containsAll(entry.value) }.size }
            .fold(mutableMapOf<String, Int>()) { acc, entry ->
                val key = mutableFields.entries.first { it.value.containsAll(entry.value) }.key
                mutableFields.remove(key)
                acc[key] = entry.key
                acc
            }
    }

    private fun isValid(ticket: List<Int>) = ticket.all { valid.contains(it) }
}
