package io.github.davidmerrick.aoc2020.day24

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class HexGridTest {

    @Test
    fun `Add value`(){
        val x = 0
        val y = 0
        val grid = HexGrid()
        grid.add(false, x, y)
        grid.get(x, y) shouldBe false
    }

    @Test
    fun `Flip value`(){
        val x = 0
        val y = 0
        val grid = HexGrid()
        grid.add(true, x, y)
        grid.flip(x, y)
        grid.get(x, y) shouldBe false
    }
}