package io.github.davidmerrick.aoc2020.day24

enum class TileDirection {
    E,
    NE,
    SE,
    W,
    NW,
    SW;

    companion object {
        fun isValid(testString: String): Boolean {
            return values().map { it.toString() }.contains(testString)
        }

        fun opposite(of: TileDirection): TileDirection {
            return when (of) {
                E -> W
                NE -> SW
                SE -> NW
                W -> E
                NW -> SE
                SW -> NE
            }
        }

        fun adjacent(to: TileDirection): List<TileDirection> {
            return when (to) {
                E -> listOf(NE, SE)
                NE -> listOf(E, NW)
                SE -> listOf(E, SW)
                W -> listOf(NW, SW)
                NW -> listOf(W, NE)
                SW -> listOf(W, SE)
            }
        }
    }
}