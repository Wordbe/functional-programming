package functor

import curry.curried

sealed class Either<out L, out R> : Functor<R> {
    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>

    companion object
}

data class Left<out L>(val value: L): Either<L, kotlin.Nothing>() {
    override fun toString(): String = "Left($value)"

    override fun <R2> fmap(f: (kotlin.Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R): Either<kotlin.Nothing, R>() {
    override fun toString(): String = "Right($value)"

    override fun <R2> fmap(f: (R) -> R2): Either<kotlin.Nothing, R2> = Right(f(value))
}

fun divideTenByN(n: Int): Either<String, Int> =
    if (n == 0) Left("Cannot divide by zero")
    else Right(10 / n)

// Applicative functor
fun <A> Either.Companion.pure(value: A) = Right(value)

infix fun <L, A, B> Either<L, (A) -> B>.apply(f: Either<L, A>): Either<L, B> =
    when (this) {
        is Left -> this
        is Right -> f.fmap(value)
    }

fun main() {
    println(divideTenByN(5))
    println(divideTenByN(0))
    println(divideTenByN(5).fmap { it * 2 })
    println(divideTenByN(0).fmap { it * 2 })

    // Applicative functor
    println(Either.pure(10))
    println(Either.pure { x: Int -> x * 2 })

    println(Either.pure { x: Int -> x * 2 } apply Left("error"))
    println(Either.pure { x: Int -> x * 2 } apply Right(10))
    println(Either.pure({ x: Int, y: Int -> x * y }.curried())
            apply Left("error")
            apply Right(10))
}
