package io.github.davidmerrick.aoc2020.day17

import io.github.davidmerrick.aoc2020.day17.CubeState.INACTIVE

class CubeGrid(private val input: List<List<List<CubeState>>>) {

    // Todo: May want to transform input to pad it with inactive cubes

    val activeCount: Int
        get() = input.flatMap { it.flatten() }
            .count { it == CubeState.ACTIVE }

    /**
     * Transition to next state
     */
    fun cycle(): CubeGrid {
        TODO()
    }

    private fun getAt(x: Int, y: Int, z: Int) = input[z][y][x]

    fun getNeighbors(x: Int, y: Int, z: Int): List<CubeState> {
        val neighbors = mutableListOf<CubeState>()
        for (zprime in (z - 1..z + 1)) {
            for (yprime in (y - 1..y + 1)) {
                for (xprime in (x - 1..x + 1)) {
                    val value = if (inBounds(xprime, yprime, zprime)) {
                        getAt(xprime, yprime, zprime)
                    } else INACTIVE
                    neighbors.add(value)
                }
            }
        }

        // Remove cube itself from list
        neighbors.remove(getAt(x, y, z))
        return neighbors.toList()
    }

    /**
     * Checks if value is in bounds of grid
     */
    private fun inBounds(x: Int, y: Int, z: Int): Boolean {
        return (x >= 0 && x < input[0][0].size) &&
            (y >= 0 && y < input[0].size) &&
            (z >= 0 && z < input.size)
    }
}
