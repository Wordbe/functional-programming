package semantic

// 객체 분해
fun decomposeObject() {
    val user: User = User("kotlin", 28)
    val (name, age) = user
    println("name: $name")
    println("age: $age")
}