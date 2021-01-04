package io.github.davidmerrick.aoc2020.day22

import io.github.davidmerrick.aoc2020.day22.CombatGame.Companion.calculateScore

class RecursiveCombatGame(private val startingCards1: List<Int>, private val startingCards2: List<Int>) {
    val player1Cards: List<Int>
        get() = _player1Cards.toList()
    val player2Cards: List<Int>
        get() = _player2Cards.toList()

    private val _player1Cards = ArrayDeque(startingCards1)
    private val _player2Cards = ArrayDeque(startingCards2)
    private val rounds = mutableListOf<Pair<Long, Long>>()

    private fun reset() {
        _player1Cards.clear()
        _player1Cards.addAll(startingCards1)
        _player2Cards.clear()
        _player2Cards.addAll(startingCards2)
        rounds.clear()
    }

    fun play(): Int {
        reset()

        while (!isGameOver()) {
            if (rounds.any { it.first == calculateScore(_player1Cards) && it.second == calculateScore(_player2Cards) }) {
                return 1
            }

            rounds.add(Pair(calculateScore(_player1Cards), calculateScore(_player2Cards)))

            val card1 = _player1Cards.removeFirst()
            val card2 = _player2Cards.removeFirst()

            // Determine whether to play subgame
            val winner = if (_player1Cards.size >= card1 && _player2Cards.size >= card2) {
                RecursiveCombatGame(_player1Cards, _player2Cards).play()
            } else if (card1 > card2) {
                1
            } else {
                2
            }

            if (winner == 1) {
                _player1Cards.addLast(card1)
                _player1Cards.addLast(card2)
            } else {
                _player2Cards.addLast(card2)
                _player2Cards.addLast(card1)
            }
        }

        return if (_player1Cards.isNotEmpty()) 1 else 2
    }

    private fun isGameOver(): Boolean {
        return _player1Cards.isEmpty() || _player2Cards.isEmpty()
    }
}