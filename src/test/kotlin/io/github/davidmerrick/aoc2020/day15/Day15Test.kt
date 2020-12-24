package io.github.davidmerrick.aoc2020.day15

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun `Example input`(){
        val input = listOf(0, 3, 6)
        Day15Solver.solve(input) shouldBe 436
    }

    @Test
    fun `Example input 2`(){
        val input = listOf(1, 3, 2)
        Day15Solver.solve(input) shouldBe 1
    }

    @Test
    fun `Example input 3`(){
        val input = listOf(2, 1, 3)
        Day15Solver.solve(input) shouldBe 10
    }

    @Test
    fun `Example input part 2`(){
        val input = listOf(2, 1, 3)
        Day15Solver.solve(input, 30000000) shouldBe 175594
    }

    @Test
    fun `Part 1 full input`(){
        val input = listOf(0, 20, 7, 16, 1, 18, 15)
        println(Day15Solver.solve(input))
    }


}
