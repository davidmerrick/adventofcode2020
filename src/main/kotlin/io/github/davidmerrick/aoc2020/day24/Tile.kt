package io.github.davidmerrick.aoc2020.day24

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
//        neighbors.filterKeys { it in TileDirection.adjacent(direction) }
//                .forEach {
//                    newNeighbor.addNeighbor(TileDirection.opposite(it.key), it.value)
//                    it.value.addNeighbor()
//                }

        return newNeighbor
    }
}
