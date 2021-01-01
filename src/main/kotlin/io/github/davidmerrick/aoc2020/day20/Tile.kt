package io.github.davidmerrick.aoc2020.day20

data class Tile(val id: Int, val pixels: List<String>) {

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

    companion object {
        fun parse(input: String): Tile {
            val lines = input.split("\n")
            val id = lines[0].split(" ")[1].replace(":", "").toInt()
            return Tile(id, lines.subList(1, lines.size))
        }
    }
}