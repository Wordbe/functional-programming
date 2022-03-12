package functor

sealed class Either<out L, out R> : Functor<R> {
    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>
}

data class Left<out L>(val value: L): Either<L, Nothing>() {
    override fun <R2> fmap(f: (Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R): Either<Nothing, R>() {
    override fun <R2> fmap(f: (R) -> R2): Either<Nothing, R2> = Right(f(value))
}

fun main() {
    println(divideTenByN(5))
    println(divideTenByN(0))
    println(divideTenByN(5).fmap { it * 2 })
    println(divideTenByN(0).fmap { it * 2 })
}

fun divideTenByN(n: Int): Either<String, Int> =
    if (n == 0) Left("Cannot divide by zero")
    else Right(10 / n)