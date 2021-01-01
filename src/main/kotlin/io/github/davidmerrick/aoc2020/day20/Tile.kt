package io.github.davidmerrick.aoc2020.day20

import io.github.davidmerrick.aoc2020.day20.EdgeDirection.BOTTOM
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.LEFT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.RIGHT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.TOP

data class Tile(
        val id: Int,
        val pixels: List<String>,
        val left: Tile? = null,
        val right: Tile? = null,
        val above: Tile? = null,
        val below: Tile? = null
) {
    val edges: Set<String> by lazy { edgeMap.keys.toSet() }
    val edgeMap = mapOf(
            pixels.first() to TOP,
            pixels.map { it.first() }.joinToString("") to LEFT,
            pixels.map { it.last() }.joinToString("") to RIGHT,
            pixels.last() to BOTTOM
    )

    fun countMatchedEdges(toMatch: Set<String>) = edges
            .count { toMatch.contains(it) || toMatch.contains(it.reversed()) }

    fun removeBorders(): Tile {
        return this.copy(
                pixels = pixels.subList(1, pixels.size - 1).map { it.substring(1, it.length - 1) }
        )
    }

    fun flipVertical(): Tile {
        return this.flipHorizontal()
                .rotateClockwise()
                .rotateClockwise()
    }

    fun flipHorizontal() = this.copy(pixels = pixels.map { it.reversed() }.toList())
    fun rotateClockwise(): Tile {
        val newPixels = mutableListOf<String>()
        for (column in 0 until pixels[0].length) {
            val newRow = buildString {
                for (row in pixels.indices) {
                    this.append(pixels[row][column])
                }
            }
            newPixels.add(newRow.reversed())
        }
        return this.copy(pixels = newPixels.toList())
    }

    /**
     * Rotates and flips this tile until aligned with target
     */
    fun align(target: Tile): Tile {
        // Find which edge to match
        val targetEdge = edges
                .flatMap { listOf(it, it.reversed()) } // Ignore edge direction for now
                .first { target.edges.contains(it) }
        val targetDirection = target.edgeMap[targetEdge]!!

        // Rotate until edge is at opposite position of target
        var newTile = this.copy()
        while (newTile.getEdgeDirection(targetEdge) != EdgeDirection.opposite(targetDirection)) {
            newTile = newTile.rotateClockwise()
        }

        // Flip if necessary. Remember that this edge was normalized
        val shouldFlip = target.edges.intersect(newTile.edges).isEmpty()
        if (shouldFlip) {
            newTile = when (targetDirection) {
                TOP, BOTTOM -> newTile.flipHorizontal()
                LEFT, RIGHT -> newTile.flipVertical()
            }
        }

        return newTile
    }

    /**
     * Gets direction of edge, ignoring whether string is reversed
     */
    fun getEdgeDirection(edge: String): EdgeDirection {
        val normalized = edges.first { it == edge || it.reversed() == edge }
        return edgeMap[normalized]!!
    }

    companion object {
        fun parse(input: String): Tile {
            val lines = input.split("\n")
            val id = lines[0].split(" ")[1].replace(":", "").toInt()
            return Tile(id, lines.subList(1, lines.size))
        }
    }
}