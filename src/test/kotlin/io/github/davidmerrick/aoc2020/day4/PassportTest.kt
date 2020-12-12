package io.github.davidmerrick.aoc2020.day4

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PassportTest {

    @Test
    fun `Validate example passports`(){
        val passports = readPassport("day4.txt")
        val validCount = passports.count { it.isValid() }
        validCount shouldBe 2
    }

    @Test
    fun `Validate part 1 input`(){
        val passports = readPassport("day4-full.txt")
        val validCount = passports.count { it.isValid() }
        println(validCount)
    }

    private fun readPassport(fileName: String): List<Passport> {
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
                Passport(
                    byr = it["byr"],
                    iyr = it["iyr"],
                    eyr = it["eyr"],
                    hgt = it["hgt"],
                    hcl = it["hcl"],
                    ecl = it["ecl"],
                    pid = it["pid"],
                    cid = it["cid"],
                )
            }
    }
}
