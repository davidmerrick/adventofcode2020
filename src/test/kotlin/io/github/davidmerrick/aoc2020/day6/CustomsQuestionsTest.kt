package io.github.davidmerrick.aoc2020.day6

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class CustomsQuestionsTest {

    @Test
    fun `Validate example questions`() {
        val groups = readGroups("day6.txt")
        val sum = groups.sumBy { it.uniqueQuestions }
        sum shouldBe 11
    }

    @Test
    fun `Part 1 full input`() {
        val groups = readGroups("day6-full.txt")
        val sum = groups.sumBy { it.uniqueQuestions }
        println(sum)
    }

    @Test
    fun `Validate part 2 test input`() {
        val groups = readGroups("day6.txt")
        val sum = groups.sumBy { it.sameQuestions }
        sum shouldBe 6
    }

    @Test
    fun `Part 2 full input`() {
        val groups = readGroups("day6-full.txt")
        val sum = groups.sumBy { it.sameQuestions }
        sum shouldBe 3288
    }

    private fun readGroups(fileName: String): List<CustomsGroup> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readText()
            .split("\n\n")
            .map {
                val input = it.split(Regex("[\n]"))
                    .filterNot { entry -> entry.isEmpty() }
                CustomsGroup(input)
            }
    }
}
