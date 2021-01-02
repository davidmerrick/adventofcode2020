package io.github.davidmerrick.aoc2020.day20

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `Rotate tile clockwise`() {
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
    fun `Flip horizontally`() {
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
        flipped.pixels.first() shouldBe ".##...##.#"
    }

    @Test
    fun `Remove borders`() {
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
        val noBorders = tile.removeBorders()
        noBorders.pixels.size shouldBe 8
        noBorders.pixels.first() shouldBe ".####..."
        noBorders.pixels.last() shouldBe ".#.#..#."
    }

    @Test
    fun `Align tiles`() {
        // Flipped and rotated version of example
        val inputA = """
            Tile 2311:
            .#####..#.
            .#.####.#.
            #..#...###
            #..##.#..#
            .##.#....#
            #.##.##...
            ....#...#.
            ....##.#.#
            #.#.###.##
            ...#.##..#
        """.trimIndent()

        val inputB = """
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

        val a = Tile.parse(inputA)
        val b = Tile.parse(inputB)

        val aligned = a.align(b)
        aligned.pixels.first() shouldBe "..##.#..#."
    }

    @Test
    fun `Contains sea monster test`() {
        val input = """
            Tile 0:
            .####...#####..#...###..
            #####..#..#.#.####..#.#.
            .#.#...#.###...#.##.##..
            #.#.##.###.#.##.##.#####
            ..##.###.####..#.####.##
            ...#.#..##.##...#..#..##
            #.##.#..#.#..#..##.#.#..
            .###.##.....#...###.#...
            #.####.#.#....##.#..#.#.
            ##...#..#....#..#...####
            ..#.##...###..#.#####..#
            ....#.##.#.#####....#...
            ..##.##.###.....#.##..#.
            #...#...###..####....##.
            .#.##...#.##.#.#.###...#
            #.###.#..####...##..#...
            #.###...#.##...#.######.
            .###.###.#######..#####.
            ..##.#..#..#.#######.###
            #.#..##.########..#..##.
            #.#####..#.#...##..#....
            #....##..#.#########..##
            #...#.....#..##...###.##
            #..###....##.#...##.##.#
        """.trimIndent()
        val tile = Tile.parse(input)
        tile.containsSeaMonsters() shouldBe true
        tile.countSeaMonsters() shouldBe 2
    }

    @Test
    fun `Contains sea monster negative test`() {
        val input = """
            Tile 0:
            .#.#..#.##...#.##..#####
            ###....#.#....#..#......
            ##.##.###.#.#..######...
            ###.#####...#.#####.#..#
            ##.#....#.##.####...#.##
            ...########.#....#####.#
            ....#..#...##..#.#.###..
            .####...#..#.....#......
            #..#.##..#..###.#.##....
            #.####..#.####.#.#.###..
            ###.#.#...#.######.#..##
            #.####....##..########.#
            ##..##.#...#...#.#.#.#..
            ...#..#..#.#.##..###.###
            .#.#....#.##.#...###.##.
            ###.#...#..#.##.######..
            .#.#.###.##.##.#..#.##..
            .####.###.#...###.#..#.#
            ..#.#..#..#.#.#.####.###
            #..####...#.#.#.###.###.
            #####..#####...###....##
            #.##..#..#...#..####...#
            .#.###..##..##..####.##.
            ...###...##...#...#..###
        """.trimIndent()
        val tile = Tile.parse(input)
        tile.containsSeaMonsters() shouldBe false
    }

    @Test
    fun `Orient to sea monsters test`() {
        val input = """
            Tile 0:
            .#.#..#.##...#.##..#####
            ###....#.#....#..#......
            ##.##.###.#.#..######...
            ###.#####...#.#####.#..#
            ##.#....#.##.####...#.##
            ...########.#....#####.#
            ....#..#...##..#.#.###..
            .####...#..#.....#......
            #..#.##..#..###.#.##....
            #.####..#.####.#.#.###..
            ###.#.#...#.######.#..##
            #.####....##..########.#
            ##..##.#...#...#.#.#.#..
            ...#..#..#.#.##..###.###
            .#.#....#.##.#...###.##.
            ###.#...#..#.##.######..
            .#.#.###.##.##.#..#.##..
            .####.###.#...###.#..#.#
            ..#.#..#..#.#.#.####.###
            #..####...#.#.#.###.###.
            #####..#####...###....##
            #.##..#..#...#..####...#
            .#.###..##..##..####.##.
            ...###...##...#...#..###
        """.trimIndent()
        val tile = Tile.parse(input)
        tile.orientToSeaMonsters().containsSeaMonsters() shouldBe true
    }
}