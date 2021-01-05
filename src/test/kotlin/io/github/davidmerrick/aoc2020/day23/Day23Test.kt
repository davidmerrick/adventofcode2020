package io.github.davidmerrick.aoc2020.day23

import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day23Test {

    @Test
    fun `Part 1 example`() {
        val game = CupGame(parseCups("389125467"))
        game.play(10)
        game.getResult() shouldBe listOf(9, 2, 6, 5, 8, 3, 7, 4)
    }

    @Test
    fun `Part 1 example with 100 rounds`() {
        val game = CupGame(parseCups("389125467"))
        game.play(100)
        game.getResult() shouldBe listOf(6, 7, 3, 8, 4, 5, 2, 9)
    }

    @Test
    fun `Part 1 full`() {
        val game = CupGame(parseCups("469217538"))
        game.play(100)
        game.getResult().joinToString("") shouldBe "27956483"
    }

    @Test
    fun `Part 2 example`() {
        val startingCups = parseCupsPart2("389125467")
        val game = CupGame(startingCups)
        game.play(10_000_000)
        val starCups = game.getResult(2)
        starCups shouldContain (934_001)
        starCups shouldContain (159_792)
    }

    @Test
    fun `Part 2 full`() {
        val startingCups = parseCupsPart2("469217538")
        val game = CupGame(startingCups)
        game.play(10_000_000)
        val starCups = game.getResult(2)
        val result = starCups.map { it.toLong() }
                .reduce { a, b -> a * b }
        println(result)
    }

    private fun parseCups(input: String): List<Int> {
        return input.split("")
                .filterNot { it.isEmpty() }
                .map { it.toInt() }
    }

    private fun parseCupsPart2(input: String): List<Int> {
        val seed = parseCups(input)
        val newCups = mutableListOf<Int>()
        newCups.addAll(seed)
        newCups.addAll(((seed.maxOrNull()!! + 1)..1_000_000))
        return newCups
    }
}