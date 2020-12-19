package io.github.davidmerrick.aoc2020.day12

import io.github.davidmerrick.aoc2020.day12.NavAction.E
import io.github.davidmerrick.aoc2020.day12.NavAction.F
import io.github.davidmerrick.aoc2020.day12.NavAction.L
import io.github.davidmerrick.aoc2020.day12.NavAction.N
import io.github.davidmerrick.aoc2020.day12.NavAction.R
import io.github.davidmerrick.aoc2020.day12.NavAction.S
import io.github.davidmerrick.aoc2020.day12.NavAction.W
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
    val x: Int
        get() = _x
    val y: Int
        get() = _y
    val wayPointX: Int
        get() = wayPoint.x
    val wayPointY: Int
        get() = wayPoint.y

    private var _x: Int = 0
    private var _y: Int = 0

    val distance: Int
        get() = abs(_x) + abs(_y)

    fun handle(instruction: NavInstruction) {
        when {
            instruction.action == F -> {
                moveShip(instruction.value)
            }
            shouldRotateWaypoint(instruction.action) -> {
                rotateWaypoint(instruction)
            }
            else -> {
                moveWayPoint(
                    actionToDirection[instruction.action]!!,
                    instruction.value
                )
            }
        }
    }

    private fun shouldRotateWaypoint(action: NavAction): Boolean {
        return action == L || action == R
    }

    private fun rotateWaypoint(instruction: NavInstruction) {
        val direction = instruction.action
        val degrees = (instruction.value % 360)
        if (degrees == 0) return
        val formula = if (direction == R) {
            rotations[degrees]
        } else {
            rotations[360 - degrees]
        }!!
        val result = formula(wayPoint.x, wayPoint.y, _x, _y)
        wayPoint.x = result.first
        wayPoint.y = result.second
    }

    // Increments of 90 degree rotations
    // a and b are x and y coords of ship, respectively
    // Formulas from https://www.khanacademy.org/math/geometry-home/transformations/geo-rotations/v/rotating-about-arbitrary-point#:~:text=A%20point%20(a%2C%20b)%20rotated%20around%20a%20point%20(,%2D%20x)%20%2B%20y).
    private val rotations = mapOf(
        90 to { a: Int, b: Int, x: Int, y: Int -> Pair(-b + y + x, a - x + y) },
        180 to { a: Int, b: Int, x: Int, y: Int -> Pair(-1 * (a - x) + x, -1 * (b - y) + y) },
        270 to { a: Int, b: Int, x: Int, y: Int -> Pair((b - y) + x, -(a - x) + y) },
    )

    private fun moveShip(value: Int) {
        val wayPointDiffX = wayPoint.x - _x
        _x += wayPointDiffX * value
        val wayPointDiffY = wayPoint.y - _y
        _y += wayPointDiffY * value

        // Waypoint moves with ship
        wayPoint.x = x + wayPointDiffX
        wayPoint.y = y + wayPointDiffY
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
            N to NORTH,
            E to EAST,
            S to SOUTH,
            W to WEST
        )
    }
}
