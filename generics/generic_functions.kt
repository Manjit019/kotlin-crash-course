/*
    ! Generic Functions in Kotlin -->

    * <T> means "any type"
    * T is just a name , you can use any letter
    ? T = Type , E = Element, K = Key , V = Value

    * Without generics -> write same function for each type
    * With generics -> write once , works for all types

*/

fun main(){
    // ! problem without generics
    // ? You would need separate functions for each type

    fun printInt(value:Int) = println(value)
    fun printString(value:String) = println(value)
    fun printDouble(value:Double) = println(value)

    // ? Repeating same logic for different types = BAD

    println("===== without generics ======")

    printInt(100)
    printString("Hello")
    printDouble(12.3)

    // * Solution with generics
    // ? One function works for all types

    fun <T> printItem(value:T){
        println("Value : $value , Type : ${value!!::class.simpleName}")
    }

    println("===== with generics ======")

    printItem(100)
    printItem("Hello")
    printItem(12.3)
    printItem(true)

    // ! Generic function with return type

    fun <T> firstItem(list : List<T>):T {
        return list.first()
    }

    val firstInt = firstItem(listOf(1, 2, 3, 4))
    val firstString = firstItem(listOf("a", "b", "c", "d"))

    println("First Int : $firstInt ,\n First String : $firstString")

    // ! Generic function with two type parameters

    fun <K,V> printPair(key : K, value : V){
        println("Key : $key , Value : $value")
    }

    printPair("name", "Manjit")
    printPair(1, "Manjit")
    printPair("PI", 3.14)


    // ! Generic swap function

    fun <T> swap(list : MutableList<T>,i:Int,j : Int){
        val temp = list[i]
        list[i] = list[j]
        list[j] = temp
    }

    val nums = mutableListOf(1,2,3,4,5)
    println("Before swap : $nums")
    swap(nums,0,4)
    println("After swap : $nums")

    val words = mutableListOf('a','b','c')
    println("Before swap : $words")
    swap(words, 0,2)
    println("After swap : $words")


    // ! Generics filter function

    fun <T> customFilter(list : List<T>,condition : (T) -> Boolean):List<T>{
        val result = mutableListOf<T>()
        for (item in list){
            if (condition(item)){
                result.add(item)
            }
        }
        return result
    }

    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
    val evens = customFilter(numbers) { it % 2 == 0}
    println("Evens : $evens")

    val names = listOf("Alice","Bob","Anna","Alex")
    val aNames = customFilter(names){ it.startsWith("A")}
    println("A-names : $aNames")
}