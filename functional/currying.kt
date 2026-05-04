/*
   ! KOTLIN — CURRYING
  
   * Currying = transforming a function that takes
     multiple arguments into a chain of functions
     that each take ONE argument
  
   * Normal:    f(a, b, c)
   * Curried:   f(a)(b)(c)
  
   * Why currying?
     ? Create specialized functions from general ones
     ? Partial application (fix some arguments)
     ? More flexible function composition
  
   ! Kotlin does not have built-in currying
   ! But we can implement it easily with closures
 */

fun main() {

    // * NORMAL FUNCTION (not curried)

    fun add(a: Int, b: Int) = a + b

    println("Normal: add(2, 3) = ${add(2, 3)}")

    println()

    // * CURRIED VERSION

    fun curriedAdd(a: Int): (Int) -> Int {
        return { b -> a + b }
    }

    println("--- Curried ---")
    println("curriedAdd(2)(3)  = ${curriedAdd(2)(3)}")

    // ? Create specialized functions
    val addFive = curriedAdd(5)
    val addTen = curriedAdd(10)

    println("addFive(3)   = ${addFive(3)}")    // 8
    println("addFive(7)   = ${addFive(7)}")    // 12
    println("addTen(20)   = ${addTen(20)}")    // 30

    println()

    // * CURRIED MULTIPLY

    fun curriedMultiply(a: Int): (Int) -> Int = { b -> a * b }

    val double = curriedMultiply(2)
    val triple = curriedMultiply(3)

    println("--- Curried Multiply ---")
    println("double(5) = ${double(5)}")   // 10
    println("triple(5) = ${triple(5)}")   // 15

    println()

    // * THREE ARGUMENT CURRYING

    fun curriedAdd3(a: Int): (Int) -> (Int) -> Int {
        return { b -> { c -> a + b + c } }
    }

    println("--- Three Arguments ---")
    println("add3(1)(2)(3) = ${curriedAdd3(1)(2)(3)}")

    val addOneAnd = curriedAdd3(1)
    val addOneAndTwo = addOneAnd(2)
    println("addOneAndTwo(10) = ${addOneAndTwo(10)}")   // 13

    println()

    // * GENERIC CURRY HELPER

    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C {
        return { a -> { b -> f(a, b) } }
    }

    // ? Curry any 2-argument function
    val curriedSubtract = curry { a: Int, b: Int -> a - b }
    val subtractFrom100 = curriedSubtract(100)

    println("--- Generic Curry ---")
    println("subtractFrom100(30) = ${subtractFrom100(30)}")   // 70
    println("subtractFrom100(75) = ${subtractFrom100(75)}")   // 25

    println()

    // * REAL WORLD -> Curried formatter

    fun formatter(prefix: String): (String) -> (String) -> String {
        return { separator ->
            { value ->
                "$prefix$separator$value"
            }
        }
    }

    val logFormatter = formatter("[LOG]")
    val logWithDash = logFormatter(" - ")
    val logWithColon = logFormatter(": ")

    println("--- Formatter ---")
    println(logWithDash("App started"))
    println(logWithDash("User logged in"))
    println(logWithColon("Error occurred"))

    println()

    // * REAL WORLD -> Curried discount calculator

    fun discountCalc(discountPercent: Double): (Double) -> Double {
        return { price -> price * (1 - discountPercent / 100) }
    }

    val studentDiscount = discountCalc(20.0)
    val employeeDiscount = discountCalc(30.0)
    val vipDiscount = discountCalc(50.0)

    println("--- Discounts ---")
    println("Student  $100 -> $${studentDiscount(100.0)}")
    println("Employee $100 -> $${employeeDiscount(100.0)}")
    println("VIP      $100 -> $${vipDiscount(100.0)}")
}