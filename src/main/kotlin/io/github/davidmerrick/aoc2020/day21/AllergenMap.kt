package io.github.davidmerrick.aoc2020.day21

class AllergenMap(input: List<IngredientList>) {
    val allergens: Map<String, String>

    init {
        val allergens = mutableMapOf<String, Set<String>>()
        input.forEach { ingredients ->
            ingredients.allergens.forEach {
                allergens.compute(it) { _, value ->
                    val newSet = value ?: ingredients.foods
                    newSet.intersect(ingredients.foods)
                }
            }
        }

        // Narrow down among known allergens
        var knownAllergens = allergens.filter { it.value.size == 1 }
                .mapValues { it.value.first() }
                .toMutableMap()
        val unknown = allergens.filter { it.value.size > 1 }.toMutableMap()
        while (unknown.isNotEmpty()) {
            val toRemove = mutableListOf<String>()
            for (entry in unknown) {
                val narrowed = entry.value - entry.value.intersect(knownAllergens.values.toSet())
                if (narrowed.size == 1) {
                    knownAllergens[entry.key] = narrowed.first()
                    toRemove.add(entry.key)
                }
            }
            toRemove.forEach { unknown.remove(it) }
        }

        this.allergens = knownAllergens
    }

}