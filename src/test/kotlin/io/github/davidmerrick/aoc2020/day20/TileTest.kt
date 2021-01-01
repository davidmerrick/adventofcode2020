package io.github.davidmerrick.aoc2020.day20

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `Rotate tile clockwise`(){
        val input = """
            Tile 1951:
            #.##...##.
            #.####...#
            .....#..##
            #...######
            .##.#....#
            .###.#####
            ###.##.##.
            .###....#.
            ..#.#..#.#
            #...##.#..
        """.trimIndent()
        val tile = Tile.parse(input)
        val rotated = tile.rotateClockwise()
        rotated.pixels[0] shouldBe "#..#..#.##"
        rotated.pixels[1] shouldBe "..####...."
        rotated.pixels.last() shouldBe ".#..#####."
    }

    @Test
    fun `Flip horizontally`(){
        val input = """
            Tile 1951:
            #.##...##.
            #.####...#
            .....#..##
            #...######
            .##.#....#
            .###.#####
            ###.##.##.
            .###....#.
            ..#.#..#.#
            #...##.#..
        """.trimIndent()
        val tile = Tile.parse(input)
        val flipped = tile.flipHorizontal()
        flipped.pixels[0] shouldBe ".##...##.#"
    }
}