package io.github.davidmerrick.aoc2020.day18

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun `Base case`() {
        val input = parse("2 * 3")
        val postFix = ExpressionEvaluator.toPostFix(input)
        postFix shouldBe listOf('2', '3', '*')

        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 6
    }

    @Test
    fun `Example 1`() {
        val input = parse("1 + (2 * 3) + (4 * (5 + 6))")
        val postFix = ExpressionEvaluator.toPostFix(input)
        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 51
    }

    @Test
    fun `Example 2`() {
        val input = parse("2 * 3 + (4 * 5)")
        val postFix = ExpressionEvaluator.toPostFix(input)
        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 26
    }

    @Test
    fun `Basic parens case`() {
        val input = parse("5 + (8 * 3 + 9)")
        val postFix = ExpressionEvaluator.toPostFix(input)
        postFix.size shouldBe 7
        postFix shouldBe listOf('5', '8', '3', '*', '9', '+', '+')

        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 38
    }

    @Test
    fun `Example 3`() {
        val input = parse("5 + (8 * 3 + 9 + 3 * 4 * 3)")
        val postFix = ExpressionEvaluator.toPostFix(input)
        postFix.size shouldBe 13
        postFix shouldBe listOf('5', '8', '3', '*', '9', '+', '3', '+', '4', '*', '3', '*', '+')

        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 437
    }

    @Test
    fun `Example 4`() {
        val input = parse("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")
        val postFix = ExpressionEvaluator.toPostFix(input)

        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 12_240
    }

    @Test
    fun `Example 5`() {
        val input = parse("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
        val postFix = ExpressionEvaluator.toPostFix(input)

        ExpressionEvaluator.evaluatePostFix(postFix) shouldBe 13_632
    }

    @Test
    fun `Part 1`() {
        val result = TestUtil.parseInput(this::class, "day18.txt") {
            val postFix = ExpressionEvaluator.toPostFix(parse(it))
            ExpressionEvaluator.evaluatePostFix(postFix).toLong()
        }.sum()
        println(result)
    }

    private fun parse(input: String): List<Char> {
        return input.map { it }
            .filterNot { it == ' ' }
    }
}

