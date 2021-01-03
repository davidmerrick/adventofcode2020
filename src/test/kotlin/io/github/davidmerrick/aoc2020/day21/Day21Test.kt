package io.github.davidmerrick.aoc2020.day21

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun `Parse input`() {
        val ingredientList = TestUtil.parseInput(this::class, "example.txt") {
            IngredientList.parse(it)
        }

        ingredientList[0].foods.size shouldBe 4
        ingredientList[0].allergens.size shouldBe 2
    }

    @Test
    fun `Narrow down allergens`() {

    }
}