package io.github.davidmerrick.aoc2020.day11

import io.github.davidmerrick.aoc2020.day11.SeatType.EMPTY
import io.github.davidmerrick.aoc2020.day11.SeatType.OCCUPIED
import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import java.lang.RuntimeException

class Day11Test {

    @Test
    fun `Parse input`(){
        val grid = parseSeats("day11.txt")
        grid shouldNotBe null
    }

    @Test
    fun `Get adjacent seats at left corner`(){
        val expected = listOf(SeatType.FLOOR, EMPTY, EMPTY)
        val grid = parseSeats("day11.txt")
        val actual = grid.getAdjacentSeats(0, 0)
        actual.size shouldBe expected.size
        actual.containsAll(expected)
    }

    @Test
    fun `Get adjacent seats at right corner`(){
        val expected = listOf(OCCUPIED, EMPTY, EMPTY)
        val grid = parseSeats("day11.txt")
        val actual = grid.getAdjacentSeats(grid.width-1, 0)
        actual.size shouldBe expected.size
        actual.containsAll(expected)
    }

    @Test
    fun `Get visible seats from part 2 example`(){
        val expected = listOf(
            OCCUPIED,
            OCCUPIED,
            OCCUPIED,
            OCCUPIED,
            OCCUPIED,
            OCCUPIED,
            OCCUPIED,
            OCCUPIED
        )
        val grid = parseSeats("day11-part2.txt")
        val actual = grid.getVisibleSeats(3, 4)
        actual.size shouldBe expected.size
        actual.containsAll(expected)
    }

    @Test
    fun `Get visible seats from part 2 example 2`(){
        val expected = listOf(EMPTY)
        val grid = parseSeats("day11-part2-2.txt")
        val actual = grid.getVisibleSeats(1, 1)
        actual.size shouldBe expected.size
        actual.containsAll(expected)
    }

    @Test
    fun `Get adjacent seats in middle of grid`(){
        val expected = listOf(
            EMPTY,
            EMPTY,
            EMPTY,
            EMPTY,
            EMPTY,
            EMPTY,
            EMPTY,
            SeatType.FLOOR
        )
        val grid = parseSeats("day11.txt")
        val actual = grid.getAdjacentSeats(4, 4)
        actual.size shouldBe expected.size
        actual.containsAll(expected)
    }

    @Test
    fun `Count iterations until stable`(){
        var currentGrid = parseSeats("day11.txt")
        val rules = fun(grid: SeatGrid, x: Int, y: Int): SeatType {
            val seat = grid.getSeatAt(x, y)
            val adjacentSeats = grid.getAdjacentSeats(x, y)
            return if(seat == EMPTY &&
                adjacentSeats.none { it == OCCUPIED }
            ){
                OCCUPIED
            } else if (
                seat == OCCUPIED && (adjacentSeats.count { it == OCCUPIED } >= 4)
            ){
                EMPTY
            } else {
                seat
            }
        }
        var stable = false
        var iterationCount = 0
        while(!stable){
            val newGrid = currentGrid.applyRules(rules)
            stable = newGrid.seats == currentGrid.seats
            currentGrid = newGrid
            iterationCount++
        }

        iterationCount shouldBe 6
        val occupiedCount = currentGrid.seats.asSequence()
            .flatten()
            .count { it == OCCUPIED }
        occupiedCount shouldBe 37
    }

    @Test
    fun `Count occupied seats in part 2 example`(){
        var currentGrid = parseSeats("day11.txt")

        var stable = false
        var iterationCount = 0
        while(!stable){
            val newGrid = currentGrid.applyRules(part2Rules)
            stable = newGrid.seats == currentGrid.seats
            currentGrid = newGrid
            iterationCount++
        }

        val occupiedCount = currentGrid.seats.asSequence()
            .flatten()
            .count { it == OCCUPIED }
        occupiedCount shouldBe 26
    }

    @Test
    fun `Count occupied seats in part 2 full`(){
        var currentGrid = parseSeats("day11-full.txt")

        var stable = false
        var iterationCount = 0
        while(!stable){
            val newGrid = currentGrid.applyRules(part2Rules)
            stable = newGrid.seats == currentGrid.seats
            currentGrid = newGrid
            iterationCount++
        }

        val occupiedCount = currentGrid.seats.asSequence()
            .flatten()
            .count { it == OCCUPIED }
        println(occupiedCount)
    }

    private val part2Rules = fun(grid: SeatGrid, x: Int, y: Int): SeatType {
        val seat = grid.getSeatAt(x, y)
        val visibleSeats = grid.getVisibleSeats(x, y)
        return if(seat == EMPTY &&
            visibleSeats.none { it == OCCUPIED }
        ){
            OCCUPIED
        } else if (
            seat == OCCUPIED && (visibleSeats.count { it == OCCUPIED } >= 5)
        ){
            EMPTY
        } else {
            seat
        }
    }

    @Test
    fun `Count part 1`(){
        var currentGrid = parseSeats("day11-full.txt")
        val rules = fun(grid: SeatGrid, x: Int, y: Int): SeatType {
            val seat = grid.getSeatAt(x, y)
            val adjacentSeats = grid.getAdjacentSeats(x, y)
            return if(seat == EMPTY &&
                adjacentSeats.none { it == OCCUPIED }
            ){
                OCCUPIED
            } else if (
                seat == OCCUPIED && (adjacentSeats.count { it == OCCUPIED } >= 4)
            ){
                EMPTY
            } else {
                seat
            }
        }
        var stable = false
        var iterationCount = 0
        while(!stable){
            val newGrid = currentGrid.applyRules(rules)
            stable = newGrid.seats == currentGrid.seats
            currentGrid = newGrid
            iterationCount++
        }

        val occupiedCount = currentGrid.seats.asSequence()
            .flatten()
            .count { it == OCCUPIED }
        println(occupiedCount)
    }

    private fun parseSeats(fileName: String): SeatGrid {
        val input = TestUtil.parseInput(this::class, fileName){
            it.map { char ->
                when(char){
                    'L' -> EMPTY
                    '.' -> SeatType.FLOOR
                    '#' -> OCCUPIED
                    else -> throw RuntimeException("Invalid input")
                }
            }.toList()
        }.toList()
        return SeatGrid(input)
    }
}
