package io.github.davidmerrick.aoc2020.day20

data class Tile(
        val id: Int,
        val pixels: List<String>,
        var left: Tile? = null,
        var right: Tile? = null,
        var above: Tile? = null,
        var below: Tile? = null
) {

    /**
     * Set of 4 edges
     */
    val edges: Set<String> = setOf(
            pixels.first(), // top
            pixels.map { it.first() }.joinToString(""), // right
            pixels.map { it.last() }.joinToString(""), // left
            pixels.last() // bottom
    )

    fun edgesMatch(toMatch: Set<String>): Int {
        return edges.count { toMatch.contains(it) || toMatch.contains(it.reversed()) }
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

    companion object {
        fun parse(input: String): Tile {
            val lines = input.split("\n")
            val id = lines[0].split(" ")[1].replace(":", "").toInt()
            return Tile(id, lines.subList(1, lines.size))
        }
    }
}