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
    }
}