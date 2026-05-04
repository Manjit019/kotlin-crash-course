/*
     ! KOTLIN — FUNCTION TYPES
    
     * Functions can be:
       ? Stored in variables
       ? Passed as arguments
       ? Returned from functions
    
     * Function type syntax:
       ? (Int) -> String           takes Int, returns String
       ? (Int, Int) -> Int         takes 2 Ints, returns Int
       ? () -> Unit                takes nothing, returns nothing
       ? (String) -> Boolean       takes String, returns Boolean
    
     * Ways to create function values:
       ? Lambda:            { x: Int -> x * 2 }
       ? Anonymous:         fun(x: Int): Int = x * 2
       ? Reference:         ::functionName
 */

fun main() {

    // * STORING FUNCTIONS IN VARIABLES

    val add: (Int, Int) -> Int = { a, b -> a + b }
    val multiply: (Int, Int) -> Int = { a, b -> a * b }
    val greet: (String) -> String = { name -> "Hello, $name!" }
    val doNothing: () -> Unit = { println("Doing nothing") }

    println("add(2, 3)      = ${add(2, 3)}")
    println("multiply(4, 5) = ${multiply(4, 5)}")
    println("greet(Alice)   = ${greet("Alice")}")
    doNothing()

    println()

    // * PASSING FUNCTIONS AS ARGUMENTS

    fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    println("--- Passing Functions ---")
    println("add:      ${operate(10, 5, add)}")
    println("multiply: ${operate(10, 5, multiply)}")
    println("subtract: ${operate(10, 5) { a, b -> a - b }}")   // inline lambda

    println()

    // * RETURNING FUNCTIONS

    fun getOperation(type: String): (Int, Int) -> Int {
        return when (type) {
            "add"      -> { a, b -> a + b }
            "subtract" -> { a, b -> a - b }
            "multiply" -> { a, b -> a * b }
            "divide"   -> { a, b -> a / b }
            else       -> { _, _ -> 0 }
        }
    }

    val op = getOperation("multiply")
    println("--- Returned Function ---")
    println("op(6, 7) = ${op(6, 7)}")

    println()

    // * FUNCTION REFERENCES ::

    fun isEven(n: Int) = n % 2 == 0
    fun isPositive(n: Int) = n > 0
    fun toUpper(s: String) = s.uppercase()

    val numbers = listOf(1, 2, 3, 4, 5, 6)

    // ? Using function reference instead of lambda
    val evens = numbers.filter(::isEven)
    println("Evens: $evens")

    val words = listOf("hello", "world")
    val upper = words.map(::toUpper)
    println("Upper: $upper")

    println()

    // * ANONYMOUS FUNCTIONS

    val square = fun(x: Int): Int = x * x
    val isLong = fun(s: String): Boolean = s.length > 5

    println("square(5)        = ${square(5)}")
    println("isLong(kotlin)   = ${isLong("kotlin")}")
    println("isLong(hi)       = ${isLong("hi")}")

    println()

    // * NULLABLE FUNCTION TYPES

    var callback: ((String) -> Unit)? = null

    // ? Safe call on function
    callback?.invoke("test")   // does nothing (null)

    callback = { println("Callback: $it") }
    callback?.invoke("test")   // Callback: test

    println()

    // * REAL WORLD -> Strategy Pattern

    data class Order(val items: List<String>, val total: Double)

    fun processOrder(
        order: Order,
        discount: (Double) -> Double,
        notify: (Order) -> Unit
    ) {
        val finalPrice = discount(order.total)
        println("Original: $${order.total}")
        println("Final:    $$$finalPrice")
        notify(order)
    }

    val halfOff: (Double) -> Double = { it * 0.5 }
    val tenPercent: (Double) -> Double = { it * 0.9 }
    val emailNotify: (Order) -> Unit = { println("Email sent for ${it.items}") }

    println("--- Strategy Pattern ---")
    processOrder(
        Order(listOf("Laptop"), 1000.0),
        discount = halfOff,
        notify = emailNotify
    )

    println()

    processOrder(
        Order(listOf("Phone"), 500.0),
        discount = tenPercent,
        notify = { println("SMS sent for ${it.items}") }
    )
}