package io.github.davidmerrick.aoc2020.day24

data class Tile(private val neighbors: MutableMap<TileDirection, Tile>, private var _isWhite: Boolean = true) {

    val isWhite: Boolean
        get() = _isWhite

    fun visit() {
        _isWhite = !_isWhite
    }

    /**
     * Gets a tile's neighbor by direction.
     * If neighbor doesn't exist, this initializes it.
     */
    fun getNeighbor(direction: TileDirection): Tile {
        return neighbors[direction] ?: addNeighbor(direction)
    }

    private fun addNeighbor(direction: TileDirection): Tile {
        TODO()
    }
}
