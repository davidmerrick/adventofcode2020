package io.github.davidmerrick.aoc2020.day23

class CircularLookupList {

    private val nodeMap: MutableMap<Int, LookupNode> = mutableMapOf()

    fun add(value: Int) {
        if (nodeMap.isNotEmpty()) {
            throw IllegalArgumentException("List is initialized. Use addAfter instead")
        }
        nodeMap[value] = LookupNode(value, null, null)
    }

    fun addAfter(value: Int, toAdd: Int) {
        val node = nodeMap[value]!!
        val next = node.next
                ?: node.value // If this is the first node, next will be null. In this case, link back to it.

        val newNode = LookupNode(toAdd, node.value, next)
        nodeMap[toAdd] = newNode

        // Point node to new one
        nodeMap.compute(value) { _, v ->
            v!!.copy(next = newNode.value, previous = v.previous ?: newNode.value)
        }

        // Point next one to this one
        nodeMap.compute(newNode.next!!) { _, v ->
            v!!.copy(previous = newNode.value)
        }
    }

    fun getNext(value: Int) = nodeMap[value]!!.next!!

    fun remove(value: Int) {
        val previous = nodeMap[value]!!.previous
        val next = nodeMap[value]!!.next
        nodeMap.compute(previous!!) { _, v ->
            v!!.copy(next = next)
        }
        nodeMap.compute(next!!) { _, v ->
            v!!.copy(previous = previous)
        }
        nodeMap.remove(value)
    }

    fun contains(value: Int) = nodeMap.containsKey(value)

    fun max() = nodeMap.values.map { it.value }.maxOrNull()!!
}