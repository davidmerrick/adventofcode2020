package io.github.davidmerrick.aoc2020.day16

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun `Parse input`(){
        val text = TestUtil.readText(this::class, "day16.txt")
        solve(text) shouldBe 71
    }

    @Test
    fun `Part 1`(){
        val text = TestUtil.readText(this::class, "day16-full.txt")
        println(solve(text))
    }

    private fun solve(text: String): Int {
        val split = text.split("\n\n")

        // Parse rules
        val rulesText = split[0]

        val validNumbers = rulesText.split("\n")
            .map { getRanges(it) }
            .reduce { a, b -> a.union(b) }
            .sorted()

        // Parse nearby tickets
        val nearby = split[2].split("\n")
            .filterNot { it.startsWith("nearby") }
            .filterNot { it.isEmpty() }
            .flatMap { line -> line.split(",").map { it.toInt() } }

        return nearby.filterNot { validNumbers.contains(it) }
            .sum()
    }

    /**
     * Given a line of rules, like:
     * departure location: 42-322 or 347-954
     * Return a set of valid values
     */
    private fun getRanges(line: String): Set<Int> {
        val rulesRegex = "[a-z ]*\\: ([\\d]+)\\-([\\d]+) or ([\\d]+)\\-([\\d]+)"
            .toRegex()
        val groups = rulesRegex.find(line)!!.groups
        val a = (groups[1]!!.value.toInt()..groups[2]!!.value.toInt())
            .toSet()
        val b = (groups[3]!!.value.toInt()..groups[4]!!.value.toInt())
            .toSet()
        return a.union(b)
    }
}
