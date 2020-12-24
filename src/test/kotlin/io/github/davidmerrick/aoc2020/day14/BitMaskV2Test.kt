package io.github.davidmerrick.aoc2020.day14

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BitMaskV2Test {

    @Test
    fun `Example input`(){
        val mask = BitMaskV2("000000000000000000000000000000X1001X")
        val results = mask.apply(42)
        results.size shouldBe 4
        results.containsAll(listOf(26, 27, 58, 59)) shouldBe true
    }
}
