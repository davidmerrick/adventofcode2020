package io.github.davidmerrick.aoc2020.day17

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day17Test {

    @Test
    fun `Parse initial cube`(){
        var grid = Day17Parser.parse("example.txt")
        grid.activeCount shouldBe 5

        var neighbors = grid.getNeighbors(0,0,0)
        neighbors.size shouldBe 26
        neighbors.count { it == CubeState.ACTIVE } shouldBe 1

        neighbors = grid.getNeighbors(1,2,0)
        neighbors.size shouldBe 26
        neighbors.count { it == CubeState.ACTIVE } shouldBe 3

        repeat((1..6).count()) {
            grid = grid.cycle()
        }
        grid.activeCount shouldBe 112
    }

    @Test
    fun `Part 1`(){
        var grid = Day17Parser.parse("part1.txt")

        repeat((1..6).count()) {
            grid = grid.cycle()
        }
        println(grid.activeCount)
    }
}
