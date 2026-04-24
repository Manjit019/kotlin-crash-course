/*
    ! Collections in Kotlin :

    - Collections are used to store multiple values in a single variable.
    - Kotlin provides powerful built-in collection types.

    --------------------------------------------------

    ! Types of Collections :

        1. List
        2. Set
        3. Map

    --------------------------------------------------

    ! 1. List :

    - Ordered collection (keeps insertion order)
    - Can contain duplicate elements

    ? Two types:
        a) Immutable List (read-only)
        b) Mutable List (can be modified)

    ? Example (Immutable):
        val list = listOf(1, 2, 3, 4)

    ? Example (Mutable):
        val list = mutableListOf(1, 2, 3)
        list.add(4)
        list.remove(2)

    ? Access element:
        println(list[0])

    --------------------------------------------------

    ! 2. Set :

    - Unordered collection
    - Does NOT allow duplicate values

    ? Example:
        val set = setOf(1, 2, 3, 3)

    - Duplicate "3" will be ignored

    ? Mutable Set:
        val set = mutableSetOf(1, 2, 3)
        set.add(4)

    --------------------------------------------------

    ! 3. Map :

    - Stores data in key-value pairs
    - Keys must be unique

    ? Example:
        val map = mapOf(1 to "A", 2 to "B")

    ? Access value:
        println(map[1])

    ? Mutable Map:
        val map = mutableMapOf(1 to "A")
        map[2] = "B"

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

    ! Important Points:

        - List = ordered, allows duplicates
        - Set = no duplicates
        - Map = key-value pairs
        - Mutable collections can be changed
        - Immutable collections cannot be modified
*/