package io.github.davidmerrick.aoc2020.day4

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PassportPart2Test {

    @Test
    fun `Validate example passports`(){
        val passports = readPassport("day4-part2.txt")
        val validCount = passports.count { it.isValid() }
        validCount shouldBe 4
    }

    @Test
    fun `Validate all passports with part2`(){
        val passports = readPassport("day4-full.txt")
        val validCount = passports.count { it.isValid() }
        println(validCount)
    }

    private fun readPassport(fileName: String): List<PassportPart2> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readText()
            .split("\n\n")
            .map {
                it.split(Regex("[\n ]"))
                    .filterNot { entry -> entry.isEmpty() }
                    .associate { entry ->
                        val pair = entry.split(":")
                        pair[0] to pair[1]
                    }
            }
            .map {
                PassportPart2(
                    byr = it["byr"]?.toInt(),
                    iyr = it["iyr"]?.toInt(),
                    eyr = it["eyr"]?.toInt(),
                    hgt = it["hgt"],
                    hcl = it["hcl"],
                    ecl = it["ecl"],
                    pid = it["pid"],
                    cid = it["cid"],
                )
            }
    }
}
