package semantic

// covariance 공변
interface Box1<out T> {
    fun read(): T
    // fun write(value: T) // compile error
}

// contravariance 반공변
interface Box2<in T> {
    // fun read(): T // compile error
    fun write(value: T)
}
