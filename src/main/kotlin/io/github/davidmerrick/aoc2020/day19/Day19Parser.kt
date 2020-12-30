package io.github.davidmerrick.aoc2020.day19

class Day19Parser(private val input: String) {

    fun parseRules(): List<Rule> {
        return input.split("\n\n")[0]
            .split("\n")
            .map { it.trim() }
            .filterNot { it.isEmpty() }
            .flatMap { parseRulesLine(it) }
    }

    private fun parseRulesLine(line: String): List<Rule> {
        val split = line.split(": ")
        val ruleId = split[0].toInt()

        // Handle "or" case in rules
        return split[1].split("|")
            .map { Rule(ruleId, it.trim()) }
    }

    fun parseMessages(): List<String> {
        return input.split("\n\n")[1]
            .split("\n")
            .map { it.trim() }
            .filterNot { it.isEmpty() }
    }
}
