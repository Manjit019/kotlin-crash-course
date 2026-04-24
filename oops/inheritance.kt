/*
    ! Inheritance in Kotlin : ->

    - Inheritance in Kotlin is a way to reuse code and create a hierarchy of classes
    - It allows a class (child/subclass) to acquire properties and behavior of another class (parent/superclass)
    - It helps in code reusability and method overriding

    ⚠️ By default, classes in Kotlin are FINAL
    - You MUST use "open" keyword to allow inheritance

    * Types of Inheritance in Kotlin :

        1. Single Inheritance
        2. Multilevel Inheritance
        3. Hierarchical Inheritance

    ⚠️ Kotlin does NOT support:
        - Multiple inheritance (with classes)
        - Hybrid inheritance (directly with classes)
      (But multiple inheritance is possible using interfaces)

    --------------------------------------------
    * Syntax :

        open class Superclass {
            // parent class body
        }

        class Subclass : Superclass() {
            // child class body
        }

    --------------------------------------------
    * Example :

        open class Animal {
            fun makeSound() {
                println("Animal makes a sound")
            }
        }

        class Dog : Animal() {
            fun bark() {
                println("Dog barks")
            }
        }

        fun main() {
            val dog = Dog()
            dog.makeSound() // Output: Animal makes a sound
            dog.bark()      // Output: Dog barks
        }

    --------------------------------------------
    ! Important Points :

    - Use "open" keyword for superclass to allow inheritance
    - Inheritance is done using ":" symbol
    - Parent class constructor is called using ()
    - Kotlin supports multiple inheritance only through interfaces
*/

// Code Example : 

// Base Class 

open class Animal(val name : String){

    fun eat(){
        println("$name eats");
    }
    open fun makeSound(){ // open enables overriding
        println("$name makes a sound");
    }
}

// Derived Class

class Dog(name : String) : Animal(name){

    fun jump(){
        println("$name jumps");
    }
    override fun makeSound(){ // Method Overriding
        println("$name barks");
    }
}

class Cat(name : String) : Animal(name){

    override fun makeSound(){ // Method Overriding
        println("$name meows");
    }
}

//main function
fun main() {
    val dog = Dog("Rover");
    dog.makeSound();
    dog.jump();
    dog.eat();

    val cat = Cat("Whiskers");
    cat.makeSound();
    cat.eat();

    val animal = Animal("Animal");
    animal.makeSound();
    animal.eat();


}