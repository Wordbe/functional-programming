package functor

sealed class BinaryTree<out A> : Functor<A> {
    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): BinaryTree<B>
}

object EmptyBinaryTree : BinaryTree<kotlin.Nothing>() {
    override fun toString(): String = "EmptyTree"

    override fun <B> fmap(f: (kotlin.Nothing) -> B): BinaryTree<B> = EmptyBinaryTree
}

data class Node<out A>(val value: A, val leftTree: BinaryTree<A>, val rightTree: BinaryTree<A>) : BinaryTree<A>() {
    override fun toString(): String = "(Node $value $leftTree $rightTree)"

    override fun <B> fmap(f: (A) -> B): BinaryTree<B> =
        Node(f(value), leftTree.fmap(f), rightTree.fmap(f))
}

fun <T> treeOf(value: T, leftTree: BinaryTree<T> = EmptyBinaryTree, rightTree: BinaryTree<T> = EmptyBinaryTree): BinaryTree<T> =
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