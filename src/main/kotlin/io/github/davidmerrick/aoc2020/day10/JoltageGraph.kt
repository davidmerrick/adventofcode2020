package io.github.davidmerrick.aoc2020.day10

private const val MAX_STEP = 3

class JoltageGraph(
    private val adapters: List<Int>
) {
    private val nodes: Map<Int, Set<Int>>

    init {
        var nodesMap = mutableMapOf<Int, Set<Int>>()
        for(i in (0 until adapters.size-1)){
            val currentAdapter = adapters[i]
            nodesMap[currentAdapter] = getValidAdapters(currentAdapter)
        }
        nodes = nodesMap.toMap()
    }

    fun distinctPaths(source: Int, dest: Int): Long {
        if(source == dest){
            return 1L
        }
        return nodes[source]!!
            .map { distinctPaths(it, dest) }
            .sum()
    }

    private fun getValidAdapters(value: Int): Set<Int> {
        return adapters.filter { it > value && it <= value + MAX_STEP }
            .toSet()
    }
}
