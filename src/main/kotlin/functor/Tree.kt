package functor

sealed class Tree<out A> : Functor<A> {
    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): Tree<B>
}

object EmptyTree : Tree<kotlin.Nothing>() {
    override fun toString(): String = "EmptyTree"

    override fun <B> fmap(f: (kotlin.Nothing) -> B): Tree<B> = EmptyTree
}

data class Node<out A>(val value: A, val leftTree: Tree<A>, val rightTree: Tree<A>) : Tree<A>() {
    override fun toString(): String = "(Node $value $leftTree $rightTree)"

    override fun <B> fmap(f: (A) -> B): Tree<B> =
        Node(f(value), leftTree.fmap(f), rightTree.fmap(f))
}

fun <T> treeOf(value: T, leftTree: Tree<T> = EmptyTree, rightTree: Tree<T> = EmptyTree): Tree<T> =
    Node(value, leftTree, rightTree)

fun main() {
    val tree = treeOf(1,
        treeOf(2,
            treeOf(3), treeOf(4)),
        treeOf(5,
            treeOf(6), treeOf(7)))

    println(tree)

    val transformedTree = tree.fmap { it + 1 }

    println(transformedTree)
}