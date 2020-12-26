package io.github.davidmerrick.aoc2020.day17

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day17Part2Test {

    @Test
    fun `Parse initial cube`(){
        var grid = Day17Parser.parse4d("example.txt")
        grid.activeCount shouldBe 5

        repeat((1..6).count()) {
            grid = grid.cycle()
        }
        grid.activeCount shouldBe 848
    }

    @Test
    fun `Part 2`(){
        var grid = Day17Parser.parse4d("part1.txt")

        repeat((1..6).count()) {
            grid = grid.cycle()
        }
        println(grid.activeCount)
    }
}
