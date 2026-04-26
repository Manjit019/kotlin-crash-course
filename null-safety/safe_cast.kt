/*
    ! Safe Cast as? in kotlin ->

    * val result = value as? TargetType
    ? if cast succeeds -> returns value as type
    ? if cast fails -> returns null (no crash!)

    * Regular cast -> as (dangerous , can crash)
    * Safe cast -> as? (safe, returns null)

    * smart cast -> After "is" check , kolin auto-casts for you
*/

open class Animal(val name : String)
class Dog(name : String) : Animal(name){
    fun bark() = println("  ${name} says : Woof!")
}
class Cat(name : String):Animal(name){
    fun meow() = println("  ${name} says : Meow!")
}

fun main(){
    //! Regular cast as -> dangerous

    val obj: Any = "hello"
    val str = obj as String
    println("Cast Ok : $str")


    try {
        val num = obj as Int
        println("num : $num")
    }
    catch(e:ClassCastException) {
        println("Error : $e.message")
    }

    //! Safe cast as? -> safe
    val obj2 : Any = "Hello"
    val str2 = obj2 as? String // Hello
    val num2 = obj2 as? Int // null (no crash)

    println("Str2 : $str2")
    println("Num2 : $num2")

    // ! safe cast + elvis

    val obj3 : Any = 43
    val result = (obj3 as? String) ?: "Not a String"
    println("Result : $result")

    // ! Casting objects

    val animals: List<Animal> = listOf(
        Dog("Rex"),
        Cat("Whiskers"),
        Dog("Buddy"),
        Cat("Luna"),
    )

    println("Animal Sounds ....")
    for (animal in animals){
        (animal as? Dog)?.bark()
        (animal as? Cat)?.meow()
    }

    // ! Smart Cast -> Kotlin auto-casts after "is" block
    val value:Any = "Kolin is awesome!"

    if (value is String){
        // ? No cast needed
        println("Length : ${value.length}")
        println("Upper : ${value.uppercase()}")
    }

    // smart cast with when
    
    fun describe(obj : Any):String{
        return when (obj){
            is String -> "String of length ${obj.length}"
            is Int -> "Integer of value $obj"
            is Boolean -> "Boolean of value $obj"
            is List<*> -> "List of size ${obj.size}"
            else -> "Unknown type"
        }
    }

    println(describe("hello"))
    println(describe(42))
    println(describe(true))
    println(describe(listOf(1,2,3)))
    println(describe(3.23))
}