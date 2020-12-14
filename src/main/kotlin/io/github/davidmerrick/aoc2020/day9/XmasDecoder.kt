package io.github.davidmerrick.aoc2020.day9

import com.github.shiguruikai.combinatoricskt.combinations
import java.lang.RuntimeException

class XmasDecoder(
    private val preambleSize: Int,
    private val input: List<Long>
) {

    /**
     * Returns value of first int
     * in input that is not the sum
     * of the previous n numbers in the preamble
     */
    fun findFirstAnomaly(): Long {
        val inspectRange = (preambleSize..input.size)
        for(i in inspectRange){
            val current = input[i]
            val sums = getSums(i - preambleSize)
            if(current !in sums){
                return current
            }
        }

        throw RuntimeException("No anomalies found")
    }

    fun findContiguousSet(goalSum: Long): List<Long> {
        for(i in input.indices){
            var sum = input[i]
            var j = i + 1
            while (sum < goalSum){
                sum += input[j]
                if(sum == goalSum){
                    return input.subList(i, j + 1)
                }
                j++
            }
        }

        throw RuntimeException("No set found")
    }

    private fun getSums(startIndex: Int): List<Long> {
        return getPreamble(startIndex)
            .combinations(2)
            .map { it.sum() }
            .toList()
    }

    private fun getPreamble(startIndex: Int): List<Long> {
        return input.subList(
            startIndex,
            startIndex + preambleSize
        )
    }
}
