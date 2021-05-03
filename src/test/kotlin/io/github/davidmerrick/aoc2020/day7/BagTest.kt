package io.github.davidmerrick.aoc2020.day7

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val SHINY_GOLD = "shiny gold"

class BagTest {

    @Test
    fun `Parser test`() {
        val line = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        extractRootColor(line) shouldBe "light red"
        val containsBags = extractContainsBags(line)
        containsBags.size shouldBe 2
        containsBags.contains("bright white") shouldBe true
        containsBags.contains("muted yellow") shouldBe true
    }

    @Test
    fun `Parser test with contains no others`() {
        val line = "dotted black bags contain no other bags."
        extractRootColor(line) shouldBe "dotted black"
        val containsBags = extractContainsBags(line)
        containsBags.size shouldBe 0
    }

    @Test
    fun `Example input`() {
        val parsed = parseInput("day7.txt")
        val children = traverse(SHINY_GOLD, parsed)
        children.size shouldBe 4
    }

    @Test
    fun `Full input`() {
        val parsed = parseInput("day7-full.txt")
        val children = traverse(SHINY_GOLD, parsed)
        children.size shouldBe 370
    }

    private fun traverse(node: String, tree: Map<String, Set<String>>): Set<String> {
        if (!tree.containsKey(node)) {
            return emptySet()
        }

        val currentSet = tree[node]!!
        return currentSet
            .map { traverse(it, tree) }
            .reduce { a, b -> a.union(b) }
            .toSet()
            .union(currentSet)
    }

    private fun parseInput(fileName: String): Map<String, Set<String>> {
        val colorMap = mutableMapOf<String, Set<String>>()
        this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .forEach { parseLine(it, colorMap) }
        return colorMap.toMap()
    }

    private fun parseLine(line: String, colorMap: MutableMap<String, Set<String>>) {
        val rootColor = extractRootColor(line)
        extractContainsBags(line)
            .forEach {
                colorMap.compute(it) { _, value ->
                    val newVal = (value ?: setOf()).toMutableSet()
                    newVal.add(rootColor)
                    newVal.toSet()
                }
            }
    }

    private fun extractRootColor(line: String): String {
        val regex = "^([a-z ]*) bags contain".toRegex()
        return regex.find(line)!!
            .groups[1]!!
            .value
            .trim()
    }

    private fun extractContainsBags(line: String): List<String> {
        if (line.contains("contain no other bags")) {
            return emptyList()
        }

        val regex = "contain ([^.]*)\\.$".toRegex()
        return regex.find(line)!!
            .groups[1]!!
            .value
            .split(",")
            .map {
                it.trim()
                    .replace(Regex("^[\\d]* "), "")
                    .replace(Regex(" bag[s]?$"), "")
            }
    }
}
