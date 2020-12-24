package io.github.davidmerrick.aoc2020.day14

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BitMaskTest {

    @Test
    fun `Apply bitmask`() {
        val mask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        mask.apply("000000000000000000000000000000001011") shouldBe 73
        mask.apply("000000000000000000000000000001100101") shouldBe 101
        mask.apply(0) shouldBe 64
    }
}
