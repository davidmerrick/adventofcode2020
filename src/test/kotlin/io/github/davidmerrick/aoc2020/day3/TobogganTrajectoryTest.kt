package io.github.davidmerrick.aoc2020.day3

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import kotlin.math.ceil

class TobogganTrajectoryTest {

    @Test
    fun `Handle example 1`(){
        val deltaX = 3
        val deltaY = 1

        val treeCount = countTreesForTerrain("day3.txt", deltaX, deltaY)
        treeCount shouldBe 7
    }

    @Test
    fun `Handle example deltaX 1 deltaY 1`(){
        val deltaX = 1
        val deltaY = 1

        val treeCount = countTreesForTerrain("day3.txt", deltaX, deltaY)
        treeCount shouldBe 2
    }

    @Test
    fun `Handle example deltaX 5 deltaY 1`(){
        val deltaX = 5
        val deltaY = 1

        val treeCount = countTreesForTerrain("day3.txt", deltaX, deltaY)
        treeCount shouldBe 3
    }

    @Test
    fun `Handle full problem input`(){
        val deltaX = 3
        val deltaY = 1

        val treeCount = countTreesForTerrain("day3-full.txt", deltaX, deltaY)

        treeCount shouldBe 237
    }

    @Test
    fun `Handle part 2 example input`(){
        val fileName = "day3.txt"
        val results = listOf(
            countTreesForTerrain(fileName, 1, 1),
            countTreesForTerrain(fileName, 3, 1),
            countTreesForTerrain(fileName, 5, 1),
            countTreesForTerrain(fileName, 7, 1),
            countTreesForTerrain(fileName, 1, 2),
        )
        val product = results.reduce { a, b -> a * b }
        product shouldBe 336
    }

    @Test
    fun `Handle part 2 full input`(){
        val fileName = "day3-full.txt"
        val results = listOf(
            countTreesForTerrain(fileName, 1, 1),
            countTreesForTerrain(fileName, 3, 1),
            countTreesForTerrain(fileName, 5, 1),
            countTreesForTerrain(fileName, 7, 1),
            countTreesForTerrain(fileName, 1, 2),
        )
        val product = results.reduce { a, b -> a * b }
        product shouldBe 2_106_818_610
    }

    private fun countTreesForTerrain(
        terrainFileName: String,
        deltaX: Int,
        deltaY: Int
    ): Int {
        val terrain = parseTerrain(
            terrainFileName,
            deltaX,
            deltaY
        )
        val path = mutableListOf<TerrainType>()
        var x = 0
        var y = 0
        while (y < terrain.height){
            path.add(terrain.getElementAtCoordinate(x, y))
            x += deltaX
            y += deltaY
        }

        return path.count { it == TerrainType.TREE }
    }

    private fun parseTerrain(
        fileName: String,
        deltaX: Int,
        deltaY: Int
    ): TobogganTerrain {
        val input = readLines(fileName)
        val numRepeats = ((input.size/deltaY)*deltaX)/input[0].length
        val repeated = (0 .. numRepeats)
            .map { input.toList() }
            .reduce { listA, listB ->
                val newList = mutableListOf<String>()
                for(i in listA.indices){
                    newList.add(i, listA[i] + listB[i])
                }
                newList.toList()
            }
        return TobogganTerrain(repeated)
    }

    private fun readLines(fileName: String): List<String> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
    }
}
