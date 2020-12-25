package io.github.davidmerrick.aoc2020.day16

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day16Part2Test {

    @Test
    fun `Part 2 example`() {
        val text = TestUtil.readText(this::class, "day16-part2.txt")
        val parser = Day16Parser(text)
        val solver = Day16Solver(
            parser.parseFields(),
            parser.parseYourTicket(),
            parser.parseTickets()
        )
        val fields = solver.solveFields()
            .entries
            .sortedBy { it.value }
            .map { it.key }
        fields.size shouldBe 3
        fields shouldBe listOf("row", "class", "seat")
    }

    @Test
    fun `Solve fields`() {
        val text = TestUtil.readText(this::class, "day16-full.txt")
        val parser = Day16Parser(text)
        val solver = Day16Solver(
            parser.parseFields(),
            parser.parseYourTicket(),
            parser.parseTickets()
        )
        val fields = solver.solveFields()
        fields.size shouldBe 20
    }

    @Test
    fun `Part 2 full`() {
        val text = TestUtil.readText(this::class, "day16-full.txt")
        val parser = Day16Parser(text)
        val solver = Day16Solver(
            parser.parseFields(),
            parser.parseYourTicket(),
            parser.parseTickets()
        )
        solver.solve() shouldBe 517_827_547_723
    }
}
