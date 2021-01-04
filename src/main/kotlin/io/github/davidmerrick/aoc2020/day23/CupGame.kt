package io.github.davidmerrick.aoc2020.day23

class CupGame(private val startingCups: List<Int>) {

    /**
     * Cups, in clockwise order from 1
     */
    val result: List<Int>
        get() = getClockwiseCups(_cups.indexOf(1), _cups.size).filterNot { it == 1 }

    private var _cups = startingCups.toMutableList()
    private var currentCup: Int = _cups[0]

    private fun reset() {
        _cups = startingCups.toMutableList()
        currentCup = _cups[0]
    }

    fun play(numRounds: Int) {
        reset()

        repeat((0 until numRounds).count()) { playRound() }
    }

    private fun playRound() {
        val pickedUp = getClockwiseCups(_cups.indexOf(currentCup), 3)
        _cups.removeIf { pickedUp.contains(it) }
        val destination = findDestinationCup(currentCup, _cups.toList())
        insertCups(destination, pickedUp)
        currentCup = getNextCup()
    }

    /**
     * Inserts cups clockwise of destination cup
     */
    private fun insertCups(destination: Int, toInsert: List<Int>) {
        val newCups = mutableListOf<Int>()
        newCups.addAll(_cups.subList(0, _cups.indexOf(destination) + 1))
        newCups.addAll(toInsert)
        newCups.addAll(_cups.subList(_cups.indexOf(destination) + 1, _cups.size))
        _cups = newCups
    }

    /**
     * Gets the next value of the current cup
     */
    private fun getNextCup(): Int {
        val index = (_cups.indexOf(currentCup) + 1) % _cups.size
        return _cups[index]
    }

    private fun findDestinationCup(currentCup: Int, cupsList: List<Int>): Int {
        var i = currentCup - 1
        while (i > 0) {
            if (cupsList.contains(i)) return i
            i--
        }
        return cupsList.maxOrNull()!!
    }

    private fun getClockwiseCups(startingIndex: Int, numCups: Int): List<Int> {
        val subList = mutableListOf<Int>()
        var index = (startingIndex + 1) % _cups.size
        while (subList.size < numCups) {
            subList.add(_cups[index])
            index = (index + 1) % _cups.size
        }
        return subList
    }
}