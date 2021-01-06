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

    val tiles: List<Pair<Int, Int>>
        get() = _tiles.flatMap { entry -> entry.value.keys.map { Pair(entry.key, it) } }

    val blackTiles: List<Boolean>
        get() = _tiles.values.flatMap { it.values }.filterNot { it }

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

    fun get(x: Int, y: Int): Boolean {
        if (!tileIsSet(x, y)) {
            add(true, x, y)
        }
        return _tiles[x]!![y]!!
    }

    /**
     * Returns coordinates of neighboring tiles
     */
    fun getNeighbors(x: Int, y: Int): List<Pair<Int, Int>> {
        return TileDirection.values().map {
            val delta = move(it)
            Pair(x + delta.first, y + delta.second)
        }
    }

    fun shouldFlip(x: Int, y: Int): Boolean {
        val self = get(x, y)
        val adjacentBlackTiles = getNeighbors(x, y)
                .map { get(it.first, it.second) }
                .filter { !it }
        if (!self && (adjacentBlackTiles.isEmpty() || adjacentBlackTiles.size > 2)) {
            return true
        }
        if (self && adjacentBlackTiles.size == 2) {
            return true
        }
        return false
    }

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