package io.github.davidmerrick.aoc2020.day6

class CustomsGroup(private val input: List<String>) {

    val uniqueQuestions: Int by lazy {
//        input.flatMap { it.toList() }
//            .toSet()
//            .count()
        TODO("Compiler issue. Fix")
    }

    val sameQuestions: Int by lazy {
        input.map { it.toList().toSet() }
            .reduce { a, b -> a.intersect(b) }
            .size
    }
}
