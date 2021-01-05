package io.github.davidmerrick.aoc2020.day24

import io.github.davidmerrick.aoc2020.day24.TileDirection.*
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `Flip tile`() {
        val tile = Tile()
        tile.isWhite shouldBe true
        tile.flip()
        tile.isWhite shouldBe false
        tile.flip()
        tile.isWhite shouldBe true
    }

    @Test
    fun `Adding a neighboring tile points back to original tile`() {
        val a = Tile()
        val b = a.addNeighbor(NE)
        a.getNeighbor(NE) shouldBe b
        b.getNeighbor(SW) shouldBe a
    }

    @Test
    fun `Adding a neighbor should update adjacent tiles`() {
        val a = Tile()
        val b = a.addNeighbor(NE)
        val c = b.addNeighbor(W)

        // c should now be a's NW neighbor
        a.getNeighbor(NW) shouldBe c
        c.getNeighbor(SE) shouldBe a
    }
}