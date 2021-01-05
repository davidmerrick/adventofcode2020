package io.github.davidmerrick.aoc2020.day24

import io.github.davidmerrick.aoc2020.day24.TileDirection.E
import io.github.davidmerrick.aoc2020.day24.TileDirection.NE
import io.github.davidmerrick.aoc2020.day24.TileDirection.NW
import io.github.davidmerrick.aoc2020.day24.TileDirection.SE
import io.github.davidmerrick.aoc2020.day24.TileDirection.SW
import io.github.davidmerrick.aoc2020.day24.TileDirection.W

/**
 * True represents white, false black
 */
class HexGrid {

    val tiles: List<Boolean>
        get() = _tiles.values.flatMap { it.values }

    /**
     * Coordinates are combinations of east and north
     * See docs/img/hexagonal_grid.png
     */
    private val _tiles: MutableMap<Int, MutableMap<Int, Boolean>> = mutableMapOf(0 to mutableMapOf(0 to true))

    fun add(value: Boolean, x: Int, y: Int) {
        if (tileIsSet(x, y)) {
            return
        }
        if (!_tiles.containsKey(x)) {
            _tiles[x] = mutableMapOf(y to value)
            return
        }
        _tiles[x]!![y] = value
    }

    private fun tileIsSet(x: Int, y: Int): Boolean {
        return _tiles.containsKey(x) && _tiles[x]!!.containsKey(y)
    }

    fun get(x: Int, y: Int) = _tiles[x]!![y]!!

    fun flip(x: Int, y: Int) {
        if (!tileIsSet(x, y)) {
            add(true, x, y)
        }
        _tiles[x]!!.compute(y) { _, value -> !(value!!) }
    }

    companion object {
        /**
         * Returns pair of delta x, y for moving in a given direction
         */
        fun move(direction: TileDirection): Pair<Int, Int> {
            return when (direction) {
                E -> Pair(1, 0)
                NE -> Pair(0, 1)
                SE -> Pair(1, -1)
                W -> Pair(-1, 0)
                NW -> Pair(-1, 1)
                SW -> Pair(0, -1)
            }
        }
    }
}