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
class Ship(initialDirection: ShipDirection = EAST) {
    private var x: Int = 0
    private var y: Int = 0
    private var _direction = initialDirection
    val direction
    get() = _direction

    val distance: Int
    get() = abs(x) + abs(y)

    fun handle(instruction: NavInstruction){
        when {
            shouldTurn(instruction.action) -> {
                turn(instruction)
            }
            instruction.action == NavAction.F -> {
                moveForward(instruction.value)
            }
            else -> {
                move(
                    actionToDirection[instruction.action]!!,
                    instruction.value
                )
            }
        }
    }

    private fun moveForward(value: Int){
        move(_direction, value)
    }

    private fun move(direction: ShipDirection, value: Int){
        when(direction){
            NORTH -> y += value
            EAST -> x += value
            SOUTH -> y -= value
            WEST -> x -= value
        }
    }

    private fun shouldTurn(action: NavAction): Boolean {
        return action in listOf(NavAction.L, NavAction.R)
    }

    private fun turn(instruction: NavInstruction){
        val direction = instruction.action
        val numTurns = (instruction.value % 360)/90
        val index = shipDirections.indexOf(_direction)
        _direction = if(direction == NavAction.R){
            shipDirections[(index + numTurns) % shipDirections.size]
        } else {
            val difference = index - numTurns
            val newIndex = if(difference < 0){
                shipDirections.size + difference
            } else difference
            shipDirections[newIndex]
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
