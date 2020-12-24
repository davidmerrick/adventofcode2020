package io.github.davidmerrick.aoc2020.day13

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day13Part2Test {

    @Test
    // https://www.youtube.com/watch?v=2-tdwLqyaKo
    fun `Simple example`() {
        val congruences = listOf(
            Congruence(1, 3),
            Congruence(1, 4),
            Congruence(1, 5),
            Congruence(0, 7),
        )
        val result = CrtSolver(congruences).solve()
        result shouldBe 301
    }

    @Test
    // https://www.youtube.com/watch?v=0dbXaSkZ-vc
    fun `Simple example 2`() {
        val congruences = listOf(
            Congruence(2, 3),
            Congruence(3, 5),
            Congruence(2, 7),
        )
        val result = CrtSolver(congruences).solve()
        result shouldBe 23
    }

    @Test
    fun `Example 1`(){
        val congruences = parseLine("17,x,13,19")
        val result = CrtSolver(congruences).solve()
        result shouldBe 3417
    }

    @Test
    fun `Example 2`(){
        val congruences = parseLine("67,7,59,61")
        val solver = CrtSolver(congruences)
        val result = solver.solve()
        result shouldBe 754018
    }

    @Test
    fun `Example 3`(){
        val congruences = parseLine("67,x,7,59,61")
        val result = CrtSolver(congruences).solve()
        result shouldBe 779210
    }

    @Test
    fun `Example 4`(){
        val congruences = parseLine("67,7,x,59,61")
        val result = CrtSolver(congruences).solve()
        result shouldBe 1261476
    }

    @Test
    fun `Example 5`(){
        val congruences = parseLine("1789,37,47,1889")
        val time = CrtSolver(congruences).solve()
        time shouldBe 1202161486
    }

    @Test
    fun `Example test`() {
        val congruences = parseInput("day13.txt")
        val time = CrtSolver(congruences).solve()
        time shouldBe 1_068_781
    }

    private fun parseInput(fileName: String): List<Congruence> {
        val lines = TestUtil.readLines(this::class, fileName)
        return parseLine(lines[1])
    }

    private fun parseLine(line: String): List<Congruence> {
        return line.split(",")
            .mapIndexed { i, value ->
                if (value != "x") {
                    Congruence(value.toLong(), i.toLong())
                } else null
            }
            .filterNotNull()
            .filterNot { it.mod == 0L }
    }
}
