package io.github.davidmerrick.aoc2020.day23

class CupGame(private val startingCups: List<Int>) {

    private val cups = CircularLookupList()

    init {
        cups.add(startingCups.first())
        var previousCup = startingCups.first()
        for (cup in startingCups.subList(1, startingCups.size)) {
            cups.addAfter(previousCup, cup)
            previousCup = cup
        }
    }

    /**
     * Cups, in clockwise order from 1
     */
    fun getResult(numItems: Int = startingCups.size): List<Int> {
        val cupsList = mutableListOf<Int>()
        var cup = 1
        for (i in 0 until numItems) {
            cup = cups.getNext(cup)
            cupsList.add(cup)
        }
        return cupsList.filterNot { it == 1 }
    }

    private var currentCup: Int = startingCups.first()

    private fun reset() {
        currentCup = startingCups.first()
    }

    fun play(numRounds: Int) {
        reset()

        for (i in 0 until numRounds) {
            playRound()

            if (i % 100_000 == 0) {
                println("Played $i rounds")
            }
        }
    }

    private fun playRound() {
        val pickedUp = pickupCups()
        val destination = findDestinationCup(currentCup)
        insertCups(destination, pickedUp)
        currentCup = cups.getNext(currentCup)
    }

    /**
     * Picks next 3 clockwise cups out, removes them from cups array, then returns them
     */
    private fun pickupCups(): List<Int> {
        val pickedUp = getClockwiseCups(3)
        pickedUp.forEach { cups.remove(it) }
        return pickedUp
    }

    /**
     * Inserts cups clockwise of destination cup
     */
    private fun insertCups(destination: Int, toInsert: List<Int>) {
        var addAfter = destination
        for (cup in toInsert) {
            cups.addAfter(addAfter, cup)
            addAfter = cup
        }
    }

    /**
     * Note: Assumes you've removed cups first
     */
    private fun findDestinationCup(currentCup: Int): Int {
        var i = currentCup - 1
        while (i > 0) {
            if (cups.contains(i)) return i
            i--
        }
        return cups.max()
    }

    private fun getClockwiseCups(numCups: Int): List<Int> {
        val subList = mutableListOf<Int>()
        var cup = cups.getNext(currentCup)
        while (subList.size < numCups) {
            subList.add(cup)
            cup = cups.getNext(cup)
        }
        return subList
    }
}