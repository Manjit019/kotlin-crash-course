/*
    ! Safe Call Operator (?.) in Kotlin

    - The safe call operator (?.) is used to access properties or methods of an object that may be null.
    - It returns the value of the property or method if the object is not null, otherwise it returns null.

    ? Example:
       user?.address // returns the address property if user is not null, otherwise returns null

    * It can be chained to access nested properties:
        user?.address?.city // returns the city property if both user and address are not null, otherwise returns null
    
    ! Without ?. you get NullPointerException
*/

data class Address(val city:String, val country:String, val pincode:Int?)
data class User(val name:String, val age:Int, val address:Address?)


fun main(){
    val user1 = User("Aman",20,Address("Patna","India",812333))
    val user2 = User("Aman",20,null)

    // basic safe call ?.
    println("---- Basic Safe Call ----")
    println(user1.address?.city)
    println(user2.address?.city)

    // chaining safe calls ?.?.
    println("---- Chaining Safe Call ----")
    val city1 = user1.address?.city?.uppercase()
    val city2 = user2.address?.city?.uppercase()
    println("City 1 : $city1")
    println("City 2 : $city2")

    // safe call on functions
    println("---- Safe Call on Functions -----")

    val name : String? = "   Manjit  "
    val nullName : String? = null
    println(name?.length) // 11
    println(nullName?.length) // null
    println(name?.trim()) // Manjit
    println(nullName?.trim()) // null

    // without vs with safe call

    println("---- Without vs With Safe Call -----")
    // val city = user2.address.city // ! Error : NullPointerException
    val city = user2.address?.city // null(no crash!)

    println("Safe city : $city")

    // Real world example
    println("---  Example ---")
    val users = listOf(
        User("Alice", 24,Address("Patna","India",812333)),
        User("Bob", 28,null),
        User("Charlie", 32,Address("Patna","India",812333)),
        User("Diana", 27,null)
    )

    println("User Cities ")
    for(user in users) {
        val c = user.address?.city
        println("${user.name} : $c")
    }

}