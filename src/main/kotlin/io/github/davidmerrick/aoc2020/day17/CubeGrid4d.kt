package io.github.davidmerrick.aoc2020.day17

import io.github.davidmerrick.aoc2020.day17.CubeState.ACTIVE
import io.github.davidmerrick.aoc2020.day17.CubeState.INACTIVE

class CubeGrid4d(private val input: List<List<List<List<CubeState>>>>) {

    val activeCount: Int
        get() = input.flatten().flatten().flatten()
            .count { it == ACTIVE }

    /**
     * Transition to next state
     */
    fun cycle(): CubeGrid4d {
        // Check every cube's neighbors and transition them
        val newInput = mutableListOf<List<List<List<CubeState>>>>()
        for (w in -1..input.size) {
            val newZ = mutableListOf<List<List<CubeState>>>()
            for (z in -1..input[0].size) {
                val newY = mutableListOf<List<CubeState>>()
                for (y in -1..input[0][0].size) {
                    val newX = mutableListOf<CubeState>()
                    for (x in -1..input[0][0][0].size) {
                        newX.add(nextState(x, y, z, w))
                    }
                    newY.add(newX)
                }
                newZ.add(newY)
            }
            newInput.add(newZ)
        }

        // Return a new grid with the transformed input
        return CubeGrid4d(newInput.toList())
    }

    private fun nextState(x: Int, y: Int, z: Int, w: Int): CubeState {
        val neighbors = getNeighbors(x, y, z, w)
        val currentState = getAt(x, y, z, w)
        return if (currentState == ACTIVE) {
            if (neighbors.count { it == ACTIVE } in 2..3) {
                ACTIVE
            } else INACTIVE
        } else {
            if (neighbors.count { it == ACTIVE } == 3) {
                ACTIVE
            } else INACTIVE
        }

    }

    private fun getAt(x: Int, y: Int, z: Int, w: Int) = if (inBounds(x, y, z, w)) {
        input[w][z][y][x]
    } else INACTIVE

    private fun getNeighbors(x: Int, y: Int, z: Int, w: Int): List<CubeState> {
        val neighbors = mutableListOf<CubeState>()
        walkInput(
            (x - 1..x + 1).toList(),
            (y - 1..y + 1).toList(),
            (z - 1..z + 1).toList(),
            (w - 1..w + 1).toList(),
        ) { xPrime, yPrime, zPrime, wPrime ->
            val value = if (inBounds(xPrime, yPrime, zPrime, wPrime)) {
                getAt(xPrime, yPrime, zPrime, wPrime)
            } else INACTIVE
            neighbors.add(value)
        }

        // Remove cube itself from list
        neighbors.remove(getAt(x, y, z, w))
        return neighbors.toList()
    }

    private fun walkInput(
        xRange: List<Int>,
        yRange: List<Int>,
        zRange: List<Int>,
        wRange: List<Int>,
        action: (Int, Int, Int, Int) -> Unit
    ) {
        for (w in wRange) {
            for (z in zRange) {
                for (y in yRange) {
                    for (x in xRange) {
                        action(x, y, z, w)
                    }
                }
            }
        }
    }

    /**
     * Checks if value is in bounds of grid
     */
    private fun inBounds(x: Int, y: Int, z: Int, w: Int): Boolean {
        return (x >= 0 && x < input[0][0][0].size) &&
            (y >= 0 && y < input[0][0].size) &&
            (z >= 0 && z < input[0].size) &&
            (w >= 0 && w < input.size)
    }
}
