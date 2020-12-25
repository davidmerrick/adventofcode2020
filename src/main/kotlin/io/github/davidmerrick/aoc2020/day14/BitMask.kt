package io.github.davidmerrick.aoc2020.day14

private const val IGNORE_CHAR = 'X'
private const val MASK_SIZE = 36

class BitMask(private val value: String) {

    fun apply(input: String): Long {
        var output = ""
        for(i in value.indices){
            output += if(value[i] != IGNORE_CHAR){
                // Use the mask value if set
                value[i]
            } else {
                // Otherwise, use input
                input[i]
            }
        }

        return output.toLong(2)
    }

    fun apply(input: Long): Long {
        val inputString = input.toString(2).padStart(MASK_SIZE, '0')
        return apply(inputString)
    }
}
