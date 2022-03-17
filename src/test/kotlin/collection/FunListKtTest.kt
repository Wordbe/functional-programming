package collection

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.random.Random

// property based tests

internal class FunListKtTest: StringSpec({
    "testReverse" {
        forAll { a: String -> reverse(reverse(a)) == a }
    }

    "reverseFunList" {
        forAll(500, FunIntListGen()) { list: FunList<Int> ->
            list.reverse().reverse() == list
        }
    }
})

class FunIntListGen: Gen<FunList<Int>> {
    override fun constants(): Iterable<FunList<Int>> = listOf(FunList.Nil)
    override fun random(): Sequence<FunList<Int>> = generateSequence {
        val listSize = Random.nextInt(0, 10)
        val values = Gen.int().random().take(listSize)
        funListOf(*values.toList().toTypedArray())
    }
}