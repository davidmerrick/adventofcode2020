package io.github.davidmerrick.aoc2020.day20

import io.github.davidmerrick.aoc2020.day20.EdgeDirection.BOTTOM
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.LEFT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.RIGHT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.TOP

/**
 * Tracks tiles in their relative orientation to each other
 */
class TileGrid(input: List<Tile>) {
    val corners by lazy {
        links.filter { it.value.size == 2 }
                .map { entry -> tiles.first { it.id == entry.key } }
    }

    private val tiles
        get() = grid.toList()
    private val grid = mutableListOf<Tile>()
    private val links = mutableMapOf<Int, Map<EdgeDirection, Int>>()

    init {
        // Start a grid to place tiles
        // This guarantees that they're aligned
        val tileQueue = input.toMutableList()

        // Seed the grid
        val first = tileQueue.removeAt(0)
        add(first)

        // Place tiles one by one into the grid
        var i = 0
        while (tileQueue.isNotEmpty()) {
            // Seek next tile to add to grid
            i %= tileQueue.size
            val tile = tileQueue[i]

            // Check if tile can be placed in grid
            val matches = tiles.any { it.countMatchedEdges(tile.edges) > 0 }
            if (matches) {
                add(tile)
                tileQueue.remove(tile)
            }
            i++
        }
    }

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

    private fun toList(): List<List<Tile>> {
        val gridList: MutableList<List<Tile>> = mutableListOf()

        // Find upper-left tile
        var rowStart = links.asSequence().first { !it.value.containsKey(TOP) && !it.value.containsKey(LEFT) }
        gridList.add(getRow(rowStart.key))

        // Walk rows
        while (rowStart.value.containsKey(BOTTOM)) {
            rowStart = links.asSequence().first { it.key == rowStart.value[BOTTOM]!! }
            gridList.add(getRow(rowStart.key))
        }

        return gridList.toList()
    }

    /**
     * Pass in id that's start of row.
     * This is janky af, I realize. Will likely refactor later into an iterator.
     */
    private fun getRow(id: Int): List<Tile> {
        val row = mutableListOf<Tile>()
        var currentLink = links.asSequence().first { it.key == id }
        row.add(grid.first { it.id == currentLink.key })

        while (currentLink.value.containsKey(RIGHT)) {
            currentLink = links.asSequence().first { it.key == currentLink.value[RIGHT]!! }
            row.add(grid.first { it.id == currentLink.key })
        }
        return row
    }

    fun combineTileRow(tileList: List<Tile>): List<String> {
        return tileList.map { it.removeBorders() }
                .reduce { a, b ->
                    Tile(0, a.pixels.mapIndexed { i, value -> value + b.pixels[i] })
                }.pixels
    }

    fun render(): Tile {
        val gridList = toList()
        val pixels = gridList.flatMap { combineTileRow(it) }
        return Tile(0, pixels)
    }
}