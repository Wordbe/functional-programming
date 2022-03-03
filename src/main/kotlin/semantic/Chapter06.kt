package semantic

data class User(val name: String, val age: Int)

// Pattern Matching
fun checkCondition(value: Any) = when {
    value == "kotlin" -> "kotlin"
    value in 1..10 -> "1..10"
    value === User("Joe", 76) -> "=== User" // 객체의 참조 값을 비교
    value == User("Joe", 76) -> "== User(Joe, 76)" // 객체의 값을 비교
    value is User -> "is User" // 객체의 타입을 비교
    else -> "SomeValue"
}