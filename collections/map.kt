/*
   ! Map in Kotlin :

    - Key-value pairs
    - Can contain duplicate keys
    - Mutable collection

    --------------------------------------------------

    ! Types of Maps :

        1. Immutable Map (read-only)
        2. Mutable Map (can be modified)

    --------------------------------------------------

    * Syntax:
      ? Immutable: val map = mapOf(key1 to value1, key2 to value2)
      ? Mutable: val map = mutableMapOf(key1 to value1, key2 to value2)

    --------------------------------------------------

    ! Example (Immutable):
        val map = mapOf("name" to "John", "age" to 30)

    ! Example (Mutable):
        val map = mutableMapOf("name" to "John", "age" to 30)

    --------------------------------------------------

    * Common Functions:
        size        -> returns number of elements
        contains()  -> checks key exists
        get()       -> returns value of key
        put()       -> adds key-value pair
        remove()    -> removes key-value pair

    --------------------------------------------------

    ! Looping Maps :
        val map = mapOf("name" to "John", "age" to 30)

        for ((key, value) in map) {
            println("$key: $value")
        }

    --------------------------------------------------

    ? Map is Used when : 
        a) You need to store a collection of key-value pairs in a single variable.
        b) You need to access elements by key.
        c) You need to modify elements in the collection.

*/


// Code Example : 

fun main(){
    // ! Immutable Map
    val map = mapOf("name" to "John", "age" to 30)
    println(map);
    // Accessing values
    println(map["name"]);
    println(map["age"]);

    // ! Mutable Map
    val mutableMap = mutableMapOf("name" to "John", "age" to 30)
    println(mutableMap);
    // Adding a new key-value pair
    mutableMap["city"] = "New York"
    println(mutableMap);
    // Removing a key-value pair
    mutableMap.remove("age")
    println(mutableMap);


    // ? Looping over a Map
    for ((key, value) in map) {
        println("$key: $value")
    }

    // ? Common Functions
    println(mutableMap.size) // return size of the map
    println(mutableMap.containsKey("name")) // return true if the map contains the key
    println(mutableMap.containsKey("age"))
    println(mutableMap.get("name")) // return value of the key
    mutableMap.put("city", "New York") // add key-value pair
    println(mutableMap)
    mutableMap.remove("name") // remove key-value pair
    println(mutableMap)
}