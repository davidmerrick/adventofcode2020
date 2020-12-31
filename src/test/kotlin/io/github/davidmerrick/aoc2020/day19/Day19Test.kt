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

    @Test
    fun `Rule test`() {
        Rule("1", listOf("a")).isTerminal shouldBe true
        Rule("1", listOf("1", "2")).isTerminal shouldBe false
    }

    @Test
    fun `Validate simple grammar`() {
        val input = """
            0: 1 2 3
            1: "a"
            2: "b"
            3: "b"
        """.trimIndent()
        val parser = Day19Parser(input)
        val rules = parser.parseRules()

        val validator = EarleyRecognizer(rules)
        validator.isValid("abb") shouldBe true
        validator.isValid("bac") shouldBe false
    }
}
