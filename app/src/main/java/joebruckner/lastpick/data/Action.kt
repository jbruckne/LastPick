package joebruckner.lastpick.data

data class Action(
        val name: String
) {

    companion object {
        const val UPDATE = "shuffle"
    }
}