/*
    ! List in Kotlin : 

    - List is a collection of elements that can be accessed by index.
    - List is ordered collection (keeps insertion order)
    - Can contain duplicate elements

    --------------------------------------------------

    ! Types of Lists :

        1. Immutable List (read-only)
        2. Mutable List (can be modified)

    --------------------------------------------------


    * Syntax:
      ? Immutable: val list = listOf(element1, element2, element3)
      ? Mutable: val list = mutableListOf(element1, element2, element3)

    --------------------------------------------------

    ! Example (Immutable):
        val list = listOf(1, 2, 3, 4)

    ! Example (Mutable):
        val list = mutableListOf(1, 2, 3)

    --------------------------------------------------

    ! Common Functions :

        size        -> returns number of elements
        contains()  -> checks element exists
        add()       -> adds element (mutable only)
        remove()    -> removes element (mutable only)

    --------------------------------------------------

    ! Looping Collections :

        val list = listOf(10, 20, 30)

        for (item in list) {
            println(item)
        }

    --------------------------------------------------
    
    ? List are used when : 
        a) You need to store a collection of elements in a single variable.
        b) You need to access elements by index.
        c) You need to modify elements in the collection.

    --------------------------------------------------


*/


//Code Exmaple : 

fun main(){
    // ! Mutable List
    val list = mutableListOf(1, 2, 3)
    list.add(4)
    list.remove(3)
    println(list)

    // ! Immutable List
    val list2 = listOf(1, 2, 3)
    println(list2)

    // ! Looping Lists
    val list3 = listOf(10, 20, 30)
    for (item in list3) {
        println(item)
    }

    // ! Common Functions
    val list4 = listOf(1, 2, 3, 4)
    println(list4.size)
    println(list4.contains(3))
    println(list4.contains(5))
    
}