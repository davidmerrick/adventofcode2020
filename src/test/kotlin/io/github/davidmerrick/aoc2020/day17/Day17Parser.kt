package io.github.davidmerrick.aoc2020.day17

import io.github.davidmerrick.aoc2020.day17.CubeState.ACTIVE
import io.github.davidmerrick.aoc2020.day17.CubeState.INACTIVE
import io.github.davidmerrick.aoc2020.testutil.TestUtil

private const val ACTIVE_CHAR = '#'

object Day17Parser {

    fun parse(fileName: String): CubeGrid {
        val initialGrid = TestUtil.parseInput(this::class, fileName) { line ->
            line.map { if (it == ACTIVE_CHAR) ACTIVE else INACTIVE }
        }
        return CubeGrid(listOf(initialGrid))
    }

    fun parse4d(fileName: String): CubeGrid4d {
        val initialGrid = TestUtil.parseInput(this::class, fileName) { line ->
            line.map { if (it == ACTIVE_CHAR) ACTIVE else INACTIVE }
        }
        return CubeGrid4d(listOf(listOf(initialGrid)))
    }
}
