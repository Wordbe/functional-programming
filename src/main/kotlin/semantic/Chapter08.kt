package semantic;

fun collection() {
    val list: List<Int> = listOf(1, 2, 3)
    // list.add(4) compile error
    val newList = list.plus(4)

    val map1 = mapOf(1 to "One", 2 to "Two")
    val map2 = map1.plus(Pair(3, "Three"))
}

fun mutableCollection() {
    val list: MutableList<Int> = mutableListOf(1, 2, 3)
    list.add(4)

    val mutableMap = mutableMapOf(1 to "One", 2 to "Two")
    // mutableMap.put(3, "Three")
    mutableMap[3] = "Three"
    mutableMap.clear()
}
