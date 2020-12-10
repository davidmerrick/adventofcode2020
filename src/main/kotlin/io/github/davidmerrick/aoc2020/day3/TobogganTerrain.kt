package io.github.davidmerrick.aoc2020.day3

import io.github.davidmerrick.aoc2020.day3.TerrainType.TREE

private const val TREE_CHAR = '#'

data class TobogganTerrain(private val input: List<String>) {

    val height = input.size
    val width = input[0].length

    fun getElementAtCoordinate(x: Int, y: Int): TerrainType {
        val charAtCoordinate = input[y][x]
        return if(charAtCoordinate == TREE_CHAR){
            TREE
        } else TerrainType.SNOW
    }
}
