/*
     ! KOTLIN — PURE FUNCTIONS
    
     * A pure function:
       - Always returns SAME output for SAME input
       - Has NO side effects (no printing, no modifying external state)
       - Does NOT depend on external state
    
     * An impure function:
       - Depends on external state (global variables)
       - Modifies external state
       - Does I/O (print, read file, network call)
    
     ? Pure functions are easier to test, debug, and reason about
 */

// * PURE FUNCTIONS

fun add(a: Int, b: Int) = a + b
// ? Same input -> always same output
// ? No side effects

fun square(x: Int) = x * x

fun isEven(n: Int) = n % 2 == 0

fun greet(name: String) = "Hello, $name!"

fun discount(price: Double, percent: Double): Double {
    return price - (price * percent / 100)
}

// * IMPURE FUNCTIONS

var counter = 0

fun incrementCounter(): Int {
    counter++          // Modifies external state
    return counter
}

fun printMessage(msg: String) {
    println(msg)       // Side effect: I/O
}

fun currentTime(): Long {
    return System.currentTimeMillis()  // Different output each time
}

fun main() {

    // * PURE -> predictable

    println("--- Pure Functions ---")
    println("add(2, 3)     = ${add(2, 3)}")       // always 5
    println("add(2, 3)     = ${add(2, 3)}")       // always 5
    println("square(4)     = ${square(4)}")        // always 16
    println("isEven(7)     = ${isEven(7)}")        // always false
    println("greet(Alice)  = ${greet("Alice")}")   // always "Hello, Alice!"
    println("discount(100, 20) = ${discount(100.0, 20.0)}")  // always 80.0

    println()

    // * IMPURE -> unpredictable

    println("--- Impure Functions ---")
    println("counter = ${incrementCounter()}")   // 1
    println("counter = ${incrementCounter()}")   // 2
    println("counter = ${incrementCounter()}")   // 3
    // ? Same function call, different results each time

    println()

    // * MAKING IMPURE -> PURE

    // ! Impure version
    var total = 0
    fun addToTotal(n: Int): Int {
        total += n     // Modifies external state
        return total
    }

    // ? Pure version
    fun addToValue(current: Int, n: Int): Int {
        return current + n   // ? No external state
    }

    println("--- Impure vs Pure ---")
    println("Impure: ${addToTotal(5)}")   // 5
    println("Impure: ${addToTotal(5)}")   // 10 (different!)

    println("Pure:   ${addToValue(0, 5)}")   // 5
    println("Pure:   ${addToValue(0, 5)}")   // 5 (same!)

    println()

    // * PURE FUNCTIONS WITH COLLECTIONS

    val numbers = listOf(1, 2, 3, 4, 5)

    // ? All these are pure -> no side effects, return new lists
    val doubled = numbers.map { it * 2 }
    val evens = numbers.filter { isEven(it) }
    val sum = numbers.reduce { acc, n -> add(acc, n) }

    println("--- Pure with Collections ---")
    println("Original: $numbers")   // unchanged!
    println("Doubled:  $doubled")
    println("Evens:    $evens")
    println("Sum:      $sum")

    println()

    // * TESTING PURE FUNCTIONS IS EASY

    println("--- Testing ---")

    fun testAdd() {
        assert(add(2, 3) == 5)
        assert(add(0, 0) == 0)
        assert(add(-1, 1) == 0)
        println("add() -> all tests passed")
    }

    fun testIsEven() {
        assert(isEven(2) == true)
        assert(isEven(3) == false)
        assert(isEven(0) == true)
        println("isEven() -> all tests passed")
    }

    testAdd()
    testIsEven()
}