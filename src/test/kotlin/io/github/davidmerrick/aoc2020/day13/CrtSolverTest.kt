package io.github.davidmerrick.aoc2020.day13

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

/**
 * Unit tests around CRT Solver
 */
class CrtSolverTest {

    @Test
    fun `Test product`(){
        val congruences = listOf(
            Congruence(1, 3),
            Congruence(1, 4),
            Congruence(1, 5),
            Congruence(0, 7),
        )
        CrtSolver(congruences).product shouldBe 420
    }

    @Test
    fun `Test product 2`(){
        val congruences = listOf(
            Congruence(2, 3),
            Congruence(3, 5),
            Congruence(2, 7),
        )
        CrtSolver(congruences).product shouldBe 105
    }

    @Test
    fun `Test inverses`(){
        CrtSolver.inverse(140, 3) shouldBe 2
        CrtSolver.inverse(105, 4) shouldBe 1
        CrtSolver.inverse(84, 5) shouldBe 4
        CrtSolver.inverse(60, 7) shouldBe 2
    }

    @Test
    fun `Partial products`(){
        CrtSolver.partialProduct(
            420, Congruence(1, 3)) shouldBe 140
        CrtSolver.partialProduct(
            420, Congruence(1, 4)) shouldBe 105
        CrtSolver.partialProduct(
            420, Congruence(1, 5)) shouldBe 84
        CrtSolver.partialProduct(
            420, Congruence(0, 7)) shouldBe 60
    }
}
