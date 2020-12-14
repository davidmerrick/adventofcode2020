package io.github.davidmerrick.aoc2020.day10

private const val MAX_STEP = 3

class JoltageGraph(
    private val adapters: List<Int>
) {
    val nodes: Map<Int, Set<Int>>
    get() = nodesMap.toMap()
    private var nodesMap = mutableMapOf<Int, Set<Int>>()

    init {
        for(i in (0 until adapters.size-1)){
            val currentAdapter = adapters[i]
            nodesMap[currentAdapter] = getValidAdapters(currentAdapter)
        }
    }

    fun distinctPaths(): Int {
        TODO()
    }

    private fun getValidAdapters(value: Int): Set<Int> {
        return adapters.filter { it > value && it <= value + MAX_STEP }
            .toSet()
    }
}
