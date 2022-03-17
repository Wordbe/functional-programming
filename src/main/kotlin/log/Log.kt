package log

import collection.FunStream
import collection.funStreamOf
import collection.mappend
import collection.mempty
import monad.Monad

//fun <T, R> Pair<T, String>.applyLog(f: (T) -> Pair<R, String>): Pair<R, String> {
//    val applied = f(first)
//    return Pair(applied.first, second + applied.second)
//}

fun addFive(it: Int) = it + 5

fun square(it: Int) = it * it

fun isGreaterThan50(it: Int) = it > 50


// For lazy evaluation
fun <T, R> Pair<T, FunStream<String>>.applyLog(f: (T) -> Pair<R, FunStream<String>>): Pair<R, FunStream<String>> {
    val applied = f(first)
    return Pair(applied.first, second mappend applied.second)
}

// WriterMonad
data class WriterMonad<out T>(val value: T, val logs: FunStream<String>) : Monad<T> {
    override fun <V> pure(value: V): WriterMonad<V> = WriterMonad(value, logs.mempty())

    override fun <R> flatMap(f: (T) -> Monad<R>): WriterMonad<R> {
        val applied = f(value) as WriterMonad<R>
        return WriterMonad(applied.value, logs mappend applied.logs)
    }
}

infix fun <T> T.withLog(log: String): WriterMonad<T> =
    WriterMonad(this, funStreamOf(log))

fun main() {
//    funStreamOf(1, 2, 3)
//        .fmap { addFive(it) withLog "$it + 5" }
//        .fmap { square(it) withLog "$it * $it" }
//        .fmap { isGreaterThan50(it) withLog "$it > 50" }
}

