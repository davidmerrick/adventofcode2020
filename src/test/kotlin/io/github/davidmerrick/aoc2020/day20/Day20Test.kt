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
    fun `Get corner tiles`(){
        val tiles = TestUtil.readText(this::class, "example.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = TileGrid(tiles)
        val corners = image.corners
        corners.size shouldBe 4
        corners.map { it.id.toLong() }.reduce { a, b -> a * b } shouldBe 20899048083289
    }

    @Test
    fun `Part 1`(){
        val tiles = TestUtil.readText(this::class, "part1.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = TileGrid(tiles)
        val corners = image.corners
        corners.size shouldBe 4
        val result = corners.map { it.id.toLong() }.reduce { a, b -> a * b }
        println(result)
    }

    @Test
    fun `Part 2 example`(){
        val tiles = TestUtil.readText(this::class, "example.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = TileGrid(tiles)
        val rendered = image.render().flipVertical()
        rendered.pixels.first() shouldBe ".#.#..#.##...#.##..#####"
    }

    @Test
    fun `Part 2`(){
        val tiles = TestUtil.readText(this::class, "part1.txt")
                .split("\n\n").map { Tile.parse(it) }
        val image = TileGrid(tiles).render()
        val count = image.orientToSeaMonsters().countSeaMonsters()
        val seaMonsterHashes = count * 15
        val turbulence = image.pixels.joinToString("").count { it == '#' } - seaMonsterHashes
        println(turbulence)
    }
}