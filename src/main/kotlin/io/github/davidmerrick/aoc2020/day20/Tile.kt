package io.github.davidmerrick.aoc2020.day20

import io.github.davidmerrick.aoc2020.day20.EdgeDirection.BOTTOM
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.LEFT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.RIGHT
import io.github.davidmerrick.aoc2020.day20.EdgeDirection.TOP

private const val SEA_MONSTER_WIDTH = 20

data class Tile(
        val id: Int,
        val pixels: List<String>
) {
    val seaMonsterCount by lazy { countSeaMonsters() }

    val edges: Set<String> by lazy { edgeMap.keys.toSet() }
    private val edgeMap = mapOf(
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

    fun containsSeaMonster() = seaMonsterCount > 0

    private fun countSeaMonsters(): Int {
        var count = 0


        for (row in 0..pixels.size - 3) {
            for (column in 0..(pixels[row].length - SEA_MONSTER_WIDTH)) {
                if (containsSeaMonster(row, column)) {
                    count++
                }
            }
        }
        return count
    }

    private fun containsSeaMonster(row: Int, column: Int): Boolean {
        val seaMonsterRegexes = listOf(
                Regex("..................#."),
                Regex("#....##....##....###"),
                Regex(".#..#..#..#..#..#...")
        )
        val firstRowMatches = seaMonsterRegexes[0].matches(pixels[row].substring(column, column + SEA_MONSTER_WIDTH))
        val secondRowMatches = seaMonsterRegexes[1].matches(pixels[row + 1].substring(column, column + SEA_MONSTER_WIDTH))
        val thirdRowMatches = seaMonsterRegexes[2].matches(pixels[row + 2].substring(column, column + SEA_MONSTER_WIDTH))

        return firstRowMatches && secondRowMatches && thirdRowMatches
    }

    private fun rotateClockwise(times: Int): Tile {
        var newTile = this
        for (i in 0 until times) {
            newTile = newTile.rotateClockwise()
        }
        return newTile
    }

    fun orientToSeaMonsters(): Tile {
        return when {
            this.containsSeaMonster() -> {
                this.copy()
            }
            this.rotateClockwise().containsSeaMonster() -> {
                this.rotateClockwise()
            }
            this.rotateClockwise(2).containsSeaMonster() -> {
                this.rotateClockwise(2)
            }
            this.rotateClockwise(3).containsSeaMonster() -> {
                this.rotateClockwise(3)
            }
            this.flipHorizontal().containsSeaMonster() -> {
                this.flipHorizontal()
            }
            this.flipHorizontal().rotateClockwise().containsSeaMonster() -> {
                this.flipHorizontal().rotateClockwise()
            }
            this.flipHorizontal().rotateClockwise(2).containsSeaMonster() -> {
                this.flipHorizontal().rotateClockwise(2)
            }
            this.flipHorizontal().rotateClockwise(3).containsSeaMonster() -> {
                this.flipHorizontal().rotateClockwise(3)
            }
            else -> {
                throw RuntimeException("No orientation found")
            }
        }
    }
}