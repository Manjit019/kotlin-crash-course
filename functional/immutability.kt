/*
  ?! KOTLIN — IMMUTABILITY
 
  * Immutable = cannot be changed after creation
  * Mutable = can be changed
 
  * In Kotlin:
    ? val = immutable variable (prefer this)
    ! var = mutable variable (avoid when possible)
    ? listOf()        = immutable list
    ! mutableListOf() = mutable list
 
  * Why immutability?
    ? Thread safe (no race conditions)
    ? Predictable (no surprise changes)
    ? Easier to debug
    ? Functions dont accidentally modify data
 */

data class User(val name: String, val age: Int)

fun main() {

    // * val vs var

    val name = "Alice"     // ? Immutable
    var age = 25           // ! Mutable

    // name = "Bob"        // ! Compile error
    age = 26               // ? Allowed but avoid if possible

    println("name: $name, age: $age")

    println()

    // * IMMUTABLE COLLECTIONS

    val fruits = listOf("Apple", "Banana", "Cherry")

    // fruits.add("Date")       // ! Compile error
    // fruits[0] = "Avocado"    // ! Compile error
    // fruits.remove("Apple")   // ! Compile error

    println("Fruits: $fruits")   // ? Safe, nobody can modify

    println()

    // * MUTABLE COLLECTIONS (avoid when possible)

    val mutableFruits = mutableListOf("Apple", "Banana")
    mutableFruits.add("Cherry")       // ! Can modify
    mutableFruits[0] = "Avocado"      // ! Can modify

    println("Mutable: $mutableFruits")

    println()

    // * TRANSFORM INSTEAD OF MUTATE

    // ! Bad -> mutating in place
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    numbers.add(6)
    numbers.removeAt(0)
    println("Mutated: $numbers")

    // ? Good -> creating new collections
    val original = listOf(1, 2, 3, 4, 5)
    val withSix = original + 6
    val withoutFirst = original.drop(1)

    println("Original:     $original")       // unchanged!
    println("With 6:       $withSix")
    println("Without first: $withoutFirst")

    println()

    // * IMMUTABLE DATA CLASSES

    val user = User("Alice", 25)

    // user.name = "Bob"     // ! Compile error (val in data class)

    // ? Use copy() to create modified version
    val olderUser = user.copy(age = 26)
    val renamedUser = user.copy(name = "Bob")

    println("Original: $user")
    println("Older:    $olderUser")
    println("Renamed:  $renamedUser")

    println()

    // * IMMUTABLE TRANSFORMATIONS PIPELINE

    val students = listOf(
        User("Alice", 22),
        User("Bob", 17),
        User("Carol", 25),
        User("Dave", 16),
        User("Eve", 20),
    )

    // ? Each step creates NEW list, original unchanged
    val adults = students
        .filter { it.age >= 18 }
        .sortedBy { it.age }
        .map { it.copy(name = it.name.uppercase()) }

    println("Original students: $students")
    println("Adults:            $adults")

    println()

    // * WHY IMMUTABILITY MATTERS

    // ! Problem with mutable data
    fun addItemBad(list: MutableList<String>, item: String) {
        list.add(item)   // ! Modifies caller's list
    }

    val myList = mutableListOf("a", "b")
    addItemBad(myList, "c")
    println("Surprise! myList changed: $myList")   // [a, b, c]

    // ? Solution with immutable data
    fun addItemGood(list: List<String>, item: String): List<String> {
        return list + item   // ? Returns NEW list
    }

    val myList2 = listOf("a", "b")
    val newList = addItemGood(myList2, "c")
    println("myList2 unchanged: $myList2")   // [a, b]
    println("newList:           $newList")    // [a, b, c]
}