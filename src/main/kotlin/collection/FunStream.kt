package collection

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>()
}

fun <T> FunStream<T>.getHead(): T = when (this) {
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
    FunStream.Nil -> throw NoSuchElementException()
    is FunStream.Cons -> tail()
}

// mempty
fun <T> FunStream<T>.mempty(): FunStream<T> = FunStream.Nil

// mappend
infix fun <T> FunStream<T>.mappend(other: FunStream<T>): FunStream<T> = when (this) {
    FunStream.Nil -> other
    is FunStream.Cons -> FunStream.Cons(head) { tail().mappend(other) }
}

// funStreamOf
fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

private fun <T> Array<T>.toFunStream(): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> this.copyOfRange(1, this.size).toFunStream()
}