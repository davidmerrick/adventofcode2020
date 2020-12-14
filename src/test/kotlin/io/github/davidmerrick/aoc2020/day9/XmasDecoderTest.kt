package io.github.davidmerrick.aoc2020.day9

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class XmasDecoderTest {

    @Test
    fun `Parse input`(){
        val input = parseInput("day9.txt")
        input.size shouldBe 20
    }

    @Test
    fun `Find anomaly in test input`(){
        val input = parseInput("day9.txt")
        val decoder = XmasDecoder(5, input)
        decoder.findFirstAnomaly() shouldBe 127
    }

    @Test
    fun `Find anomaly in part 1 input`(){
        val input = parseInput("day9-full.txt")
        val decoder = XmasDecoder(25, input)
        println(decoder.findFirstAnomaly())
    }

    @Test
    fun `Part 2 using test input`(){
        val input = parseInput("day9.txt")
        val decoder = XmasDecoder(5, input)
        val anomaly = 127L
        val sumSet = decoder.findContiguousSet(anomaly)
        sumSet.size shouldBe 4
        sumSet.minOrNull() shouldBe 15
        sumSet.maxOrNull() shouldBe 47
    }

    @Test
    fun `Part 2, full input`(){
        val input = parseInput("day9-full.txt")
        val decoder = XmasDecoder(25, input)
        val anomaly = 2089807806L
        val sumSet = decoder.findContiguousSet(anomaly)
        println(sumSet.minOrNull()!! + sumSet.maxOrNull()!!)
    }

    private fun parseInput(fileName: String): List<Long> {
        return TestUtil.parseInput(
            this::class,
            fileName
        ) { it.toLong() }
    }
}
