package io.github.davidmerrick.aoc2020.day20

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun `Parse input`(){
        val input = TestUtil.readText(this::class, "example.txt")
        val tiles = input.split("\n\n").map { Tile.parse(it) }
        tiles.size shouldBe 9
    }

    @Test
    fun `Corner detection test`(){
        val input = TestUtil.readText(this::class, "example.txt")
        val tiles = input.split("\n\n").map { Tile.parse(it) }
        val image = Image(tiles)
        val cornerTile = tiles.first { it.id == 1951 }
        cornerTile.edgesMatch(image.getOtherEdges(cornerTile.id)) shouldBe 2
    }

    @Test
    fun `Get corner tiles`(){
        val tiles = TestUtil.readText(this::class, "example.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = Image(tiles)
        val corners = image.corners
        corners.size shouldBe 4
        corners.map { it.id.toLong() }.reduce { a, b -> a * b } shouldBe 20899048083289
    }

    @Test
    fun `Part 1`(){
        val tiles = TestUtil.readText(this::class, "part1.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = Image(tiles)
        val corners = image.corners
        corners.size shouldBe 4
        val result = corners.map { it.id.toLong() }.reduce { a, b -> a * b }
        println(result)
    }

    @Test
    fun `Part 2`(){
        // Parse tiles into an image
        val tiles = TestUtil.readText(this::class, "part1.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = Image(tiles)

    }
}