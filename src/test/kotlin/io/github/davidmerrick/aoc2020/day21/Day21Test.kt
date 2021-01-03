package io.github.davidmerrick.aoc2020.day21

import io.github.davidmerrick.aoc2020.testutil.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun `Parse input`() {
        val ingredientList = parseIngredients("example.txt")

        ingredientList[0].foods.size shouldBe 4
        ingredientList[0].allergens.size shouldBe 2
    }

    @Test
    fun `Narrow down allergens`() {
        val ingredientList = parseIngredients("example.txt")
        val allergenMap = AllergenMap(ingredientList)
        val foodCount = mutableMapOf<String, Int>()
        ingredientList.forEach {
            (it.foods - it.foods.intersect(allergenMap.allergens.values.toSet()))
                    .forEach { food ->
                        foodCount.compute(food) { _, value ->
                            (value ?: 0) + 1
                        }
                    }
        }
        foodCount.asSequence().sumOf { it.value } shouldBe 5
    }

    @Test
    fun `Part 2 example`() {
        val ingredientList = parseIngredients("example.txt")
        val allergenMap = AllergenMap(ingredientList)
        allergenMap.allergens.asSequence()
                .sortedBy { it.key }
                .joinToString(",") { it.value } shouldBe "mxmxvkd,sqjhc,fvjkl"
    }

    @Test
    fun `Part 1`() {
        val ingredientList = parseIngredients("part1.txt")
        val allergenMap = AllergenMap(ingredientList)
        val foodCount = mutableMapOf<String, Int>()
        ingredientList.forEach {
            (it.foods - it.foods.intersect(allergenMap.allergens.values.toSet()))
                    .forEach { food ->
                        foodCount.compute(food) { _, value ->
                            (value ?: 0) + 1
                        }
                    }
        }
        foodCount.asSequence().sumOf { it.value } shouldBe 2072
    }

    @Test
    fun `Part 2`() {
        val ingredientList = parseIngredients("part1.txt")
        val allergenMap = AllergenMap(ingredientList)
        val output = allergenMap.allergens.asSequence()
                .sortedBy { it.key }
                .joinToString(",") { it.value }
        println(output)
    }

    private fun parseIngredients(fileName: String): List<IngredientList> {
        return TestUtil.parseInput(this::class, fileName) {
            IngredientList.parse(it)
        }
    }
}