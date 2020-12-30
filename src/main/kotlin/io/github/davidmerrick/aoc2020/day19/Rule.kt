package io.github.davidmerrick.aoc2020.day19

data class Rule(
    val id: Int,
    val productions: List<Char>
) {
    val isTerminal = !productions[0].isDigit()
}
