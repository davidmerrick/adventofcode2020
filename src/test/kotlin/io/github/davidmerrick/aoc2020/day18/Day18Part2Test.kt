package io.github.davidmerrick.aoc2020.day18

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test


class Day18Part2Test {

    @Test
    fun `Example 2`() {
        val input = parse("1 + (2 * 3) + (4 * (5 + 6))")
        val postFix = ExpressionEvaluatorV2.toPostFix(input)
        ExpressionEvaluatorV2.evaluatePostFix(postFix) shouldBe 51
    }

    @Test
    fun `Example 3`() {
        val input = parse("2 * 3 + (4 * 5)")
        val postFix = ExpressionEvaluatorV2.toPostFix(input)
        postFix shouldBe listOf('2', '3', '4', '5', '*', '+', '*')
        ExpressionEvaluatorV2.evaluatePostFix(postFix) shouldBe 46
    }

    @Test
    fun `Part 2`() {
        val result = TestUtil.parseInput(this::class, "day18.txt") {
            val postFix = ExpressionEvaluatorV2.toPostFix(parse(it))
            ExpressionEvaluatorV2.evaluatePostFix(postFix)
        }.sum()
        println(result)
    }

    private fun parse(input: String): List<Char> {
        return input.map { it }
            .filterNot { it == ' ' }
    }
}

