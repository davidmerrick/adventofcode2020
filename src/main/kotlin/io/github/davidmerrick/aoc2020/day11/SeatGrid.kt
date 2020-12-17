package io.github.davidmerrick.aoc2020.day11

class SeatGrid(private val _input: List<List<SeatType>>) {

    val width: Int
        get() = _input[0].size

    val height: Int
        get() = _input.size

    val seats: List<List<SeatType>>
        get() = _input.toList()

    fun getSeatAt(x: Int, y: Int): SeatType {
        return _input[y][x]
    }

    fun getVisibleSeats(x: Int, y: Int): List<SeatType> {
        return listOf(
            // Right
            getVisibleSeatsInDirection(x, y, 1, 0),
            // Left
            getVisibleSeatsInDirection(x, y, -1, 0),
            // Up
            getVisibleSeatsInDirection(x, y, 0, 1),
            // Down
            getVisibleSeatsInDirection(x, y, 0, -1),
            // Upper left
            getVisibleSeatsInDirection(x, y, -1, -1),
            // Upper right
            getVisibleSeatsInDirection(x, y, 1, -1),
            // Lower left
            getVisibleSeatsInDirection(x, y, -1, 1),
            // Lower right
            getVisibleSeatsInDirection(x, y, 1, 1)
        ).mapNotNull { it }
    }

    private fun getVisibleSeatsInDirection(
        x: Int,
        y: Int,
        deltaX: Int,
        deltaY: Int
    ): SeatType? {
        var currentX = x + deltaX
        var currentY = y + deltaY

        while (currentX in (0 until width) && currentY in (0 until height)) {
            val seat = getSeatAt(currentX, currentY)
            if (seat != SeatType.FLOOR) {
                return seat
            }
            currentX += deltaX
            currentY += deltaY
        }

        return null
    }

    fun getAdjacentSeats(x: Int, y: Int): List<SeatType> {
        val xRange = when (x) {
            0 -> (0..1)
            _input[0].size - 1 -> (x - 1..x)
            else -> (x - 1..x + 1)
        }
        val yRange = when (y) {
            0 -> (0..1)
            _input.size - 1 -> (y - 1..y)
            else -> (y - 1..y + 1)
        }

        val adjacentSeats = mutableListOf<SeatType>()
        for (i in xRange) {
            for (j in yRange) {
                adjacentSeats.add(getSeatAt(i, j))
            }
        }
        adjacentSeats.remove(getSeatAt(x, y)) // Remove seat itself
        return adjacentSeats.toList()
    }

    fun applyRules(rules: (grid: SeatGrid, x: Int, y: Int) -> SeatType): SeatGrid {
        val newInput = mutableListOf<List<SeatType>>()
        for (j in _input.indices) {
            val row = mutableListOf<SeatType>()
            for (i in _input[0].indices) {
                row.add(rules(this, i, j))
            }
            newInput.add(row)
        }
        return SeatGrid(newInput)
    }
}
