package io.github.davidmerrick.aoc2020.day13

import kotlin.math.ceil

data class Bus(val id: Int) {

    fun getClosestTimeTo(goalTime: Int): Int {
        return (ceil(goalTime.toDouble()/id.toDouble())*id).toInt()
    }
}
