package io.github.davidmerrick.aoc2020.day5

class BoardingPass(
    private val boardingPassString: String
) {
    val row: Int
    get() {
        var rows = (0..127).toList()
        val assignedRow = boardingPassString.substring(0, 7)
        assignedRow.forEach {
            rows = if(it == 'F'){
                rows.subList(0, rows.size/2)
            } else {
                rows.subList(rows.size/2, rows.size)
            }
        }
        return rows[0]
    }

    val column: Int
    get() {
        var columns = (0..8).toList()
        val assignedSeat = boardingPassString.substring(7, 10)
        assignedSeat.forEach {
            columns = if(it == 'L'){
                columns.subList(0, columns.size/2)
            } else {
                columns.subList(columns.size/2, columns.size)
            }
        }
        return columns[0]
    }

    val seatId: Int
    get(){
        return (row * 8) + column
    }
}
