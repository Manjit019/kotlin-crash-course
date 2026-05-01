/*
    ! Try catch finally in kotlin : ->

    * try { } -> code that might trow an exception
    * catch(e) { } -> handle the exception
    * finally { } -> always executed (optional)

    * Can have multiple catch blocks to handle different types of exceptions
    * finally block is executed regardless of whether an exception was thrown or caught

    * IN Kotlin, All exceptions are unchecked, meaning you are not required to declare them or catch them explicitly.
    * No need to declare "throws" like java
*/

fun main() {
    // ! Basic try catch 
    println("Basic try catch example:")
    try {
        val result = 10/0
        println("Result: $result")
    }
    catch(e : ArithmeticException){
        println("Caught an exception: ${e.message}")
    }

    //! Try catch with finally
    println("Try catch with finally example:")

    try {
        val numbers = listOf(1, 2, 3)
        println(numbers[5]) // This will throw an IndexOutOfBoundsException
    }
    catch(e : IndexOutOfBoundsException){
        println("Caught an exception: ${e.message}")
    }
    finally{
        println("This will always be executed")
    }

    //! Multiple catch blocks
    println("Multiple catch blocks example:")
    fun riskyOperation(input : String){
        try {
            val number = input.toInt()
            val result = 100 / number
            println("Result: $result")
        }
        catch(e : NumberFormatException){
            println("Caught a NumberFormatException: ${e.message}")
        }
        catch(e : ArithmeticException){
            println("Caught an ArithmeticException: ${e.message}")
        }
        catch(e : Exception){
            println("Caught a general exception: ${e.message}")
        }
    }
    riskyOperation("abc") // Will throw NumberFormatException
    riskyOperation("0")   // Will throw ArithmeticException
    riskyOperation("5")   // Will print "Result: 20"

    //! Common Exceptions -->
    println("Common exceptions example:")
    // ? NullPointerException
    try {
        val str: String? = null
        println(str!!.length)
    } catch (e: NullPointerException) {
        println("NPE: ${e.message}")
    }

    // ? NumberFormatException
    try {
        "hello".toInt()
    } catch (e: NumberFormatException) {
        println("NFE: ${e.message}")
    }

    // ? IndexOutOfBoundsException
    try {
        val list = listOf(1, 2, 3)
        list[99]
    } catch (e: IndexOutOfBoundsException) {
        println("IOB: ${e.message}")
    }

    // ? ClassCastException
    try {
        val obj: Any = "hello"
        val num = obj as Int
    } catch (e: ClassCastException) {
        println("CCE: ${e.message}")
    }

    // ! REQUIRE, CHECK, ERROR -> Kotlin helpers 
    println("\nKotlin helpers ")
    //? require -> validates function arguments
    fun setAge(age : Int){
        require(age >= 0) { "Age can not be negative : $age" }
        require(age <= 150){ "Age too large : $age" }
        println("Age set to $age")
    }
    setAge(25) 
    try{
        setAge(-5)
    }
    catch(e : IllegalArgumentException){
        println("require failed : ${e.message}")
    }
    //? check -> validates state of an object
    class Connection {
        var isConnected = false

        fun sendData(data: String) {
            check(isConnected) { "Not connected!" }
            println("Sending: $data")
        }
    }

    val conn = Connection()
    try {
        conn.sendData("hello")
    } catch (e: IllegalStateException) {
        println("check failed: ${e.message}")
    }

    conn.isConnected = true
    conn.sendData("hello")

    println()

    // ? error -> throws IllegalStateException
    fun getStatus(code: Int): String {
        return when (code) {
            200 -> "OK"
            404 -> "Not Found"
            500 -> "Server Error"
            else -> error("Unknown status code: $code")
        }
    }

    println(getStatus(200))
    try {
        getStatus(999)
    } catch (e: IllegalStateException) {
        println("error(): ${e.message}")
    }

    println()
    // * REAL WORLD -> Safe parsing

    fun parseAge(input: String): Int {
        return try {
            val age = input.trim().toInt()
            require(age in 0..150) { "Invalid age: $age" }
            age
        } catch (e: NumberFormatException) {
            println("Invalid input: $input")
            -1
        } catch (e: IllegalArgumentException) {
            println(e.message)
            -1
        }
    }

    println("--- Safe Parsing ---")
    println("Age: ${parseAge("25")}")
    println("Age: ${parseAge("abc")}")
    println("Age: ${parseAge("200")}")
}