/*
    ! Kotlin Data Types :->
    - In Kotlin, data types are used to define the type of data that a variable can hold.
    - Kotlin has a rich set of built-in data types, including:
      1. Numeric Types: Byte, Short, Int, Long, Float, Double
      2. Character Type: Char
      3. Boolean Type: Boolean
      4. String Type: String
      5. Null Type: Nothing
      6. Unit Type: Unit
    - Kotlin also supports nullable types, which can hold null values. To declare a nullable type, you can use the "?" operator after the type name.
    - Kotlin provides type inference, which means that you can omit the type declaration when the compiler can infer the type from the context.
    - Kotlin also supports type conversion, which allows you to convert a value from one type to another type using built-in functions like toInt(), toDouble(), etc.

    ! How to use Kotlin Data Types :
    - To declare a variable with a specific data type, you can use the following syntax:
        val variableName: DataType = value
        var variableName: DataType = value
    - To declare a nullable variable, you can use the following syntax:
        val variableName: DataType? = null
        var variableName: DataType? = null
    - To use type inference, you can simply assign a value to a variable without specifying the type:
        val variableName = value
        var variableName = value
    - To convert a value from one type to another, you can use the built-in conversion functions:
        val intValue: Int = 42
        val doubleValue: Double = intValue.toDouble()
        println("Integer Value: $intValue")
        println("Double Value: $doubleValue")

    
*/

// Code Example:

fun main() {
    // Declaring a variable with a specific data type
    val name: String = "Alice"
    println("Name: $name")
    
    // Declaring a nullable variable
    val age: Int? = null
    println("Age: $age")
    
    // Using type inference
    val city = "New York"
    println("City: $city")
    
    // Converting a value from one type to another
    val intValue: Int = 42
    val doubleValue: Double = intValue.toDouble()
    println("Integer Value: $intValue")
    println("Double Value: $doubleValue")

    
}