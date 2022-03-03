package semantic

data class Person(val firstName: String, val lastName: String, val age: Int)

enum class Error(val num: Int) {
    WARN(2) {
        override fun getErrorName(): String {
            return "WARN"
        }
    },
    ERROR(3) {
        override fun getErrorName(): String {
            return "ERROR"
        }
    },
    FAULT(1) {
        override fun getErrorName(): String {
            return "FAULT"
        }
    };

    abstract fun getErrorName(): String
}