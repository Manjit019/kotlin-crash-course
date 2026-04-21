/*
    ! Loops in Kotlin :

    - Loops are used to execute a block of code repeatedly
      based on a condition or range.

    - Kotlin provides 3 main loops:
        1. for loop
        2. while loop
        3. do-while loop

    --------------------------------------------------

    ! 1. for loop :

    - Used to iterate over:
        ✔ ranges
        ✔ arrays
        ✔ collections
        ✔ strings

    ? Syntax:
        for (item in collection) {
            // code
        }

    ? Range example:
        for (i in 1..5) {
            println(i)
        }

    ? Until (excludes last value):
        for (i in 1 until 5) {
            println(i)
        }

    ? Step (increment by custom value):
        for (i in 1..10 step 2) {
            println(i)
        }

    ? DownTo (reverse loop):
        for (i in 10 downTo 1) {
            println(i)
        }

    --------------------------------------------------

    ! 2. while loop :

    - Entry-controlled loop (condition checked first)
    - Runs only if condition is true

    ? Syntax:
        while (condition) {
            // code
        }

    ? Example:
        var i = 1

        while (i <= 5) {
            println(i)
            i++
        }

    --------------------------------------------------

    ! 3. do-while loop :

    - Exit-controlled loop (runs at least once)
    - Condition checked after execution

    ? Syntax:
        do {
            // code
        } while (condition)

    ? Example:
        var i = 1

        do {
            println(i)
            i++
        } while (i <= 5)

    --------------------------------------------------

    ! Looping over data structures :

    ? Array:
        val arr = arrayOf(1, 2, 3)

        for (item in arr) {
            println(item)
        }

    ? String:
        for (ch in "Kotlin") {
            println(ch)
        }

    ? List:
        val list = listOf(10, 20, 30)

        for (item in list) {
            println(item)
        }

    ? Map:
        val map = mapOf(1 to "A", 2 to "B")

        for ((key, value) in map) {
            println("$key -> $value")
        }

    --------------------------------------------------

    ! Important Points:

    - for loop = best for known ranges/collections
    - while loop = condition-based repetition
    - do-while = runs at least once
*/

// Code Example:
fun main() {

    //! For loop

    //? Range example:
    // println("Range example:")
    // for (i in 1..5) {
    //     println(i)
    // } // print 1 to 5

    //? Until (excludes last value):
    // println("Until (excludes last value):")
    // for (i in 1 until 5) {
    //     println(i)
    // }

    //? Step (increment by custom value):
    // println("Step (increment by custom value):")
    // for (i in 1..10 step 2) {
    //     println(i)
    // }

    //? DownTo (reverse loop):
    // println("DownTo (reverse loop):")
    // for (i in 10 downTo 1) {
    //     println(i)
    // }


    //! While loop
    // println("While loop:")
    // var i = 1
    // while (i <= 5) {
    //     println(i)
    //     i++
    // }

    //! Do-while loop
    // println("Do-while loop:")
    // var j = 1
    // do {
    //     println(j)
    //     j++
    // } while (j <= 5)


    //? Looping over data structures
    println("Looping over data structures:")
    val arr = arrayOf(1, 2, 3)
    println("Array:")
    for (item in arr) {
        println(item)
    }

    println("String:")
    val str = "Kotlin"
    for (ch in str) {
        println(ch)
    }

    println("List:")
    val list = listOf(10, 20, 30)
    for (item in list) {
        println(item)
    }

    println("Map:")
    val map = mapOf(1 to "A", 2 to "B")
    for ((key, value) in map) {
        println("$key -> $value")
    }
}