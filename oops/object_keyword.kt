/*
    ! Object Keyword in Kotlin : ->

    - "object" keyword is used to create a singleton object
    - Singleton means only ONE instance of the class exists
    - Kotlin automatically creates the object (no need to use "new")
    - It is used when we need a single shared instance

    --------------------------------------------
    * Visual Representation :

        Class (Normal)
        -------------------
        | can create many  |
        | objects          |
        -------------------

        Object (Singleton)
        -------------------
        | only ONE instance|
        | shared globally  |
        -------------------

    --------------------------------------------
    * Syntax :

        object ObjectName {
            // properties
            // functions
        }

    --------------------------------------------
    * Example Usage (Conceptual):

        object Database {
            fun connect() {
                println("Connected to database")
            }
        }

    --------------------------------------------
    * How it works :

        Database.connect()

        → No object creation needed
        → Direct access using name

    --------------------------------------------
    * Key Features :

        - Only one instance is created (Singleton pattern)
        - Created at first usage
        - Thread-safe by default
        - Cannot have constructor

    --------------------------------------------
    * Common Uses :

        - Database connection
        - Logging system
        - Configuration settings
        - Utility/helper functions

    --------------------------------------------
    ! Important Points :

    - object keyword = Singleton pattern
    - No need to create object manually
    - Only one instance exists in entire application
*/

// Code Example

object Database {
    fun connect() {
        println("Connected to database")
    }
}

fun main() {
    Database.connect()
}