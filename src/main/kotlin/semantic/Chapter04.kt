package semantic

// Interface

//fun run() {
//    val kotlin = Kotlin()
//    kotlin.printFoo()
//    kotlin.printBar()
//    kotlin.foo
//    kotlin.bar
//}

interface Foo {
    fun printFoo() {
        println("Foo")
    }

    fun printKotlin()

    val foo: Int
        get() = 3
}

interface Bar {
    fun printBar()

    fun printKotlin() {
        println("Bar Kotlin")
    }

    val bar: Int
}

class Kotlin : Foo, Bar {
    override fun printBar() {
        println("override printBar")
    }

    override fun printKotlin() {
        super<Foo>.printFoo()
        super<Bar>.printKotlin()
    }

    override val bar: Int = 5
}