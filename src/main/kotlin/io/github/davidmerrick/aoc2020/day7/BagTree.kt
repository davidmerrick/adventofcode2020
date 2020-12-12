package io.github.davidmerrick.aoc2020.day7

import java.io.InputStream

class BagTree(private val input: Map<String, Set<BagNode>>) {

    fun traverseChildCounts(bagNode: BagNode): Int {
        if (input[bagNode.color]?.isEmpty() == true) { return 0 }

        return input[bagNode.color]!!
            .map { it.count + it.count * traverseChildCounts(it) }
            .sum()
    }

    companion object {
        fun parse(file: InputStream): BagTree {
            val colorMap = mutableMapOf<String, Set<BagNode>>()
            file.bufferedReader()
                .readLines()
                .forEach { line ->
                    val rootColor = getRootColor(line)
                    val children = getContainsBags(line)
                    if(children.isEmpty()){
                        colorMap[rootColor] = children
                    }

                    children.forEach { child ->
                        colorMap.compute(rootColor) { _, value ->
                            val newVal = (value ?: setOf()).toMutableSet()
                            newVal.add(child)
                            newVal.toSet()
                        }
                    }
                }
            return BagTree(colorMap.toMap())
        }

        private fun getRootColor(line: String): String {
            val regex = "^([a-z ]*) bags contain".toRegex()
            return regex.find(line)!!
                .groups[1]!!
                .value
                .trim()
        }

        private fun getContainsBags(line: String): Set<BagNode> {
            if (line.contains("contain no other bags")) {
                return emptySet()
            }

            val regex = "contain ([^.]*)\\.$".toRegex()
            return regex.find(line)!!
                .groups[1]!!
                .value
                .split(",")
                .map { parseBagNode(it) }
                .toSet()
        }

        private fun parseBagNode(rule: String): BagNode {
            val bagString = rule.trim()
            val color = bagString
                .replace(Regex("^[\\d]* "), "")
                .replace(Regex(" bag[s]?$"), "")
            val count = Regex("^([\\d]) ")
                .find(bagString)!!.groups[1]!!
                .value
                .toInt()
            return BagNode(color, count)
        }
    }
}
