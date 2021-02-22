package io.github.davidmerrick.aoc2020.day3

import io.github.davidmerrick.aoc2020.testutil.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class TobogganTrajectoryTest {

    @Test
    fun `Handle example 1`() {
        val deltaX = 3
        val deltaY = 1

        val terrain = parseTerrain("day3.txt")
        val treeCount = countTreesForTerrain(terrain, deltaX, deltaY)
        treeCount shouldBe 7
    }

    @Test
    fun `Handle example deltaX 1 deltaY 1`() {
        val deltaX = 1
        val deltaY = 1

        val terrain = parseTerrain("day3.txt")
        val treeCount = countTreesForTerrain(terrain, deltaX, deltaY)
        treeCount shouldBe 2
    }

    @Test
    fun `Handle example deltaX 5 deltaY 1`() {
        val deltaX = 5
        val deltaY = 1

        val terrain = parseTerrain("day3.txt")
        val treeCount = countTreesForTerrain(terrain, deltaX, deltaY)
        treeCount shouldBe 3
    }

    @Test
    fun `Handle full problem input`() {
        val deltaX = 3
        val deltaY = 1

        val terrain = parseTerrain("day3-full.txt")
        val treeCount = countTreesForTerrain(terrain, deltaX, deltaY)

        treeCount shouldBe 237
    }

    @Test
    fun `Handle part 2 example input`() {
        val terrain = parseTerrain("day3.txt")
        val results = listOf(
            countTreesForTerrain(terrain, 1, 1),
            countTreesForTerrain(terrain, 3, 1),
            countTreesForTerrain(terrain, 5, 1),
            countTreesForTerrain(terrain, 7, 1),
            countTreesForTerrain(terrain, 1, 2),
        )
        val product = results.reduce { a, b -> a * b }
        product shouldBe 336
    }

    @Test
    fun `Handle part 2 full input`() {
        val terrain = parseTerrain("day3-full.txt")
        val results = listOf(
            countTreesForTerrain(terrain, 1, 1),
            countTreesForTerrain(terrain, 3, 1),
            countTreesForTerrain(terrain, 5, 1),
            countTreesForTerrain(terrain, 7, 1),
            countTreesForTerrain(terrain, 1, 2),
        )
        val product = results.reduce { a, b -> a * b }
        product shouldBe 2_106_818_610
    }

    private fun countTreesForTerrain(
        terrain: TobogganTerrain,
        deltaX: Int,
        deltaY: Int
    ): Int {
        val path = mutableListOf<TerrainType>()
        var x = 0
        var y = 0
        while (y < terrain.height) {
            path.add(terrain.getElementAtCoordinate(x, y))
            x += deltaX
            y += deltaY
        }

        return path.count { it == TerrainType.TREE }
    }

    private fun parseTerrain(
        fileName: String
    ): TobogganTerrain {
        val input = readLines(this::class, fileName)
        return TobogganTerrain(input)
    }
}
