package io.github.davidmerrick.aoc2020.day20

/**
 * Tracks tiles in their relative orientation to each other
 */
class TileGrid {
    val tiles
        get() = grid.toList()
    private val grid = mutableListOf<Tile>()
    private val links = mutableMapOf<Int, Map<EdgeDirection, Int>>()

    /**
     * Finds the first tile matching this tile's edge
     * Aligns tile
     * Adds to grid
     */
    fun add(tile: Tile) {
        if (grid.isEmpty()) {
            grid.add(tile)
            links.compute(tile.id) { _, _ -> emptyMap() }
        } else {
            val target = grid.first { it.countMatchedEdges(tile.edges) > 0 }
            val newTile = tile.align(target)

            // Recompute links
            recomputeLinks(newTile)

            // Finally, add to grid
            grid.add(newTile)
        }
    }

    private fun recomputeLinks(tile: Tile) {
        grid.filter { it.countMatchedEdges(tile.edges) > 0 }
                .forEach {
                    val targetEdge = it.edges.first { edge -> tile.edges.contains(edge) }
                    val targetDirection = it.getEdgeDirection(targetEdge)
                    links.compute(it.id) { _, value ->
                        val newMap = (value?.toMutableMap() ?: emptyMap()).toMutableMap()
                        newMap[targetDirection] = tile.id
                        newMap
                    }
                    links.compute(tile.id) { _, value ->
                        val newMap = (value?.toMutableMap() ?: emptyMap()).toMutableMap()
                        newMap[EdgeDirection.opposite(targetDirection)] = it.id
                        newMap
                    }
                }
    }
}