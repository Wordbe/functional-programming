package functor

data class UnaryFunction<in T, out R>(val g: (T) -> R) : Functor<R> {
    override fun <R2> fmap(f: (R) -> R2): UnaryFunction<T, R2> {
        return UnaryFunction { x: T -> f(g(x)) }
    }

    fun invoke(input: T): R = g(input)
}

fun main() {
    val f = { a: Int -> a + 1 }
    val g = { b: Int -> b * 2 }

    val fg = UnaryFunction(g).fmap(f)
    println(fg.invoke(5))

    val f2 = { a: Int -> a * 2 }
    val g2 = { b: Int -> Just(b) }
    val f2g2 = UnaryFunction(f2).fmap(g2)
    println(f2g2.invoke(5))
}