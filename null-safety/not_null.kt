/*
    ! Not Null Assertion(!!) in Kotlin

    * val result = value!!
    ? if value is not null -> returns value
    ? if value is null -> throws NullPointerException

    ! use with extreme caution
    ! only use when you are 100% sure it's not null
    ? Prefer ?. and ?: instead

*/

data class User(val name : String, val email:String?)

fun main(){
    // Basic
    val name:String? = "Manjit"
    val length = name!!.length

    println("Length : $length")

    // * !! on null -> crash

    val nullName:String? = null
    try {
        val len = nullName!!.length // ! NullPointerException
    }
    catch(e : NullPointerException) {
        println("Crashed : ${e}")
    }

    // ? when is !! acceptable?

    // ? Only when you checked null before
    val user = User("Ankit", "ankit12@gmail.com")

    if(user.email != null){
        val upperEmail = user.email!!.uppercase()
        println("Email : $upperEmail")
    }

    // * !! vs ?. vs ?: comparison

    // ! !! -> crashes if null (not safe - avoid)
       // println(email!!.length)
    // ? ?. -> Returns null if null (safe)
        // println("?. result : ${email?.length}")
    // ? ?: -> Return default if null (safe)
        // println("?: result : ${email?.length ?: 0}")
}