package io.github.davidmerrick.aoc2020.day13

/**
 * Chinese Remainder Theorem solver for congruences
 * https://en.wikipedia.org/wiki/Chinese_remainder_theorem
 */
class CrtSolver(private val congruences: List<Congruence>) {

    val product by lazy { congruences.map { it.mod }.reduce { a, b -> a * b } }

    fun solve(): Long {
        return computeSum() % product
    }

    private fun computeSum(): Long {
        return congruences.map {
            val partialProduct = partialProduct(product, it)
            val inverse = inverse(partialProduct, it.mod)
            partialProduct * inverse * it.value
        }.sum()
    }


    companion object {
        /**
         * Compute partial product
         */
        fun partialProduct(product: Long, congruence: Congruence): Long {
            return product/congruence.mod
        }

        /**
         * Compute Euclidian inverse
         * Pass the Congruence's mod for the value
         * https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
         */
        fun inverse(partialProduct: Long, value: Long): Long {
            val a = partialProduct % value
            return (1..partialProduct).firstOrNull {
                (a * it) % value == 1L
            } ?: 1L
        }
    }
}
