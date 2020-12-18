package io.github.davidmerrick.aoc2020.day12

import io.github.davidmerrick.aoc2020.day12.ShipDirection.EAST
import io.github.davidmerrick.aoc2020.day12.ShipDirection.NORTH
import io.github.davidmerrick.aoc2020.day12.ShipDirection.SOUTH
import io.github.davidmerrick.aoc2020.day12.ShipDirection.WEST
import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `Parse input`(){
        val instructions = parseInput("day12.txt")
        instructions.size shouldBe 5
    }

    @Test
    fun `Test ship turns`(){
        val ship = Ship(EAST)
        ship.handle(NavInstruction(NavAction.L, 90))
        ship.direction shouldBe NORTH
        ship.handle(NavInstruction(NavAction.R, 180))
        ship.direction shouldBe SOUTH
        ship.handle(NavInstruction(NavAction.R, 360))
        ship.direction shouldBe SOUTH
        ship.handle(NavInstruction(NavAction.R, 450))
        ship.direction shouldBe WEST
        ship.handle(NavInstruction(NavAction.L, 450))
        ship.direction shouldBe SOUTH
    }

    @Test
    fun `Test input`(){
        val ship = Ship(EAST)
        parseInput("day12.txt")
            .forEach { ship.handle(it) }
        ship.distance shouldBe 25
    }

    @Test
    fun `Part 1 input`(){
        val ship = Ship(EAST)
        parseInput("day12-full.txt")
            .forEach { ship.handle(it) }
        println(ship.distance)
    }

    private fun parseInput(fileName: String): List<NavInstruction> {
        return TestUtil.parseInput(this::class, fileName){
            NavInstruction(
                NavAction.valueOf(it.substring(0,1)),
                it.substring(1, it.length).toInt()
            )
        }
    }
}
