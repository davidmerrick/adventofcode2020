package io.github.davidmerrick.aoc2020.day4

data class Passport(
    val byr: String?,
    val iyr: String?,
    val eyr: String?,
    val hgt: String?,
    val hcl: String?,
    val ecl: String?,
    val pid: String?,
    val cid: String?,
) {
    private val requiredFields = listOf(
        byr,
        iyr,
        eyr,
        hgt,
        hcl,
        ecl,
        pid
    )

    fun isValid(): Boolean {
        return requiredFields.all { it != null }
    }
}
