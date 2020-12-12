package io.github.davidmerrick.aoc2020.day5

private const val ROW_ONE_CHAR = 'B'
private const val COLUMN_ONE_CHAR = 'R'

class BoardingPass(input: String) {
    val row = encodeFromBinary(ROW_ONE_CHAR, input.substring(0, 7))
    val column = encodeFromBinary(COLUMN_ONE_CHAR, input.substring(7, 10))

    val seatId by lazy { (row * 8) + column }
    private fun encodeFromBinary(oneChar: Char, input: String): Int {
        return input.map { if (it == oneChar) "1" else "0" }
            .joinToString("")
            .toInt(2)
    }
}
