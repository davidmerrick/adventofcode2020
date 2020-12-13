package io.github.davidmerrick.aoc2020.testutil

import kotlin.reflect.KClass

object TestUtil {

    fun readLines(thisClass: KClass<out Any>, fileName: String): List<String>{
        return thisClass.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
    }
}
