package io.github.davidmerrick.aoc2020.day22

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun `Parse cards`() {
        val game = parseCards("example.txt")
        game.startingCards1.size shouldBe 5
        game.startingCards1.shouldContain(9)

        game.startingCards2.size shouldBe 5
        game.startingCards2.shouldContain(7)
    }

    @Test
    fun `Play example game`() {
        val game = parseCards("example.txt")
        val result = game.play()
        result.numRounds shouldBe 29
        var multiple = result.winningCards.size
        var sum = 0
        for (card in result.winningCards) {
            sum += card * multiple
            multiple--
        }
        sum shouldBe 306
    }

    @Test
    fun `Part 1`() {
        val game = parseCards("part1.txt")
        val result = game.play()
        var multiple = result.winningCards.size
        var sum = 0L
        for (card in result.winningCards) {
            sum += card * multiple
            multiple--
        }
        println(sum)
    }

    @Test
    fun `Part 2 example`() {
        val game = parseCardsPart2("example.txt")
        val winningCards = if (game.play() == 1) game.player1Cards else game.player2Cards
        CombatGame.calculateScore(winningCards) shouldBe 291
    }

    @Test
    fun `Part 2 prevent infinite loop`() {
        val game = RecursiveCombatGame(listOf(43, 19), listOf(2, 29, 14))
        val result = game.play()
        result shouldBe 1
    }

    @Test
    fun `Part 2 full`() {
        val game = parseCardsPart2("part1.txt")
        val winningCards = if (game.play() == 1) game.player1Cards else game.player2Cards
        var multiple = winningCards.size
        var sum = 0L
        for (card in winningCards) {
            sum += card * multiple
            multiple--
        }
        println(sum)
    }

    private fun parseCards(fileName: String): CombatGame {
        val text = TestUtil.readText(this::class, fileName)
        val split = text.split("\n\n")
        val player1Cards = parsePlayerCards(split[0])
        val player2Cards = parsePlayerCards(split[1])
        return CombatGame(player1Cards, player2Cards)
    }

    private fun parseCardsPart2(fileName: String): RecursiveCombatGame {
        val text = TestUtil.readText(this::class, fileName)
        val split = text.split("\n\n")
        val player1Cards = parsePlayerCards(split[0])
        val player2Cards = parsePlayerCards(split[1])
        return RecursiveCombatGame(player1Cards, player2Cards)
    }

    private fun parsePlayerCards(input: String): List<Int> {
        return input.split("\n")
                .filterNot { it.startsWith("Player") }
                .map { it.toInt() }
    }
}