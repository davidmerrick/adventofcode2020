package io.github.davidmerrick.aoc2020.day2

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PasswordValidatorPart2Test {

    @Test
    fun `Test first given inputs`(){
        val inputs = parseInput("day2.txt")
        val count = inputs.count { PasswordValidatorPart2.validate(it.first, it.second) }
        count shouldBe 1
    }

    @Test
    fun `Test full inputs`(){
        val inputs = parseInput("day2-full.txt")
        val count = inputs.count { PasswordValidatorPart2.validate(it.first, it.second) }
        count shouldBe 313
    }

    private fun parseInput(fileName: String): List<Pair<String, PasswordRulesPart2>>{
        return TestUtil.readLines(this::class, fileName)
            .map { parseLine(it) }
    }

    private fun parseLine(line: String): Pair<String, PasswordRulesPart2> {
        val splitLine = line.split(" ")
        val indices = splitLine[0].split("-")
        val inputChar = splitLine[1][0]
        return Pair(
            splitLine[2],
            PasswordRulesPart2(
                inputChar,
                indices[0].toInt() - 1,
                indices[1].toInt() - 1
            )
        )
    }
}
