package io.github.davidmerrick.aoc2020.day2

object PasswordValidatorPart2 {

    fun validate(
        password: String,
        rules: PasswordRulesPart2
    ): Boolean {
        var count = 0

        // Count lower index
        count += if (password[rules.lowerIndex] == rules.inputCharacter){
            1
        } else 0

        // Count lower index
        count += if (password[rules.upperIndex] == rules.inputCharacter){
            1
        } else 0

        return count == 1
    }
}
