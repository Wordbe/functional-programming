package semantic

class Box<T>(t: T) {
    var value = t
}

fun <T> head(list: List<T>): T {
    if (list.isEmpty()) {
        throw NoSuchElementException()
    }
    return list[0]
}