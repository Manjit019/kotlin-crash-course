/*
    ! Functions in Kotlin :

    - A function is a block of reusable code that performs a specific task.
    - Functions help to avoid code repetition and improve reusability.

    --------------------------------------------------

    ! 1. Function Declaration :

    ? Syntax:
        fun functionName() {
            // code
        }

    ? Example:
        fun greet() {
            println("Hello Kotlin")
        }

        fun main() {
            greet()
        }

    --------------------------------------------------

    ! 2. Function with Parameters :

    - Parameters are values passed to a function

    ? Syntax:
        fun functionName(parameter: Type) {
            // code
        }

    ? Example:
        fun greet(name: String) {
            println("Hello $name")
        }

    --------------------------------------------------

    ! 3. Function with Return Type :

    - Functions can return a value using "return"

    ? Syntax:
        fun functionName(): ReturnType {
            return value
        }

    ? Example:
        fun add(a: Int, b: Int): Int {
            return a + b
        }

    --------------------------------------------------

    ! 4. Single Expression Function :

    - Used when function has only one expression
    - No need for return keyword

    ? Example:
        fun add(a: Int, b: Int) = a + b

    --------------------------------------------------

    ! 5. Default Parameters :

    - You can assign default values to parameters

    ? Example:
        fun greet(name: String = "Guest") {
            println("Hello $name")
        }

    --------------------------------------------------

    ! 6. Named Arguments :

    - You can pass arguments by name

    ? Example:
        fun showDetails(name: String, age: Int) {
            println("$name is $age years old")
        }

        fun main() {
            showDetails(age = 20, name = "Amit")
        }

    --------------------------------------------------


    ? Benifits and Uses of Functions in Kotlin :

    - Functions make code more modular and reusable
    - Functions help to avoid code repetition and improve reusability
    - Functions help to reduce complexity and improve maintainability
    - Functions help to make code more readable and easier to understand

    --------------------------------------------------

    ! Important Points:

    - Functions are reusable blocks of code
    - main() is the entry point of Kotlin program
    - Kotlin supports concise single-expression functions
*/