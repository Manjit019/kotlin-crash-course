/*
    ! Taking Input in Kotlin :

    - Common ways to take input in Kotlin:
        1. readLine()
        2. readln()
        3. readlnOrNull()

    NOTE:
    - There is NO simple "read()" function for standard console input in Kotlin.
      (read() exists for streams, but not for basic console input like Java Scanner)

    --------------------------------------------------

    - readLine():
        * Reads a line from standard input
        * Returns a String? (nullable)
        * Returns null if input stream is closed

    - readln():
        * Reads a line from standard input
        * Returns a non-null String
        * Throws exception if input is closed

    - readlnOrNull():
        * Reads a line from standard input
        * Returns a String? (nullable)
        * Safer version when input might be absent

    --------------------------------------------------

    syntax:
        val input = readLine()
        val input = readln()
        val input = readlnOrNull()

    --------------------------------------------------

    ? Example:
    fun main() {
        println("Enter your name:")

        val name = readln()   // recommended in modern Kotlin

        println("Hello, $name!")
    }
*/

//Code Example : 

fun main() {
    print("Enter your name: ");
    val name = readln();
    println("Hello, $name!");
}