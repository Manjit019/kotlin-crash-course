/*
    ! Nullable Types in Kotlin : 

    * In Kotlin, types are non- nullable by default, which means that you can't assign null to a variable declared with a non-nullable type.
    * To declare a variable as nullable, you can use the "?" operator after the type name.

    Note : In Java variable can be null, but in Kotlin you must explicitly allow null.
*/

data class Address(val city : String, val zip : Int?)
data class User(val name : String, val age : Int, val address : Address?)

fun main(){
    // Non-nullable 
    var name:String = "Alice"
    var age : Int = 18
    // name = null // ! ERROR — null is not assignable to type 'String'
    // age = null // ! ERROR — null is not assignable to type 'Int'

    // println(name)
    // println(age)

    // Nullable - can be null
    var score : Int? = null
    var nickName : String? = "Manjit"
    
    println("Score: $score")
    println("Nick Name: $nickName")

    nickName = null // ? allowed because String?
    score = 120 // ? allowed

    println("Score: $score")
    println("Nick Name: $nickName")

    // ! Nullable in classes

    val user1 = User(
        name = "Alice",
        age = 18,
        address = Address(city="Patna",zip=812333)
    )
    val user2 = User(
        name = "Bob",
        age = 20,
        address = null 
    )


    println("User 1 : $user1")
    println("User 2 : $user2")


    // ! Checking null manually (Old way)

    if(user2.address != null){
        println("City : ${user2.address.city}")
    } else {
        println("No address provided")
    }

    // ? Kotlin has better ways to handle this (using safe call and elvis operator)
}