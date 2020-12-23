package io.github.davidmerrick.aoc2020.day13

data class BusPart2(val id: Long, val constraint: Long) {
    fun departsAtTime(time: Long) = time % id == 0L
}
