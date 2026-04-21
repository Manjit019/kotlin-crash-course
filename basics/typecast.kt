/*
  ! Type Casting in Kotlin :

    - Type casting means converting a value from one data type to another.
    - Kotlin is strongly typed, so most conversions must be done explicitly.

    --------------------------------------------------

    ! 1. Numeric Type Conversion (Recommended Way)

    - Kotlin does NOT support automatic numeric conversion.
    - We use built-in functions like:

        * toInt()
        * toLong()
        * toFloat()
        * toDouble()
        * toString()

    ? Example:
        val intValue: Int = 10
        val doubleValue: Double = intValue.toDouble()

    --------------------------------------------------

    ! 2. Object Type Casting (using "as")

    - The "as" keyword is used to cast objects (reference types).
    - Works only when types are compatible.

    ? Example:
        val anyValue: Any = "Kotlin"
        val str: String = anyValue as String

    --------------------------------------------------

    ! 3. Safe Casting (using "as?")

    - "as?" is a safe cast operator.
    - Returns null if casting is not possible (prevents crash).

    ? Example:
        val anyValue: Any = 100
        val result: String? = anyValue as? String
        println(result)   // null

    --------------------------------------------------

    ! 4. String to Number Conversion

    ? Example:
        val str = "123"
        val num = str.toInt()

    - Safe version:
        val num = str.toIntOrNull()

    --------------------------------------------------

    ! 5. Char Conversion

    ? Example:
        val ch = 'A'
        println(ch.code)   // ASCII value

        val num = 66
        println(num.toChar())  // 'B'

    --------------------------------------------------

    ! Important Points:

    - No automatic type conversion in Kotlin
    - Use .toType() for numeric conversions
    - Use "as" for object casting
    - Use "as?" for safe casting
    - Prefer safe methods like toIntOrNull()
*/


// Code Example : 

fun main() {
    // Numeric Type Conversion
    val intValue: Int = 10
    val doubleValue: Double = intValue.toDouble()
    println("Integer Value: $intValue")
    println("Double Value: $doubleValue")


    // Object Type Casting
    val anyValue: Any = "Kotlin"
    val str: String = anyValue as String
    println("String Value: $str")


    // Safe Casting
    val anyValue2: Any = 100
    val result: String? = anyValue2 as? String
    println("Safe Casting Result: $result")


    // String to Number Conversion
    val str2 = "123"
    val num = str2.toInt()
    println("String to Number: $num")

    // Safe version
    val num2 = str2.toIntOrNull()
    println("Safe String to Number: $num2")

    
}