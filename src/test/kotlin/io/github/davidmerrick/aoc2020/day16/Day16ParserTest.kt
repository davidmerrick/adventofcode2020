package io.github.davidmerrick.aoc2020.day16

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day16ParserTest {

    @Test
    fun `Parse example`(){
        val input = TestUtil.readText(this::class, "day16-part2.txt")
        val parser = Day16Parser(input)
        val fields = parser.parseFields()
        fields.size shouldBe 3

        val tickets = parser.parseTickets()
        tickets.size shouldBe 3

        val yourTicket = parser.parseYourTicket()
        yourTicket.size shouldBe 3
    }
}
