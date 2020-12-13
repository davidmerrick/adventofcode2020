package io.github.davidmerrick.aoc2020.day8

import io.github.davidmerrick.aoc2020.day8.Operation.acc
import io.github.davidmerrick.aoc2020.day8.Operation.jmp
import io.github.davidmerrick.aoc2020.day8.Operation.nop

class InstructionExecutor(
    private val instructions: List<Instruction>
) {
    private var visited: MutableList<Int> = mutableListOf()
    private var instructionPointer = 0
    val loopDetected: Boolean
        get() = _loopDetected
    private var _loopDetected: Boolean = false

    val accumulator: Int
        get() = _accumulator
    private var _accumulator = 0

    private fun reset() {
        _accumulator = 0
        _loopDetected = false
        instructionPointer = 0
        visited = mutableListOf()
    }

    fun executeUntilHalt() {
        reset()
        while (!finishedExecuting()) {
            // Fetch current instruction
            val instruction = instructions[instructionPointer]

            // Put it in map of visited
            visited.add(instructionPointer)

            // Execute instruction
            when (instruction.operation) {
                nop -> instructionPointer++
                acc -> {
                    _accumulator += instruction.argument
                    instructionPointer++
                }
                jmp -> instructionPointer += instruction.argument
            }
        }
    }

    private fun finishedExecuting(): Boolean {
        if (instructionPointer in visited) {
            _loopDetected = true
        }
        return instructionPointer > instructions.size - 1
            || instructionPointer in visited
    }
}
