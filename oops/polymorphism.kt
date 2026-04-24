/*
    ! Polymorphism in Kotlin : ->

    - Polymorphism means "many forms"
    - It allows a single function, method, or object to behave in different ways
    - It is one of the main concepts of Object-Oriented Programming (OOP)
    - It helps in code flexibility and reusability

    * Types of Polymorphism in Kotlin :

        1. Compile-time Polymorphism (Method Overloading)
        2. Run-time Polymorphism (Method Overriding)

    --------------------------------------------
    1. Compile-time Polymorphism (Method Overloading) :

    - Same function name with different parameters
    - Decided at compile time
    - Can be in same class

    * Example :

    Calculator
    ├── add(a, b)
    ├── add(a, b, c)

    Same name → different input → different behavior

    --------------------------------------------
    2. Run-time Polymorphism (Method Overriding) :

    - Same function name in parent and child class
    - Child class provides specific implementation
    - Decided at runtime

    ⚠️ Important:
    - Parent function must be marked as "open"
    - Child function must use "override"

    * Example :

    Animal (Parent Class)
    └── sound()
         ↓
    Dog (Child Class)
    └── sound()  → "bark"
         ↓
    Cat (Child Class)
    └── sound()  → "meow"

    Same method → different output based on object

    --------------------------------------------
    ! Important Points :

    - Polymorphism = One thing, many forms
    - Compile-time = Overloading
    - Run-time = Overriding
    - Helps in flexibility and dynamic behavior in code
*/

// Code Example :

class Calculator {

    fun add(a:Int,b: Int):Int{
        return a + b;
    }

    fun add(a:Int,b:Int,c:Int):Int{
        return a + b + c;
    }
}


// Run Time Polymorphism
open class Animal {
    open fun sound() {
        println("Animal makes sound");
    }
}
class Dog : Animal() {
    override fun sound() {
        println("Dog barks");
    }
}

//main
fun main() {
    println("Compile Time Polymorphism");
    val obj = Calculator();
    println(obj.add(2,3));
    println(obj.add(2,3,4));

    println("Run Time Polymorphism")
    val animal: Animal = Dog();
    animal.sound();

}