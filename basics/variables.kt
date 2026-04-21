/*
  ! Kotlin Variables : 
    - In Kotlin, variables are used to store data that can be changed during the execution of a program.
    - You can declare a variable by using the "val" keyword, which means "value".
    - You can also declare a variable by using the "var" keyword, which means "variable".
    - You can also declare a variable by using the "lateinit" keyword, which means "late initialization".
    - The "val" keyword is used for read-only variables, which means that once a value is assigned to a variable declared with "val", it cannot be changed.
    - The "var" keyword is used for mutable variables, which means that the value of a variable declared with "var" can be changed.
    - The "lateinit" keyword is used for variables that will be initialized later, and it can only be used with non-nullable types. It allows you to declare a variable without initializing it immediately, and you can initialize it later in the code.

*/

// Code Example:

fun main() {
    // Using val for a read-only variable
    val name: String = "Alice"
    println("Name: $name")
    
    // Using var for a mutable variable
    var age: Int = 30
    println("Age: $age")
    
    // Changing the value of the mutable variable
    age = 31
    println("Updated Age: $age")
    
    // Using lateinit for a variable that will be initialized later
    lateinit var city: String
    
    // Initializing the lateinit variable
    city = "New York"
    println("City: $city")
}