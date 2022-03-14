package applicativefunctor

import functor.Functor

interface Applicative<out A> : Functor<A>{
    fun <V> pure(value: V): Applicative<V>

    infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}