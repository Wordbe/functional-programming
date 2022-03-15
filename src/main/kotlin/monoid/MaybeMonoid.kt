package monoid

import functor.Just
import functor.Maybe

object MaybeMonoid {
    fun <T> monoid(inValue: Monoid<T>) = object : Monoid<Maybe<T>> {
        override fun mempty(): Maybe<T> = functor.Nothing

        override fun mappend(m1: Maybe<T>, m2: Maybe<T>): Maybe<T> = when {
            m1 is functor.Nothing -> m2
            m2 is functor.Nothing -> m1
            m1 is Just && m2 is Just -> Just(inValue.mappend(m1.value, m2.value))
            else -> functor.Nothing
        }
    }
}

fun main() {
    val x = Just(1)
    val y = Just(2)
    val z = Just(3)

    MaybeMonoid.monoid(ProductMonoid()).run {
        println(mappend(mempty(), x) == x)
        println(mappend(x, mempty()) == x)
        println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z)))
    }

    MaybeMonoid.monoid(SumMonoid()).run {
        println(mappend(mempty(), x) == x)
        println(mappend(x, mempty()) == x)
        println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z)))
    }
}