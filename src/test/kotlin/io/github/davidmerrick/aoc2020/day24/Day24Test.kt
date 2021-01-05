package io.github.davidmerrick.aoc2020.day24

import io.github.davidmerrick.aoc2020.day24.TileDirection.SE
import io.github.davidmerrick.aoc2020.day24.TileDirection.W
import io.github.davidmerrick.aoc2020.day24.TileDirection.valueOf
import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import java.util.Stack

class Day24Test {

    @Test
    fun `Parse input`() {
        val directions = parseInput("example.txt")
        directions.size shouldBe 20
        directions.first().first() shouldBe SE
        directions.last().last() shouldBe W
    }

    @Test
    fun `Part 1 example`() {
        val directions = simplify(parseLine("esew"))
        val referenceTile = Tile()
        var currentTile = referenceTile

        for(direction in directions){
            currentTile = currentTile.getNeighbor(direction)
        }
        currentTile.flip()

        currentTile.isWhite shouldBe false
        referenceTile.getNeighbor(SE).isWhite shouldBe false
    }

    @Test
    fun `Simplify direction test`(){
        simplify(parseLine("esew")) shouldBe listOf(SE)
    }

    /**
     * Simplify directions so we're not crossing over any tiles
     * i.e. a move NE cancels out a move SW
     */
    private fun simplify(directionList: List<TileDirection>): MutableList<TileDirection> {
        val newList = mutableListOf<TileDirection>()
        for(direction in directionList){
            if(newList.contains(TileDirection.opposite(direction))){
                newList.removeAt(newList.indexOfFirst { it == TileDirection.opposite(direction) })
            } else {
                newList.add(direction)
            }
        }

        return newList
    }

    private fun parseInput(fileName: String): List<List<TileDirection>> {
        return TestUtil.parseInput(this::class, fileName) { parseLine(it) }
    }

    private fun parseLine(line: String): List<TileDirection> {
        val stack = Stack<Char>()
        for (i in line.reversed()) {
            stack.push(i)
        }
        val directions = mutableListOf<TileDirection>()
        while (stack.isNotEmpty()) {
            val firstChar = stack.pop().toUpperCase()
            val direction = if (TileDirection.isValid(firstChar.toString())) {
                firstChar.toString()
            } else {
                firstChar.toString() + stack.pop().toUpperCase()
            }
            directions.add(valueOf(direction))
        }
        return directions
    }
}