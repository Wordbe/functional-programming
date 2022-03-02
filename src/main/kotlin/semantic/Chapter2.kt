package semantic

fun run() {
    // sum
    val value = sum(5, 10) { x, y -> x + y }
    println(value)

    // extension
    val result = 10.product(2)
    println(result)
}

fun sum(x: Int, y: Int, calculate: (Int, Int) -> Int): Int {
    return calculate(x, y)
}

fun Int.product(value: Int): Int {
    return this * value
}