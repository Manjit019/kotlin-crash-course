/*
    ! Elvis Operator ?: 

    * val result = value ?: default
    ? if value is Not null -> use value
    ? if value is null -> use default

    * Named "Elvis" because ?: looks like Elvis hair

    ! can also be used with return and throw
*/

data class Address(val city : String?)
data class User(val name : String, val address : Address?)

fun main(){
    // Basic elvis ?:

    val name : String? = null
    val result = name ?: "Guest"

    println("Result : $result")

    // combining ?: with ?.

    val user1 = User("Aman",Address("Delhi"))
    val user2 = User("Bikki",null)
    val user3 = User("Rakesh",Address(null))

    val city1 = user1.address?.city ?: "Unknown City"
    val city2 = user2.address?.city ?: "Unknown City"
    val city3 = user3.address?.city ?: "Unknown City"

    println("User1 city : $city1")
    println("User1 city : $city2")
    println("User1 city : $city3")

    // Elvis with functions

    fun getLength(str:String?):Int{
        return str?.length ?: 0
    }
    println("Lenght : ${getLength("Hello")}")
    println("Lenght : ${getLength(null)}")
    

    // Elvis with return / throw

    fun getUser(id : Int) : User?{
        return if (id == 1) User("Alice",null) else null
    }

    fun printUser(id : Int){
        //return early if null
        val user = getUser(id) ?: return
        println("Found User : ${user.name}")
    }

    fun getUserOrThrow(id : Int) :User{
        // throw exception if null
        return getUser(id) ?: throw IllegalArgumentException("User $id not found!")
    }

    printUser(1)
    printUser(2)
    try {
        getUserOrThrow(99)
    }
    catch( e : IllegalArgumentException) {
        println("Error : ${e.message}")
    }

    //? Real world example

    val users = listOf(
        User("Alice", Address("New York")),
        User("Bob", null),
        User("Carol", Address(null)),
    )

    println("User Cities ")
    for (user in users) {
        val city = user.address?.city ?: "No City"
        println("  ${user.name} -> $city")
    }

}   