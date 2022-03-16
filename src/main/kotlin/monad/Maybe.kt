package monad

import curry.curried

sealed class Maybe<out A> : Monad<A> {
    companion object {
        fun <V> pure(value: V): Maybe<V> = Just(0).pure(value)
    }

    override fun <V> pure(value: V): Maybe<V> = Just(value)

    override fun <B> fmap(f: (A) -> B): Maybe<B> = super.fmap(f) as Maybe<B>

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Maybe<B> = when (this) {
        is Just -> try {
            f(value) as Maybe<B>
        } catch (e: ClassCastException) {
            Nothing
        }
        is Nothing -> Nothing
    }
}

data class Just<out A>(val value: A) : Maybe<A>() {
    override fun toString(): String = "Just($value)"
}

object Nothing : Maybe<kotlin.Nothing>() {
    override fun toString(): String = "Nothing"
}

infix fun <A, B> Maybe<(A) -> B>.apply(f: Maybe<A>): Maybe<B> = when (this) {
    is Just -> f.fmap(value)
    is Nothing -> Nothing
}

infix fun <F, G, R> ((F) -> Monad<R>).compose(g: (G) -> Monad<F>): (G) -> Monad<R> {
    return { gInput: G -> g(gInput) flatMap this }
}



fun main() {
    println(Just(10).fmap { it + 10 })
    println(Nothing.fmap { x: Int -> x + 10 })

    // pure, apply
    println(Maybe.pure(10))
    println(Maybe.pure { x: Int -> x * 2 })

    // apply
    println(Maybe.pure { x: Int -> x * 2 } apply Just(10))
    println(Maybe.pure({ x: Int, y: Int -> x * y }.curried())
            apply Just(10)
            apply Just(20))

    // flatMap
    println("flatMap")
    println(Just(10).flatMap { x -> Maybe.pure(x * 2) })
    println(monad.Nothing.flatMap { x: Int -> Maybe.pure(x * 2) })
    println(Just(Just(10)).flatMap { m -> m.fmap { x -> x * 2 } })

    // leadTo
    println(Just(10).leadTo(Just(20)))

    // access nullable value
    println("access nullable value")
    val a = A2(Just(B2(Just(C2(Just(D2(Just("some value"))))))))
    val result = when (val maybe = getValueOfD2(a)) {
        is Just -> maybe.value
        is Nothing -> ""
    }

    println(result)
}

fun getValueOfD2(a: A2): Maybe<String> =
    a.b.flatMap { it.c }
        .flatMap { it.d }
        .flatMap { it.value }

class A2(val b: Maybe<B2>)
class B2(val c: Maybe<C2>)
class C2(val d: Maybe<D2>)
class D2(val value: Maybe<String>)


