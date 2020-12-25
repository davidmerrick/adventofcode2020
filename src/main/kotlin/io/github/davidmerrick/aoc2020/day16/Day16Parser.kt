package io.github.davidmerrick.aoc2020.day16

class Day16Parser(private val input: String) {

    fun parseFields(): Map<String, Set<Int>> {
        val fieldsText = input.split("\n\n")[0]
        return fieldsText.split("\n")
            .fold(mutableMapOf()) { acc, line ->
                acc[getFieldName(line)] = getRanges(line)
                acc
            }
    }

    fun parseTickets(): List<List<Int>> {
        return input.split("\n\n")[2].split("\n")
            .filterNot { it.startsWith("nearby") }
            .filterNot { it.isEmpty() }
            .map { line -> line.split(",").map { it.toInt() } }
    }

    fun parseYourTicket(): List<Int> {
        return input.split("\n\n")[1].split("\n")[1]
            .split(",").map { it.toInt() }
    }

    private fun getFieldName(line: String): String {
        val fieldRegex = "^([a-z ]*)\\:".toRegex()
        return fieldRegex.find(line)!!.groups[1]!!.value
    }

    private fun getRanges(line: String): Set<Int> {
        val rulesRegex = "[a-z ]*\\: ([\\d]+)\\-([\\d]+) or ([\\d]+)\\-([\\d]+)"
            .toRegex()
        val groups = rulesRegex.find(line)!!.groups
        val a = (groups[1]!!.value.toInt()..groups[2]!!.value.toInt())
            .toSet()
        val b = (groups[3]!!.value.toInt()..groups[4]!!.value.toInt())
            .toSet()
        return a.union(b)
    }
}
