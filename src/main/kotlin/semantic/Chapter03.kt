package semantic

import java.lang.Integer.parseInt

fun max(x: Int, y: Int): Int {
    val max: Int = if (x > y) x else y
    return max
}

fun doWhen(x: Int) {
    when (x) {
        1 -> println("x == 1")
        2, 3 -> println("x == 2 or 3")
        parseInt("4") -> println("x = 4")
        else -> println("else number")
    }

    val numString = when {
        x == 0 -> "zero"
        x > 0 -> "positive"
        else -> "negative"
    }
}
