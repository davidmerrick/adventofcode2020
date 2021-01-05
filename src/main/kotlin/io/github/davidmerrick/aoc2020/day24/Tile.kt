package io.github.davidmerrick.aoc2020.day24

import io.github.davidmerrick.aoc2020.day24.TileDirection.Companion.adjacent
import io.github.davidmerrick.aoc2020.day24.TileDirection.Companion.relativePosition

data class Tile(
        val neighbors: MutableMap<TileDirection, Tile> = mutableMapOf(),
        private var _isWhite: Boolean = true
) {

    val isWhite: Boolean
        get() = _isWhite

    fun flip() {
        _isWhite = !_isWhite
    }

    /**
     * Gets a tile's neighbor by direction.
     * If neighbor doesn't exist, this initializes it.
     */
    fun getNeighbor(direction: TileDirection): Tile {
        return neighbors[direction] ?: addNeighbor(direction)
    }

    fun addNeighbor(direction: TileDirection, newNeighbor: Tile = Tile()): Tile {
        // Add this tile as its neighbor
        neighbors[direction] = newNeighbor
        newNeighbor.neighbors[TileDirection.opposite(direction)] = this

        // Add adjacent tiles as neighbors
        neighbors.filterKeys { it in adjacent(direction) }
                .mapNotNull { it }
                .forEach {
                    val neighborDirection = relativePosition(direction, it.key)
                    newNeighbor.neighbors[neighborDirection] = it.value
                    it.value.neighbors[TileDirection.opposite(neighborDirection)] = newNeighbor
                }

        return newNeighbor
    }

    override fun toString(): String {
        return isWhite.toString()
    }
}
