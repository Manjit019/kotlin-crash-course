/*
    ! let scope function in kotlin

    * value?.let { }
    ? Executes block only if value is not null
    ? Inside block, value is accessed as "it"
    ? Returns result of the block

    * value?.let{ name -> }
    ? can rename "id" to any name

    ! perfect for running logic on nullable values
*/

data class User(val name : String, val email:String?)

fun sendEmail(email : String){
    println("   Sending email to -> $email")
}

fun main(){
    // basic let

    val name: String? = "Manjit"
    val nullName : String? = null

    // ? run only if name is not null
    name?.let{
        println("Name is : $it")
        println("Lenght : ${it.length}")
    }

    // ? Does not run because nullName is null
    nullName?.let{
        println("This will not print")
    }

    //  let vs if null check
    val email: String? = "test@gmail.com"

    // // Old way
    if (email != null){
        sendEmail(email)
    }

    // * Kotlin way
    email?.let{ sendEmail(it) }

    // ! let with custom name (instead of "it")

    val user: User? = User("Akash","akash@gmail.com")

    user?.let{u ->
        println("User Name : ${u.name}")
        println("User Email : ${u.email}")
        u.email?.let{e -> sendEmail(e)}
    }


    // let return a value
    val number: Int? = 5
    val result = number?.let{
        it * it // ? returns 25
    }

    println("Result : $result")

    val nullNumber : Int? = null
    val nullResult = nullNumber?.let{
        it * it
    } 
    println("Null Result : $nullResult ")

    // let + elvis -> perfect combo
    val score: Int? = null
    val msg = score?.let{
        "your score is $it"
    } ?: "No score yet"

    println("Message : $msg")

    // Real world example

    val users = listOf(
        User("Alice", "alice@email.com"),
        User("Bob", null),
        User("Carol", "carol@email.com"),
    )

    println("Sending Emails...")
    for (user in users) {
        user.email?.let {
            sendEmail(it)
        } ?: println("   ${user.name} has no email!")
    }
}