package io.github.davidmerrick.aoc2020.day22

class RecursiveCombatGame(startingCards1: List<Int>, startingCards2: List<Int>) {
    val player1Cards: List<Int>
        get() = _player1Cards.toList()
    val player2Cards: List<Int>
        get() = _player2Cards.toList()

    private val _player1Cards = ArrayDeque(startingCards1)
    private val _player2Cards = ArrayDeque(startingCards2)
    private val rounds = mutableSetOf<List<Int>>()

    private val result: Int by lazy { computeWinner() }

    fun play() = result

    private fun computeWinner(): Int {
        while (!isGameOver()) {
            // Prevent infinite games
            if (rounds.contains(_player1Cards.toList())) {
                return 1
            } else if (_player1Cards.maxOrNull()!! > _player2Cards.maxOrNull()!!) {
                return 1
            }

            playRound()
        }

        return if (_player1Cards.isNotEmpty()) 1 else 2
    }

    private fun playRound() {
        rounds.add(_player1Cards.toList())

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

    private fun isGameOver(): Boolean {
        return _player1Cards.isEmpty() || _player2Cards.isEmpty()
    }
}