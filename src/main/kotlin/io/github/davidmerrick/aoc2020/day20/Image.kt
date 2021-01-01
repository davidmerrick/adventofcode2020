package io.github.davidmerrick.aoc2020.day20

class Image(val tiles: List<Tile>) {

    private val rendered: Tile by lazy { render() }

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
    private fun render(): Tile {
        // Start a grid to place tiles
        val grid = mutableListOf<Tile>()
        val tileQueue = tiles.toMutableList()

        // Seed the grid
        val first = tileQueue.removeAt(0)
        grid.add(first)

        // Place tiles one by one into the grid
        while (tileQueue.isNotEmpty()) {
            // Seek next tile to add to grid
            for (tile in tileQueue) {
                // Check if tile can be placed in grid
                val gridEdges = grid.flatMap { it.edges }.toSet()
                val matches = tile.countMatchedEdges(gridEdges) > 0
                if (!matches) continue

                // Find target tile
                val target = grid.first { it.countMatchedEdges(tile.edges) > 0 }

                // Align target tile
                val newTile = tile.align(target)

                // Place in grid

                // Remove from queue
                tileQueue.remove(tile)

            }
        }

        // Iterate through grid, remove borders, consolidate pixels

        TODO()
    }
}