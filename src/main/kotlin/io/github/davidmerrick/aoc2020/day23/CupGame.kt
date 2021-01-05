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
        val destination = findDestinationCup(currentCup)
        insertCups(destination, pickedUp)
        currentCup = getNextCup()
    }

    /**
     * Inserts cups clockwise of destination cup
     */
    private fun insertCups(destination: Int, toInsert: List<Int>) {
        var destinationIndex = getNextIndex(_cups.indexOf(destination))
        for (cup in toInsert) {
            _cups.add(destinationIndex, cup)
            destinationIndex = getNextIndex(destinationIndex)
        }
    }

    /**
     * Gets the next value of the current cup
     */
    private fun getNextCup(): Int {
        return _cups[getNextIndex(_cups.indexOf(currentCup))]
    }

    private fun getNextIndex(index: Int): Int {
        return (index + 1) % _cups.size
    }

    /**
     * Note: Assumes you've removed cups first
     */
    private fun findDestinationCup(currentCup: Int): Int {
        var i = currentCup - 1
        while (i > 0) {
            if (_cups.contains(i)) return i
            i--
        }
        return _cups.maxOrNull()!!
    }

    private fun getClockwiseCups(startingIndex: Int, numCups: Int): List<Int> {
        val subList = mutableListOf<Int>()
        var index = getNextIndex(startingIndex)
        while (subList.size < numCups) {
            subList.add(_cups[index])
            index = getNextIndex(index)
        }
        return subList
    }
}