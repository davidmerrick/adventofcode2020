package io.github.davidmerrick.aoc2020.day24

import io.github.davidmerrick.aoc2020.day24.TileDirection.*
import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import java.util.Stack

class Day24Test {

    @Test
    fun `Parse input`() {
        val directions = parseInput("example.txt")
        directions.size shouldBe 367
        directions.first() shouldBe SE
        directions.last() shouldBe W
    }

    private fun parseInput(fileName: String): MutableList<TileDirection> {
        val input = TestUtil.readLines(this::class, fileName)
                .joinToString("")
        val stack = Stack<Char>()
        for (i in input.reversed()) {
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