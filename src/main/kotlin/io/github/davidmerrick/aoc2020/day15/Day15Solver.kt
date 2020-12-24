package io.github.davidmerrick.aoc2020.day15

object Day15Solver {
    fun solve(input: List<Int>, stop: Int = 2020): Int {
        val spoken = mutableListOf<Int>()
        spoken.addAll(input)
        for (i in input.size until stop){
            val lastSpoken = spoken.last()
            val toSpeak = if(spoken.count { it == lastSpoken } == 1){
                0
            } else {
                i - (spoken.subList(0, i-1).lastIndexOf(lastSpoken) + 1)
            }
            spoken.add(toSpeak)
        }
        return spoken.last()
    }
}
