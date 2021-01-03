package io.github.davidmerrick.aoc2020.day21

data class IngredientList(val foods: Set<String>, val allergens: Set<String>) {
    companion object {
        fun parse(input: String): IngredientList {
            val split = input.split("(")
            val foods = split[0].split(" ")
                    .map { it.trim() }
                    .filterNot { it.isEmpty() }
                    .toSet()
            val allergens = split[1].split(" ")
                    .asSequence()
                    .map { it.replace(")", "") }
                    .map { it.replace(",", "") }
                    .map { it.trim() }
                    .filterNot { it == "contains" || it.isEmpty() }
                    .toSet()
            return IngredientList(foods, allergens)
        }
    }
}
