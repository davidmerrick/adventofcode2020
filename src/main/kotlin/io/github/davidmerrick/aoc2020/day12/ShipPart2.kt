package io.github.davidmerrick.aoc2020.day12

import io.github.davidmerrick.aoc2020.day12.ShipDirection.EAST
import io.github.davidmerrick.aoc2020.day12.ShipDirection.NORTH
import io.github.davidmerrick.aoc2020.day12.ShipDirection.SOUTH
import io.github.davidmerrick.aoc2020.day12.ShipDirection.WEST
import kotlin.math.abs

/**
 * Ship moves in x, y direction
 * Positive y = north
 * Positive x = east
 */
class ShipPart2(initialDirection: ShipDirection = EAST) {
    private val wayPoint = WayPoint(10, 1)
    private var x: Int = 0
    private var y: Int = 0

    val distance: Int
        get() = abs(x) + abs(y)

    fun handle(instruction: NavInstruction) {
        when {
            instruction.action == NavAction.F -> {
                moveShip(instruction.value)
            }
            else -> {
                moveWayPoint(
                    actionToDirection[instruction.action]!!,
                    instruction.value
                )
            }
        }
    }

    private fun moveShip(value: Int) {
        x += wayPoint.x * value
        y += wayPoint.y * value

        // Waypoint moves with ship
        wayPoint.x += x
        wayPoint.y += y
    }

    private fun moveWayPoint(direction: ShipDirection, value: Int) {
        when (direction) {
            NORTH -> wayPoint.y += value
            EAST -> wayPoint.x += value
            SOUTH -> wayPoint.y -= value
            WEST -> wayPoint.x -= value
        }
    }

    companion object {
        private val shipDirections = listOf(
            NORTH,
            EAST,
            SOUTH,
            WEST
        )

        private val actionToDirection = mapOf(
            NavAction.N to NORTH,
            NavAction.E to EAST,
            NavAction.S to SOUTH,
            NavAction.W to WEST
        )
    }
}
