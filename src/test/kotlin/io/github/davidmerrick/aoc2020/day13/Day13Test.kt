package io.github.davidmerrick.aoc2020.day13

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.should
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun `Parse input`(){
        val input = parseInput("day13.txt")
        input.first shouldBe 939
        input.second.size shouldBe 5
    }

    @Test
    fun `Bus goal time test`(){
        val bus = Bus(7)
        bus.getClosestTimeTo(939) shouldBe 945
    }

    @Test
    fun `Solve example 1`(){
        val input = parseInput("day13.txt")
        val goalTime = input.first
        val buses = input.second
        val min = buses.minByOrNull { it.getClosestTimeTo(goalTime) }
        min!!.id shouldBe 59
        (min.getClosestTimeTo(goalTime) - goalTime) * min.id shouldBe 295
    }

    @Test
    fun `Solve Part 1`(){
        val input = parseInput("day13-full.txt")
        val goalTime = input.first
        val buses = input.second
        val min = buses.minByOrNull { it.getClosestTimeTo(goalTime) }
        println((min!!.getClosestTimeTo(goalTime) - goalTime) * min!!.id)
    }

    private fun parseInput(fileName: String): Pair<Int, List<Bus>> {
        val lines = TestUtil.readLines(this::class, fileName)
        return Pair(
            lines[0].toInt(),
            lines[1].split(",")
                .filter { it != "x" }
                .map { Bus(it.toInt()) }
        )
    }
}
