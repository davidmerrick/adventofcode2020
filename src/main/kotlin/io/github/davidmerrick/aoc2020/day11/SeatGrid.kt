package io.github.davidmerrick.aoc2020.day11

class SeatGrid(private val _input: List<List<SeatType>>) {

    val width: Int
    get() = _input[0].size

    val seats: List<List<SeatType>>
    get() = _input.toList()

    fun getSeatAt(x: Int, y: Int): SeatType {
        return _input[y][x]
    }

    fun getAdjacentSeats(x: Int, y: Int): List<SeatType> {
        val xRange = when(x){
            0 -> (0..1)
            _input[0].size - 1 -> (x-1..x)
            else -> (x-1..x+1)
        }
        val yRange = when(y){
            0 -> (0..1)
            _input.size - 1 -> (y-1..y)
            else -> (y-1..y+1)
        }

        val adjacentSeats = mutableListOf<SeatType>()
        for(i in xRange){
            for (j in yRange){
                adjacentSeats.add(getSeatAt(i, j))
            }
        }
        adjacentSeats.remove(getSeatAt(x, y)) // Remove seat itself
        return adjacentSeats.toList()
    }

    fun applyRules(rules: (grid: SeatGrid, x: Int, y: Int) -> SeatType): SeatGrid {
        val newInput = mutableListOf<List<SeatType>>()
        for(j in _input.indices){
            val row = mutableListOf<SeatType>()
            for (i in _input[0].indices){
                row.add(rules(this, i, j))
            }
            newInput.add(row)
        }
        return SeatGrid(newInput)
    }
}
