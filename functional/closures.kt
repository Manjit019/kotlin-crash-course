/*
  ! KOTLIN — CLOSURES
 
  * A closure is a function that "remembers"
    variables from its surrounding scope
 
  * The function "captures" or "closes over"
    the variables from outside
 
  * Even after the outer function finishes,
    the captured variables are still accessible
 
  ? Closures are powerful for creating
    functions with "memory"
 */

fun main() {

    // * BASIC CLOSURE

    var count = 0

    val increment = {
        count++   // ? captures "count" from outer scope
        println("Count: $count")
    }

    increment()   // Count: 1
    increment()   // Count: 2
    increment()   // Count: 3

    println()

    // * CLOSURE CAPTURES VARIABLE, NOT VALUE

    var x = 10

    val printX = { println("x = $x") }

    printX()   // x = 10
    x = 20
    printX()   // x = 20  (captures variable, not the value 10)

    println()

    // * FUNCTION RETURNING A CLOSURE

    fun makeCounter(): () -> Int {
        var count = 0
        return {
            count++   //  captures "count"
            count
        }
    }

    val counter1 = makeCounter()
    val counter2 = makeCounter()   //  separate counter

    println("--- Separate Counters ---")
    println("counter1: ${counter1()}")   // 1
    println("counter1: ${counter1()}")   // 2
    println("counter1: ${counter1()}")   // 3
    println("counter2: ${counter2()}")   // 1  (separate!)
    println("counter2: ${counter2()}")   // 2

    println()

    // * CLOSURE WITH PARAMETERS

    fun makeMultiplier(factor: Int): (Int) -> Int {
        // ? captures "factor"
        return { number -> number * factor }
    }

    val double = makeMultiplier(2)
    val triple = makeMultiplier(3)
    val tenTimes = makeMultiplier(10)

    println("--- Multipliers ---")
    println("double(5)    = ${double(5)}")       // 10
    println("triple(5)    = ${triple(5)}")       // 15
    println("tenTimes(5)  = ${tenTimes(5)}")     // 50

    println()

    // * CLOSURE AS ACCUMULATOR

    fun makeAccumulator(initial: Int): (Int) -> Int {
        var total = initial
        return { amount ->
            total += amount   // ? captures and modifies "total"
            total
        }
    }

    val wallet = makeAccumulator(100)

    println("--- Wallet ---")
    println("Add 50:  ${wallet(50)}")    // 150
    println("Add 30:  ${wallet(30)}")    // 180
    println("Add -20: ${wallet(-20)}")   // 160

    println()

    // * CLOSURES IN COLLECTION OPERATIONS

    val threshold = 5

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // ? Lambda captures "threshold" from outer scope
    val filtered = numbers.filter { it > threshold }

    println("Threshold: $threshold")
    println("Filtered:  $filtered")

    println()

    // * REAL WORLD -> Logger factory

    fun makeLogger(prefix: String): (String) -> Unit {
        var logCount = 0
        return { message ->
            logCount++
            println("[$prefix #$logCount] $message")
        }
    }

    val infoLog = makeLogger("INFO")
    val errorLog = makeLogger("ERROR")

    println("--- Logger ---")
    infoLog("App started")
    infoLog("User logged in")
    errorLog("Connection failed")
    infoLog("Data loaded")
    errorLog("Timeout")
}