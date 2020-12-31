package io.github.davidmerrick.aoc2020.day19

data class Rule(
    val id: String,
    val productions: List<String>
) {
    val isTerminal = productions[0] in listOf("a", "b")
}
