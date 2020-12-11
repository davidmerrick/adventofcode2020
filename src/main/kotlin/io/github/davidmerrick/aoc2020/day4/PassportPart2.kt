package io.github.davidmerrick.aoc2020.day4

data class PassportPart2(
    val byr: Int?,
    val iyr: Int?,
    val eyr: Int?,
    val hgt: String?,
    val hcl: String?,
    val ecl: String?,
    val pid: String?,
    val cid: String?,
) {
    private fun validateByr(): Boolean {
        return byr != null && byr in 1920..2002
    }

    private fun validateIyr(): Boolean {
        return iyr != null && iyr in 2010..2020
    }

    private fun validateEyr(): Boolean {
        return eyr != null && eyr in 2020..2030
    }

    private fun validateHgt(): Boolean {
        if (hgt == null) return false

        val value =
            try {
                hgt.substring(0, hgt.length - 2)
                    .toInt()
            } catch (e: Exception){
                return false
            }
        return when (hgt.substring(hgt.length - 2, hgt.length)) {
            "in" -> {
                value in 59..76
            }
            "cm" -> {
                value in 150..193
            }
            else -> false
        }
    }

    private fun validatePid(): Boolean {
        return pid?.matches(Regex("\\d".repeat(9))) ?: false
    }

    private fun validateHcl(): Boolean {
        return hcl?.matches(Regex("#[0-9a-f]{6}")) ?: false
    }

    private fun validateEcl(): Boolean {
        val validValues = listOf(
            "amb",
            "blu",
            "brn",
            "gry",
            "grn",
            "hzl",
            "oth"
        )
        return ecl != null && ecl in validValues
    }

    fun isValid(): Boolean {
        return validateByr()
            && validateIyr()
            && validateEyr()
            && validateHgt()
            && validateHcl()
            && validateEcl()
            && validatePid()

    }
}
