package io.github.davidmerrick.aoc2020.day2

object PasswordValidator {

    fun validate(
        password: String,
        rules: PasswordRules
    ): Boolean {
        val count = password.count { it == rules.inputCharacter }
        return count >= rules.minCount && count <= rules.maxCount
    }
}
