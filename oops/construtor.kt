/*
   ! Constructor in Kotlin :

   - Constructor is used to initialize an object when it is created
   - It is used to assign values to the properties of a class
   - It is called automatically when an object is created

   * Types of constructors in Kotlin :

        1. Primary constructor
        2. Secondary constructor


   --------------------------------------------
   ? 1. Primary Constructor :

   - It is declared in the class header
   - It can have parameters
   - It cannot have a body directly
   - init block is used to write initialization logic

   * Syntax :
        class ClassName(val param1: Type, var param2: Type)

   * Example :
        class Person(val name: String, val age: Int) {
            init {
                println("Name = $name, Age = $age")
            }
        }


   --------------------------------------------
    ? 2. Secondary Constructor :

   - It is declared inside the class body using "constructor" keyword
   - It provides alternative ways to create objects
   - It must initialize all properties
   - If primary constructor exists, it must call it using this()

   * Syntax :
        class ClassName {
            constructor(parameters) {
                // initialization code
            }
        }

   * Example :
        class Person {
            var name: String
            var age: Int

            constructor(name: String, age: Int) {
                this.name = name
                this.age = age
            }
        }


   --------------------------------------------
   ! Constructor Delegation :

   - When both primary and secondary constructors exist
   - Secondary constructor must call primary constructor using this()

   * Example :
        class Person(val name: String) {
            var age: Int = 0

            constructor(name: String, age: Int) : this(name) {
                this.age = age
            }
        }
*/


// Code Examples : 

// Class with primary constructor
class Person(val name: String, val age: Int) {
    init {
        println("Name = $name, Age = $age")
    }
}

// Class with secondary constructor

class Cylinder(val radius: Double, val height: Double) {
    constructor(radius: Double) : this(radius, 1.0)

    fun volume():Double{
        return Math.PI * radius * radius * height;
    }
}

// Class with constructor delegation
class Employee(val name: String){
    var age: Int  = 0
    constructor(name: String, age: Int) : this(name){
        this.age = age
    }

    fun introduce(){
        println("My name is $name and I am $age years old");
    }
}


//Main

fun main(){
    // Class with primary constructor
    val person = Person("Amit", 20);

    // Class with secondary constructor
    val cylinder = Cylinder(5.0);
    println("Volume : ${cylinder.volume()}");
    println("Volume: %.2f".format(cylinder.volume()))

    // Class with constructor delegation
    val employee = Employee("Amit", 20);
    employee.introduce();

}