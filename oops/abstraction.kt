/*
    ! Abstraction in Kotlin : ->

    - Abstraction means "hiding implementation details and showing only essential features"
    - It focuses on WHAT an object does, not HOW it does it
    - It reduces complexity and increases security
    - It is achieved using:
        1. Abstract class
        2. Interface

    --------------------------------------------
    * Visual Representation :

        User
         |
         v
     ATM Machine
     --------------------
     | withdraw()       |
     | deposit()        |
     --------------------
         |
         v
    (Internal process hidden)

    User only uses functions
    Internal logic is hidden

    --------------------------------------------
    1. Abstract Class :

    - Declared using "abstract" keyword
    - Can have abstract (no body) and normal methods
    - Cannot create object of abstract class
    - Must be inherited

    * Key Points :

        - Contains both abstract + non-abstract methods
        - Child class must override abstract methods

    --------------------------------------------
    2. Interface :

    - Used to achieve full abstraction
    - Contains only abstract methods (and default methods in Kotlin)
    - A class can implement multiple interfaces

    * Key Points :

        - No constructor
        - Supports multiple inheritance
        - Used for designing behavior

    --------------------------------------------
    * Difference (Simple Visual) :

        Abstract Class
        -------------------
        | abstract + normal |
        | single inheritance|
        -------------------

        Interface
        -------------------
        | only behavior     |
        | multiple support  |
        -------------------

    --------------------------------------------
    ! Important Points :

    - Abstraction = hiding implementation details
    - Achieved using abstract class and interface
    - Helps in reducing complexity
    - Focus is on "what to do", not "how to do"
*/

// Code Example 

abstract class Vechile{
    abstract fun move()
    fun stop(){
        println("Vechile is stopped")
    }
}

class Car : Vechile(){
    override fun move(){
        println("Car is moving")
    }
}

// with interface
interface Moveable{
    fun move()
    fun stop(){
        println("Vechile is stopped")
    }
}

class Car2 : Moveable{
    override fun move(){
        println("Car is moving")
    }
}

fun main() {
    val car = Car()
    car.move()
    car.stop()

    // with interface
    val car2 = Car2()
    car2.move()
    car2.stop()
}