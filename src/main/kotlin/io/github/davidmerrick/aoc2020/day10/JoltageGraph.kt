package io.github.davidmerrick.aoc2020.day10

private const val MAX_STEP = 3

class JoltageGraph(
    private val adapters: List<Int>
) {
    private val nodes: Map<Int, Set<Int>>
    private val visited: MutableMap<Int, Long> = mutableMapOf()

    init {
        var nodesMap = mutableMapOf<Int, Set<Int>>()
        for (i in (0 until adapters.size - 1)) {
            val currentAdapter = adapters[i]
            nodesMap[currentAdapter] = getValidAdapters(currentAdapter)
        }
        nodes = nodesMap.toMap()
    }

    fun distinctPaths(source: Int, dest: Int): Long {
        if (source == dest) return 1L

        if (!visited.containsKey(source)) {
            visited[source] = nodes[source]!!.sumOf { distinctPaths(it, dest) }
        }

        return visited[source]!!
    }

    private fun getValidAdapters(value: Int): Set<Int> {
        return adapters.filter { it > value && it <= value + MAX_STEP }
            .toSet()
    }
}
