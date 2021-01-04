package io.github.davidmerrick.aoc2020.day22

class CombatGame(val startingCards1: List<Int>, val startingCards2: List<Int>) {

    private val player1Cards = ArrayDeque(startingCards1)
    private val player2Cards = ArrayDeque(startingCards2)

    private fun reset() {
        player1Cards.clear()
        player1Cards.addAll(startingCards1)
        player2Cards.clear()
        player2Cards.addAll(startingCards2)
    }

    fun play(): GameResult {
        reset()

        var numRounds = 0
        while (!isGameOver()) {
            numRounds++
            playRound()
        }

        val winningCards = if (player1Cards.isNotEmpty()) {
            player1Cards.toList()
        } else {
            player2Cards.toList()
        }

        return GameResult(winningCards, numRounds)
    }

    private fun isGameOver(): Boolean {
        return player1Cards.isEmpty() || player2Cards.isEmpty()
    }

    private fun playRound() {
        val card1 = player1Cards.removeFirst()
        val card2 = player2Cards.removeFirst()

        if (card1 > card2) {
            player1Cards.addLast(card1)
            player1Cards.addLast(card2)
        } else {
            player2Cards.addLast(card2)
            player2Cards.addLast(card1)
        }
    }

    companion object {
        fun calculateScore(cards: List<Int>) = cards.reversed()
                .mapIndexed { i, value -> value.toLong() * (i + 1) }
                .sum()
    }

}