/*
    ! Set in Kotlin :

    - Set is a collection that contains unique elements.
    - Set is an unordered collection, which means that the order of elements in the set is not guaranteed.
    - Set is mutable, which means that elements can be added or removed from the set.

    --------------------------------------------------

    ! Types of Sets :

        1. Immutable Set (read-only)
        2. Mutable Set (can be modified)

    --------------------------------------------------

    * Syntax:
      ? Immutable: val set = setOf(element1, element2, element3)
      ? Mutable: val set = mutableSetOf(element1, element2, element3)

    --------------------------------------------------

    ! Example (Immutable):
        val set = setOf(1, 2, 3, 4)

    ! Example (Mutable):
        val set = mutableSetOf(1, 2, 3)

    --------------------------------------------------

    ! Common Functions:

        add()       -> adds element (mutable only)
        remove()    -> removes element (mutable only)
        contains()  -> checks element exists

    --------------------------------------------------
    
    * Sets are useful when : 
        1. You want to store unique elements
        2. You want to perform set operations like union, intersection, difference, etc.

    --------------------------------------------------

*/


//Code Example : 

fun main(){
    // ! Immutable Set
    val set = setOf(1, 2, 3, 4)
    println(set)

    // ! Mutable Set
    val set2 = mutableSetOf(1, 2, 3)
    set2.add(4)
    set2.remove(3)
    println(set2)

    // ! Common Functions
    val set3 = mutableSetOf(1, 2, 3, 4)
    println(set3.contains(3))
    println(set3.contains(5))

    // ! Set Operations
    val set4 = setOf(1, 2, 3)
    val set5 = setOf(3, 4, 5)
    val union = set4.union(set5)
    val intersection = set4.intersect(set5)
    val difference = set4.subtract(set5)
    
    print("Union: $union\n")
    print("Intersection: $intersection\n")
    print("Difference: $difference\n")
}