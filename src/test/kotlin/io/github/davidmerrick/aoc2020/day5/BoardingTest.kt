package io.github.davidmerrick.aoc2020.day5

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BoardingTest {

    @Test
    fun `Test rows`(){
        testRows("BFFFBBFRRR", 70)
        testRows("FFFBBBFRRR", 14)
        testRows("BBFFBBFRLL", 102)
    }

    @Test
    fun `Test columns`(){
        testColumns("FBFBBFFRLR", 5)
        testColumns("BFFFBBFRRR", 7)
        testColumns("FFFBBBFRRR", 7)
        testColumns("BBFFBBFRLL", 4)
    }

    @Test
    fun `Test seat id`(){
        testSeatId("FBFBBFFRLR", 357)
        testSeatId("BFFFBBFRRR", 567)
        testSeatId("FFFBBBFRRR", 119)
        testSeatId("BBFFBBFRLL", 820)
    }

    @Test
    fun `Part 1 input`(){
        val maxId = readLines("day5-part1.txt")
            .map { BoardingPass(it).seatId }
            .maxOrNull()
        println(maxId)
    }

    @Test
    fun `Part 2`(){
        val ids = readLines("day5-full.txt")
            .map { BoardingPass(it).seatId }
            .sorted()
        val seatSet = (ids.minOrNull()!!..ids.maxOrNull()!!)
            .toSet()
        val remaining = seatSet.minus(ids.toSet())
        remaining shouldBe 743
    }

    private fun testRows(
        boardingPassString: String,
        expectedRow: Int
    ){
        BoardingPass(boardingPassString)
            .row shouldBe expectedRow
    }

    private fun testColumns(
        boardingPassString: String,
        expectedColumn: Int
    ){
        BoardingPass(boardingPassString)
            .column shouldBe expectedColumn
    }

    private fun testSeatId(
        boardingPassString: String,
        expectedId: Int
    ){
        BoardingPass(boardingPassString)
            .seatId shouldBe expectedId
    }

    private fun readLines(fileName: String): List<String> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
    }
}
