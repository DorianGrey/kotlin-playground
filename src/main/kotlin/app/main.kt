package app

/**
 * Testing some java POJO interop...
 */
fun javaInteropPOJO() {
    val user = User("Urql Random") // Look 'ma, no `new` required!
    println("""Hello ${user.name}! Your ID is ${user.id}.""") // Look 'ma, no `get` required!
    user.name = user.name.split(" ").last() // Look 'ma, no `set` required!
    println("""Username was changed to '${user.name}'.""")
}

fun main(args: Array<String>) {
    javaInteropPOJO()
}