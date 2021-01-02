package io.github.davidmerrick.aoc2020.day20

class Image(private val tiles: List<Tile>) {

//    private val rendered: Tile by lazy { render() }

    val corners: Set<Tile>
        get() = tiles.filter { it.countMatchedEdges(getOtherEdges(it.id)) == 2 }.toSet()

    private fun getOtherEdges(excludeTile: Int) = getOtherEdges(tiles, excludeTile)

    private fun getOtherEdges(tiles: List<Tile>, excludeTile: Int): Set<String> {
        return tiles.filterNot { it.id == excludeTile }
                .flatMap { it.edges }
                .toSet()
    }

    /**
     * Render the full image into a single, large tile
     */
    fun render() {
        // Start a grid to place tiles
        // This guarantees that they're aligned
        val grid = TileGrid()
        val tileQueue = tiles.toMutableList()

        // Seed the grid
        val first = tileQueue.removeAt(0)
        grid.add(first)

        // Place tiles one by one into the grid
        var i = 0
        while (tileQueue.isNotEmpty()) {
            // Seek next tile to add to grid
            i %= tileQueue.size
            val tile = tileQueue[i]

            // Check if tile can be placed in grid
            val matches = grid.tiles.any { it.countMatchedEdges(tile.edges) > 0 }
            if (matches) {
                grid.add(tile)
                tileQueue.remove(tile)
            }
            i++
        }

        // Iterate through grid, remove borders, consolidate pixels

    }
}