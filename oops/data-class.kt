/*
    ! Data Class in Kotlin : ->

    - Data class is a special class used to hold data
    - It is mainly used to store values (like model classes)
    - Kotlin automatically generates useful functions for it

    --------------------------------------------
    * Main Purpose :

        - Store data efficiently
        - Reduce boilerplate code

    --------------------------------------------
    * Syntax :

        data class ClassName(val param1: Type, val param2: Type)

    --------------------------------------------
    * Visual Representation :

        data class Student
        -------------------------
        | name: String          |
        | age: Int              |
        -------------------------

        Kotlin automatically adds:
        - toString()
        - equals()
        - hashCode()
        - copy()

    --------------------------------------------
    * Example Usage (Conceptual):

        Student("Aman", 20)

        → prints: Student(name=Aman, age=20)

    --------------------------------------------
    * Automatically Generated Functions :

        1. toString()   → prints object data
        2. equals()     → compares objects
        3. hashCode()   → used in collections
        4. copy()       → creates copy with changes

    --------------------------------------------
    * Copy Visual :

        Original:
        Student(name="Aman", age=20)

        Copy with change:
        Student(name="Aman", age=21)

    --------------------------------------------
    ! Important Points :

    - data class must have at least 1 parameter in primary constructor
    - parameters should be val or var
    - cannot be abstract, open, sealed, or inner
    - used mostly for models (User, Student, Product, etc.)
*/


// Example
data class Student(val name: String, val age: Int)

fun main() {
    val student = Student("Aman", 20)
    println(student)

    val student2 = student.copy(age = 22);
    println(student2)

    val student3 = student.copy(name = "John");
    println(student3)

    val student4 = student.copy(name = "John", age = 22);
    println(student4)

    val student5 = student.copy();
    println(student5)

    
}