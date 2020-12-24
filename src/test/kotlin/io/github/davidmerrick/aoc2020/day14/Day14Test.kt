package io.github.davidmerrick.aoc2020.day14

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun `Solve example`(){
        solve("day14.txt") shouldBe 165
    }

    @Test
    fun `Solve part 1`(){
        println(solve("day14-full.txt"))
    }

    private fun solve(fileName: String): Long {
        val lines = TestUtil.readLines(this::class, fileName)
        var mask: BitMask? = null
        val memory = mutableMapOf<Long, Long>()
        for(line in lines){
            if(isMask(line)) {
                mask = parseMask(line)
            } else {
                val command = parseCommand(line)
                memory[command.location] = mask!!.apply(command.value)
            }
        }
        return memory.values.sum()
    }

    private fun isMask(line: String): Boolean {
        return line.startsWith("mask")
    }

    private fun parseMask(line: String): BitMask {
        return BitMask(line.split("=")[1].trim())
    }

    private fun parseCommand(line: String): Command {
        val regex = "mem\\[([\\d]+)\\] \\= ([\\d]+)".toRegex()
        val groups = regex.find(line)!!.groups
        return Command(
            groups[1]!!.value.toLong(),
            groups[2]!!.value.toLong()
        )
    }
}
