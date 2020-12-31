package io.github.davidmerrick.aoc2020.day19

class EarleyRecognizer(private val grammar: Set<Rule>) {

    // Note: Each rule in a state set item should be unique
    private lateinit var earleyStateSets: MutableList<MutableList<EarleyState>>

    private fun initState() {
        val initialState = grammar
            .filter { it.id == "0" } // Fetch first ids
            .map { EarleyState(it, 0, 0) }
            .toMutableList()
        earleyStateSets = mutableListOf(initialState)
    }

    private fun getStateSet(k: Int): List<EarleyState> {
        if (k == earleyStateSets.size) {
            earleyStateSets.add(k, mutableListOf())
        }
        return earleyStateSets[k]
    }

    fun isValid(input: String): Boolean {
        initState()

        // Loop over input
        for (k in (0..input.length)) {
            val stateSet = getStateSet(k)
            var j = 0
            while (j < stateSet.size) {
                if (stateSet[j].isComplete) {
                    completer(stateSet[j], k)
                } else {
                    if (stateSet.getOrNull(j)?.rule?.isTerminal == true) {
                        input.getOrNull(k)?.let { scanner(it, stateSet[j], k) }
                    } else {
                        predictor(stateSet[j], k)
                    }
                }
                j++
            }
        }

        return earleyStateSets.last().any { it.rule.id == "0" && it.isComplete }
    }

    /**
     * The symbol at the right of the fat dot is non-terminal.
     * We add the the corresponding rules to the current state set.
     */
    private fun predictor(state: EarleyState, k: Int) {
        val ruleId = state.rule.productions[state.fatDot]
        grammar.filter { it.id == ruleId }
            .map { EarleyState(it, 0, k) }
            .forEach { addStateItem(it, k) }
    }

    /**
     * The symbol at the right of the fat dot is terminal.
     * We check if the input matches this symbol.
     * If it does, we add this item (advanced one step) to the next state set.
     */
    private fun scanner(input: Char, state: EarleyState, k: Int) {
        if (state.rule.productions[state.fatDot] == input.toString()) {
            val newItem = state.copy(fatDot = state.fatDot + 1)
            addStateItem(newItem, k + 1)
        }
    }

    /**
     * There is nothing at the right of the fat dot.
     * This means we have a successful partial parse.
     * We look for the parent items, and add them (advanced one step)
     * to this state set.
     */
    private fun completer(state: EarleyState, k: Int) {
        earleyStateSets[state.origin]
            .filter { item -> !item.isComplete && item.rule.productions[item.fatDot] == state.rule.id }
            .map { it.copy(fatDot = it.fatDot + 1) }
            .forEach { addStateItem(it, k) }
    }

    private fun addStateItem(state: EarleyState, k: Int) {
        if (k == earleyStateSets.size) {
            earleyStateSets.add(mutableListOf())
        }
        if (!earleyStateSets[k].contains(state)) {
            earleyStateSets[k].add(state)
        }
    }
}
