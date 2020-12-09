package io.github.davidmerrick.aoc2020.day2

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PasswordValidatorTest {

    @Test
    fun `Test first given inputs`(){
        val inputs = parseInput("day2.txt")
        val count = inputs.count { PasswordValidator.validate(it.first, it.second) }
        count shouldBe 2
    }

    @Test
    fun `Test full inputs`(){
        val inputs = parseInput("day2-full.txt")
        val count = inputs.count { PasswordValidator.validate(it.first, it.second) }
        count shouldBe 500
    }

    private fun parseInput(fileName: String): List<Pair<String, PasswordRules>>{
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .map { parseLine(it) }
    }

    private fun parseLine(line: String): Pair<String, PasswordRules> {
        val splitLine = line.split(" ")
        val range = splitLine[0].split("-")
        val inputChar = splitLine[1][0]
        return Pair(
            splitLine[2],
            PasswordRules(
                inputChar,
                range[0].toInt(),
                range[1].toInt()
            )
        )
    }
}
