package semantic

import java.io.FileInputStream
import java.util.Properties

// Kotlin standard library
// let
fun printPersonName(person: Person?) {
    val name = person?.let { it.firstName + it.lastName } ?: "default name"
    println(name)
}

data class User1(var name: String, var age: Int)

// with
fun changeUser() {
    val user = User1("FP", 30)
    val result = with(user) {
        name = "Kotlin"
        age = 10
        this
    }
}

// run
fun practiceRun() {
    val user = User1("FP", 30)
    val result = user.run {
        name = "Kotlin"
        age = 10
        this
    }

    val user2 = run {
        val name = "Kotlin"
        val age = 10
        User1(name, age)
    }
}

// apply, also
fun changeAndPrintUser() {
    val user1 = User1("FP", 30)
    val result1 = user1.apply {
        name = "Kotlin"
        age = 10
    }
    print(result1)

    val user2 = User1("FP", 30)
    val result2 = user2.also {
        it.name = "Kotlin"
        it.age = 10
    }
    print(result2)
}

// use
fun useFile() {
    val property = Properties()
    FileInputStream("config.properties").use {
        property.load(it)
    } // FileInputStream 이 자동으로 close
}

