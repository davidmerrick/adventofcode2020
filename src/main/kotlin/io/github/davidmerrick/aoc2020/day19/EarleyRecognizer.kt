package io.github.davidmerrick.aoc2020.day19

class EarleyRecognizer(private val grammar: Set<Rule>) {

    // Note: Each rule in a state set item should be unique
    private lateinit var earleyStateSets: MutableList<MutableList<EarleyState>>

    private fun initState() {
        val initialState = grammar
            .filter { it.id == 0 } // Fetch first ids
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
        for (i in input.indices) {
            val stateSet = getStateSet(i)
            var j = 0
            while (j < stateSet.size) {
                if (stateSet[j].isComplete) {
                    completer(stateSet[j], j)
                } else {
                    if (stateSet.getOrNull(j)?.rule?.isTerminal == true) {
                        scanner(input[i], stateSet[j], i)
                    } else {
                        predictor(stateSet[j], i)
                    }
                }
                j++
            }
        }

        return true
    }

    /**
     * The symbol at the right of the fat dot is non-terminal.
     * We add the the corresponding rules to the current state set.
     */
    private fun predictor(state: EarleyState, k: Int) {
        val ruleId = Character.getNumericValue(state.rule.productions[state.fatDot])
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
        if(state.rule.productions[state.fatDot] == input){
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
        TODO()
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
