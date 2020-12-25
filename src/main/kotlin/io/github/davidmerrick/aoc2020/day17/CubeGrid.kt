package io.github.davidmerrick.aoc2020.day17

import io.github.davidmerrick.aoc2020.day17.CubeState.ACTIVE
import io.github.davidmerrick.aoc2020.day17.CubeState.INACTIVE

class CubeGrid(private val input: List<List<List<CubeState>>>) {

    val activeCount: Int
        get() = input.flatten().flatten()
            .count { it == ACTIVE }

    /**
     * Transition to next state
     */
    fun cycle(): CubeGrid {
        // Check every cube's neighbors and transition them
        val newInput: MutableList<List<List<CubeState>>> = mutableListOf()
        for (z in -1..input.size) {
            val newY = mutableListOf<List<CubeState>>()
            for (y in -1..input[0].size) {
                val newX = mutableListOf<CubeState>()
                for (x in -1..input[0][0].size) {
                    newX.add(nextState(x, y, z))
                }
                newY.add(newX)
            }
            newInput.add(newY)
        }

        // Return a new grid with the transformed input
        return CubeGrid(newInput.toList())
    }

    private fun nextState(x: Int, y: Int, z: Int): CubeState {
        val neighbors = getNeighbors(x, y, z)
        val currentState = getAt(x, y, z)
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

    private fun getAt(x: Int, y: Int, z: Int) = if (inBounds(x, y, z)) {
        input[z][y][x]
    } else INACTIVE

    fun getNeighbors(x: Int, y: Int, z: Int): List<CubeState> {
        val neighbors = mutableListOf<CubeState>()
        walkInput(
            (x - 1..x + 1).toList(),
            (y - 1..y + 1).toList(),
            (z - 1..z + 1).toList()
        ) { xPrime, yPrime, zPrime ->
            val value = if (inBounds(xPrime, yPrime, zPrime)) {
                getAt(xPrime, yPrime, zPrime)
            } else INACTIVE
            neighbors.add(value)
        }

        // Remove cube itself from list
        neighbors.remove(getAt(x, y, z))
        return neighbors.toList()
    }

    private fun walkInput(
        xRange: List<Int>,
        yRange: List<Int>,
        zRange: List<Int>,
        action: (Int, Int, Int) -> Unit
    ) {
        for (z in zRange) {
            for (y in yRange) {
                for (x in xRange) {
                    action(x, y, z)
                }
            }
        }
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
