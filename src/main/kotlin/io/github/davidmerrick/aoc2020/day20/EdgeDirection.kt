package io.github.davidmerrick.aoc2020.day20

enum class EdgeDirection {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT;

    companion object {
        fun opposite(of: EdgeDirection) = when (of) {
            TOP -> BOTTOM
            LEFT -> RIGHT
            BOTTOM -> TOP
            RIGHT -> LEFT
        }
    }
}