package io.github.davidmerrick.aoc2020.day19

data class EarleyState(
    val rule: Rule,
    val fatDot: Int,
    val origin: Int
) {
    val isComplete
        get() = fatDot == rule.productions.size
}
