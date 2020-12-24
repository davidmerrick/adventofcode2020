package io.github.davidmerrick.aoc2020.day14

private const val FLOAT_CHAR = 'X'
private const val MASK_SIZE = 36

class BitMaskV2(private val value: String) {
    fun apply(input: String): List<Long> {
        var output = mutableListOf("")
        for(i in value.indices) {
            when (value[i]) {
                '0' -> appendOutput(input[i], output)
                '1' -> appendOutput('1', output)
                FLOAT_CHAR -> {
                    val ones = output.toMutableList()
                    appendOutput('1', ones)
                    appendOutput('0', output)
                    output.addAll(ones)
                }
            }
        }

        return output.map { it.toLong(2) }
    }

    private fun appendOutput(char: Char, output: MutableList<String>){
        for (i in output.indices){
            output[i] = output[i] + char
        }
    }

    fun apply(input: Long): List<Long> {
        val inputString = input.toString(2).padStart(MASK_SIZE, '0')
        return apply(inputString)
    }
}
