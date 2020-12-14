package io.github.davidmerrick.aoc2020.day10

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `Example test`(){
        val joltages = parseAdapters("day10.txt")

        val fencePosts = mutableListOf<Int>()
        for(i in (1 until joltages.size)){
            fencePosts.add(joltages[i] - joltages[i-1])
        }
        val oneJolts = fencePosts.filter { it == 1 }
        oneJolts.size shouldBe 22
        val threeJolts = fencePosts.filter { it == 3 }
        threeJolts.size shouldBe 10
    }

    @Test
    fun `Part 1 input`(){
        val joltages = parseAdapters("day10-full.txt")

        val fencePosts = mutableListOf<Int>()
        for(i in (1 until joltages.size)){
            fencePosts.add(joltages[i] - joltages[i-1])
        }
        val oneJolts = fencePosts.filter { it == 1 }
        val threeJolts = fencePosts.filter { it == 3 }
        println(oneJolts.size * threeJolts.size)
    }

    @Test
    fun `Part 2 with minimal input`(){
        val adapters = parseAdapters("day10-minimal.txt")
        val graph = JoltageGraph(adapters)
        print("foo")
    }

    @Test
    fun `Part 2 with example input`(){
        val adapters = parseAdapters("day10.txt")
        val graph = JoltageGraph(adapters)
        val pathsCount = graph.distinctPaths(adapters[0], adapters[adapters.size - 1])
        pathsCount shouldBe 19_208L
    }

    @Test
    fun `Part 2 with full input`(){
        val adapters = parseAdapters("day10-full.txt")
        val graph = JoltageGraph(adapters)
        val pathsCount = graph.distinctPaths(adapters[0], adapters[adapters.size - 1])
        println(pathsCount)
    }

    private fun parseAdapters(fileName: String): List<Int> {
        val input = TestUtil.parseInput(this::class, fileName) {
            it.toInt()
        }

        // Add the wall adapter and your device
        val adapters = input.toMutableList()
        adapters.add(0)
        adapters.add(adapters.maxOrNull()!! + 3)
        return adapters.sorted()
            .toList()
    }

}
