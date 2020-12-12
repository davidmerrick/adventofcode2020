package io.github.davidmerrick.aoc2020.day7

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val SHINY_GOLD = "shiny gold"

class BagTestPart2 {

    @Test
    fun `Example input test`() {
        val file = this::class.java.getResourceAsStream("day7.txt")
        val tree = BagTree.parse(file)
        val count = tree.traverseChildCounts(BagNode(SHINY_GOLD, 1))
        count shouldBe 32
    }

    @Test
    fun `Full input`() {
        val file = this::class.java.getResourceAsStream("day7-full.txt")
        val tree = BagTree.parse(file)
        val count = tree.traverseChildCounts(BagNode(SHINY_GOLD, 1))
        println(count)
    }
}
