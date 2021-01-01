package io.github.davidmerrick.aoc2020.day20

class Image(val tiles: List<Tile>) {

    val corners: Set<Tile>
        get() = tiles.filter { it.edgesMatch(getOtherEdges(it.id)) == 2 }.toSet()

    fun getOtherEdges(excludeTile: Int): Set<String> {
        return tiles.filterNot { it.id == excludeTile }
                .flatMap { it.edges }
                .toSet()
    }
}