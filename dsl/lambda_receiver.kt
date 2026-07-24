/*
    ! Kotlin - Lambda with Receiver

    * The core feature behind Kotlin DSLs

    * Normal lambda:
    ? (String) -> Unit
    ? Access params as "it" or named param

    * Lambda with receiver : 
    ? String.()-> Unit
    ? Inside lambda, "this" refers to the receiver
    ? Can access receiver properties/methods directly

    * This is what makes DSLs read like English
*/

fun main(args: Array<String>) {
    ///* Normal Lambda vs Lambda with Receiver */

    //? Normal Lambda
    val greet:(String)-> String = { name -> "Hello $name!"}
    val multipy:(Int,Int)->Int = {a,b -> a*b}

    //? Lambda with receiver
    val greet2:String.()->String = {"Hello $this!"} // "this" is the String
    val multiply2:Int.(Int) -> Int = { other -> this * other}
    

    println("--- Normal Lambda ---")
    println(greet("Aman"))
    println(multipy(2,3))

    println("--- Lambda with receiver ---")
    println(greet2("Aman"))
    println(6.multiply2(2))

    // * HOW IT WORKS

    // ? Normal: pass StringBuilder as parameter
    fun buildString(action:(StringBuilder)->Unit):String {
        val sb = StringBuilder()
        action(sb)
        return sb.toString()
    }

    val s1 = buildString{sb -> 
        sb.append("Hello, ")
        sb.append("World!")
    }

    //? With receiver : StringBuilder is "this"
    fun buildString2(action:StringBuilder.()->Unit):String {
        val sb = StringBuilder()
        sb.action()
        return sb.toString()
    }

    val s2 = buildString2 {
        append("Hello ")   // ? no need for sb.append
        append("World!")    // ? "this" is StringBuilder
    }

    println("Normal param: $s1")
    println("With receiver: $s2")

    println()

    // * APPLY IS A LAMBDA WITH RECEIVER

    // ? apply uses lambda with receiver internally
    // ? fun <T> T.apply(block: T.() -> Unit): T

    val list = mutableListOf<Int>().apply {
        add(1) // ? "this" is the list
        add(2)
        add(3)
    }

    println("apply: $list")

    println()


    // * BUILDING A SIMPLE DSL

    class Person {
        var name = ""
        var age = 0
        var email = ""

        override fun toString() = "Person(name=$name, age=$age, email=$email)"
    }

    fun person(init: Person.() -> Unit):Person{
        val p = Person()
        p.init()  // "this" inside refer to Person
        return p
    }

    val alice = person{
        name = "Alice"
        age = 30
        email = "alice@email.com"
    }

    println("Person: $alice")

    println()

    // * NESTED LAMBDA WITH RECEIVER

    class Address {
        var street = ""
        var city = ""
        var zip = ""

        override fun toString() = "$street, $city $zip"
    }

    class Contact {
        var name = ""
        var phone = ""
        var address = Address()

        fun address(init: Address.()->Unit) {
            address = Address().apply(init)
        }
        
        override fun toString() = "Contact(name=$name, phone=$phone, address=$address)"
    }

    fun contact(init:Contact.() -> Unit):Contact {
        return Contact().apply(init)
    }

    val myContact = contact {
        name = "Alice"
        phone = "555-1234"
        address {
            street = "123 Main St"
            city = "Springfield"
            zip = "62701"
        }
    }

    println("Contact: $myContact")

    println()

    // * EXTENSION FUNCTION AS RECEIVER

    fun Int.times(action: (Int) -> Unit) {
        for (i in 0 until this) {
            action(i)
        }
    }

    println("--- Custom times ---")
    3.times { i ->
        println("  Iteration $i")
    }

    println()

    // * LAMBDA RECEIVER WITH RETURN VALUE
    
    fun <T> buildList(init:MutableList<T>.() -> Unit):List<T> {
        return mutableListOf<T>().apply(init)
    }

    val fruits = buildList<String> {
        add("Apple")
        add("Banana")
        add("Cherry")
    }

    println("Fruits: $fruits")
}