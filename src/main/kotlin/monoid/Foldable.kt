package monoid

import collection.FunList
import collection.funListOf

interface Foldable<out A> {
    fun <B> foldLeft(acc: B, f: (B, A) -> B): B

    fun <B> foldMap(f: (A) -> B, m: Monoid<B>): B = foldLeft(m.mempty()) { b, a -> m.mappend(b, f(a)) }
}

sealed class FoldableBinaryTree<out A> : Foldable<A> {
    override fun <B> foldLeft(acc: B, f: (B, A) -> B): B = when (this) {
        is FoldableEmptyTree -> acc
        is FoldableNode -> {
            val leftAcc = leftTree.foldLeft(acc, f)
            val rootAcc = f(leftAcc, value)
            rightTree.foldLeft(rootAcc, f)
        }
    }
}

data class FoldableNode<A>(val value: A,
                           val leftTree: FoldableBinaryTree<A> = FoldableEmptyTree,
                           val rightTree: FoldableBinaryTree<A> = FoldableEmptyTree) : FoldableBinaryTree<A>()
object FoldableEmptyTree : FoldableBinaryTree<Nothing>()

//fun <A> FoldableBinaryTree<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())
//fun <A> FoldableBinaryTree<A>.toFunList(): FunList<A> = foldMap({ funListOf(it) }, FunListMonoid())