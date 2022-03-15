package monoid

import collection.FunList
import collection.foldRight
import collection.funListOf

interface Monoid<T> {
    fun mempty(): T

    fun mappend(m1: T, m2: T): T
}

fun <T> Monoid<T>.mconcat(list: FunList<T>): T = list.foldRight(mempty(), ::mappend)

fun main() {
    println(ProductMonoid().mconcat(funListOf(1, 2, 3, 4, 5)))
    println(SumMonoid().mconcat(funListOf(1, 2, 3, 4, 5)))
}