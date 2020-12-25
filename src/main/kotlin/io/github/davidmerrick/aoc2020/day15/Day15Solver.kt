package io.github.davidmerrick.aoc2020.day15

import com.google.common.collect.EvictingQueue

class Day15Solver(private val input: List<Int>) {

    private val lookbacks = mutableMapOf<Int, EvictingQueue<Int>>()

    fun solve(stop: Int = 2020): Int {
        initLookbacks()
        var lastSpoken = input.last()
        for (i in input.size until stop) {
            val toSpeak = if (lookbacks[lastSpoken]?.size == 1) {
                0
            } else {
                (i - 1) - lookbacks[lastSpoken]!!.first()
            }
            lastSpoken = toSpeak
            updateLookbacks(i, lastSpoken)
        }
        return lastSpoken
    }

    private fun initLookbacks() {
        lookbacks.clear()
        input.forEachIndexed { i, value -> updateLookbacks(i, value) }
    }

    private fun updateLookbacks(index: Int, value: Int) {
        lookbacks.compute(value) { _, v ->
            val queue = v ?: EvictingQueue.create(2)
            queue.add(index)
            queue
        }
    }
}
