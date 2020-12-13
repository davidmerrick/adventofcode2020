package io.github.davidmerrick.aoc2020.day8

import io.github.davidmerrick.aoc2020.day8.Operation.acc
import io.github.davidmerrick.aoc2020.day8.Operation.jmp
import io.github.davidmerrick.aoc2020.day8.Operation.nop
import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun `Execute example input`(){
        val instructions = parseInstructions("day8.txt")
        val executor = InstructionExecutor(instructions)
        executor.executeUntilHalt()
        executor.accumulator shouldBe 5
    }

    @Test
    fun `Execute corrected input`(){
        val instructions = parseInstructions("day8-corrected.txt")
        val executor = InstructionExecutor(instructions)
        executor.executeUntilHalt()
        executor.accumulator shouldBe 8
    }

    @Test
    fun `Execute full input`(){
        val instructions = parseInstructions("day8-full.txt")
        val executor = InstructionExecutor(instructions)
        executor.executeUntilHalt()
        println(executor.accumulator)
    }

    @Test
    fun `Execute corrected, full input`(){
        val instructions = parseInstructions("day8-full-corrected.txt")
        val executor = InstructionExecutor(instructions)
        executor.executeUntilHalt()
        executor.loopDetected shouldBe false
        println(executor.accumulator)
    }

    @Test
    fun `Test find terminable program`(){
        val instructions = parseInstructions("day8.txt")
        val lineToChange = findTerminableProgram(instructions)
        lineToChange shouldBe 7
    }

    @Test
    fun `Find terminable program`(){
        val instructions = parseInstructions("day8-full.txt")
        val lineToChange = findTerminableProgram(instructions)
        println(lineToChange)
    }

    /**
     * Returns line to change to make program terminate
     */
    private fun findTerminableProgram(instructions: List<Instruction>): Int {
        for(i in instructions.indices){
            if(instructions[i].operation == acc) continue

            val newInstructions = instructions.toMutableList()
                if(instructions[i].operation == nop){
                    newInstructions[i] = instructions[i].copy(jmp)
                } else {
                    newInstructions[i] = instructions[i].copy(nop)
                }
            val executor = InstructionExecutor(newInstructions)
            executor.executeUntilHalt()
            if(!executor.loopDetected){
                return i
            }
        }

        throw RuntimeException("Not found")
    }

    @Test
    fun `Execute full, corrected input`(){
        val instructions = parseInstructions("day8-full-corrected.txt")
        val executor = InstructionExecutor(instructions)
        executor.executeUntilHalt()
        println(executor.accumulator)
    }

    private fun parseInstructions(fileName: String): List<Instruction> {
        return TestUtil.readLines(this::class, fileName)
            .map {
                val split = it.split(" ")
                Instruction(
                    Operation.valueOf(split[0]),
                    split[1].toInt()
                )
            }
    }
}
