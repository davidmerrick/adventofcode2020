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

        /**
         * Returns the position that the current neighbor should be in relative to the new neighbor
         * i.e. if new neighbor b is a's NE neighbor, a's E neighbor should now be b's SW neighbor
         */
        fun relativePosition(newNeighbor: TileDirection, currentNeighbor: TileDirection): TileDirection {
            return if (newNeighbor == NE) {
                require(currentNeighbor in adjacent(NE))
                if (currentNeighbor == E) SE else W
            } else if (newNeighbor == NW) {
                require(currentNeighbor in adjacent(NW))
                if (currentNeighbor == NE) E else SW
            } else if (newNeighbor == W) {
                require(currentNeighbor in adjacent(W))
                if (currentNeighbor == NW) NE else SE
            } else if (newNeighbor == SW) {
                require(currentNeighbor in adjacent(SW))
                if (currentNeighbor == W) NW else E
            } else if (newNeighbor == SE) {
                require(currentNeighbor in adjacent(SE))
                if (currentNeighbor == SW) W else NE
            } else if (newNeighbor == E) {
                require(currentNeighbor in adjacent(E))
                if (currentNeighbor == SE) SW else NW
            } else {
                throw RuntimeException("Invalid directions: $newNeighbor to $currentNeighbor")
            }
        }
    }
}