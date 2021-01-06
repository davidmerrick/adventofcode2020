package io.github.davidmerrick.aoc2020.day25

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val SUBJECT_NUMBER = 7L

class Day25Test {

    @Test
    fun `Brute-force example keys`() {
        findLoopSize(5_764_801L) shouldBe 8
        findLoopSize(17_807_724L) shouldBe 11
    }

    @Test
    fun `Get example encryption key`() {
        val cardKey = 5_764_801L
        val doorKey = 17_807_724L

        val loopSize = findLoopSize(cardKey)
        val encryptionKey = transform(doorKey, loopSize)
        encryptionKey shouldBe 14_897_079L
    }

    @Test
    fun `Part 1`() {
        val cardKey = 8_458_505L
        val doorKey = 16_050_997L

        val loopSize = findLoopSize(doorKey)
        val encryptionKey = transform(cardKey, loopSize)
        println(encryptionKey)
    }

    private fun findLoopSize(key: Long): Long {
        var value = 1L
        var i = 0L
        while (value != key) {
            value = (value * SUBJECT_NUMBER) % 20201227
            i++
        }
        return i
    }

    private fun transform(subjectNumber: Long, loopSize: Long): Long {
        var value = 1L
        for (i in (0 until loopSize)) {
            value = (value * subjectNumber) % 20201227
        }
        return value
    }
}