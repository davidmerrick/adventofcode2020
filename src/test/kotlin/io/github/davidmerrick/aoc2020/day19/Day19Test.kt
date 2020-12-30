package io.github.davidmerrick.aoc2020.day19

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun `Parse input`() {
        val input = TestUtil.readText(this::class, "example.txt")
        val parser = Day19Parser(input)
        val messages = parser.parseMessages()
        messages.size shouldBe 5

        val rules = parser.parseRules()
        rules.size shouldBe 9
    }
}
