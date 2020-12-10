package io.github.davidmerrick.aoc2020.day3

import io.github.davidmerrick.aoc2020.day3.TerrainType.TREE

private const val TREE_CHAR = '#'

data class TobogganTerrain(private val input: List<String>) {

    val height = input.size

    fun getElementAtCoordinate(x: Int, y: Int): TerrainType {
        // Handle overflow with a modulus
        val translatedX = x % input[0].length
        val charAtCoordinate = input[y][translatedX]
        return if(charAtCoordinate == TREE_CHAR){
            TREE
        } else TerrainType.SNOW
    }
}
