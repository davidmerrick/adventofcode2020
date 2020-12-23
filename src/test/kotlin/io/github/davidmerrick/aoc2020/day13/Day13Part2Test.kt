package io.github.davidmerrick.aoc2020.day13

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day13Part2Test {

    @Test
    fun `Example test`() {
        val busList = parseInput("day13.txt")
        val time = bruteForceFindTime(busList = busList)
        time shouldBe 1_068_781
    }

    @Test
    fun `Example test 2`() {
        val busList = parseLine("67,7,59,61")
        val time = bruteForceFindTime(busList = busList)
        time shouldBe 754_018
    }

    @Test
    fun `Example test 3`() {
        val busList = parseLine("1789,37,47,1889")
        val time = bruteForceFindTime(busList = busList)
        time shouldBe 1_202_161_486
    }

    @Test
    fun `Full input`() {
        val busList = parseInput("day13-full.txt")
        val time = bruteForceFindTime(
            startTime = 100_000_000_000_000L,
            busList = busList
        )
        println(time)
    }

    private fun bruteForceFindTime(startTime: Long = 0, busList: List<BusPart2>): Long {
        return generateSequence(startTime) { it + getStepSize(busList) }
            .first { matchesConstraints(it, busList) }
    }

    private fun getStepSize(busList: List<BusPart2>): Long {
        val bus = busList.maxByOrNull { it.id }!!
        return 1L
    }

    private fun matchesConstraints(time: Long, busList: List<BusPart2>): Boolean {
        return busList.all { it.departsAtTime(time + it.constraint) }
    }

    private fun parseInput(fileName: String): List<BusPart2> {
        val lines = TestUtil.readLines(this::class, fileName)
        return parseLine(lines[1])
    }

    private fun parseLine(line: String): List<BusPart2> {
        return line.split(",")
            .mapIndexed { i, value ->
                if (value != "x") {
                    BusPart2(value.toLong(), i.toLong())
                } else null
            }
            .filterNotNull()
            .sortedBy { it.constraint }
    }
}
