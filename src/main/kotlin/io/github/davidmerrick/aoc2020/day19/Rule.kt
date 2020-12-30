package io.github.davidmerrick.aoc2020.day19

data class Rule(
    val id: Int,
    val productions: List<Int>,
    val terminal: Char? = null
) {
    val isTerminal: Boolean
        get() = terminal != null
}
