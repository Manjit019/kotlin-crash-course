/*
    ! Generic Constraints

    * <T : SomeType> means T must be SomeType or its subclass

    * Common constrains : 
    ? <T : Number >  -> ONly numbers (Int, Double, Float)
    ? <T : Comparable<T>>   -> only comparable types
    ? <T : Any>   -> any non-nullable type(default)

    * Multiple constraints use "where"
    ? fun <T> sort(list : List<T>) where T : Comparable<T> , T : Number

    ! Without constraints use "where"
    ! Constaints help you LIMIT what T can be

*/

// ! Basic constraints -> T must be Number

fun <T : Number> doubleValue(value : T) : Double {
    return value.toDouble() * 2
}

// ! Constraint -> T must be Comparable
fun <T :Comparable<T>> findMax(a:T,b:T):T{
    return if (a > b) a else b
}

fun <T : Comparable<T>> findMin(list : List<T>):T{
    var min = list.first()
    for (item in list){
        if (item < min) min = item
    }

    return min
}

// ! Multiple constraints using where

fun <T> sortAndSum(list : List<T>): Double 
    where T : Number, T : Comparable<T> {
        val sorted  = list.sorted()
        println("Sorted : $sorted")
        return sorted.sumOf{it.toDouble()}
    }

// ! Constrained class

class NumberBox<T : Number> (val value : T){
    fun toDouble() = value.toDouble()
    fun toInt() = value.toInt()
    override fun toString() = "NumberBox($value)"
}

// ! Real world -> generic min/max finder

class MinMaxFinder<T : Comparable<T>> {
    private val items = mutableListOf<T>()

    fun add(item : T) { items.add(item)}
    fun addAll(vararg item : T) { items.addAll(item)}
    fun min():T = items.min()
    fun max() : T  = items.max()
    fun sorted(): List<T> = items.sorted()

    override fun toString() = items.toString()
}

fun main() {

    // * BASIC NUMBER CONSTRAINT

    println("--- Number Constraint ---")
    println(doubleValue(5))       // 10.0
    println(doubleValue(3.14))    // 6.28
    println(doubleValue(7L))      // 14.0

    // ! doubleValue("hello")   // Compile error! String is not Number

    println()

    // * COMPARABLE CONSTRAINT

    println("--- Comparable Constraint ---")
    println("Max(3, 7)     : ${findMax(3, 7)}")
    println("Max(a, z)     : ${findMax("a", "z")}")
    println("Max(3.1, 2.5) : ${findMax(3.1, 2.5)}")

    println()

    println("--- Find Min ---")
    println("Min of [5,2,8,1,9] : ${findMin(listOf(5, 2, 8, 1, 9))}")
    println("Min of [c,a,b]     : ${findMin(listOf("c", "a", "b"))}")

    println()

    // * MULTIPLE CONSTRAINTS

    println("--- Multiple Constraints ---")
    val sum1 = sortAndSum(listOf(5, 3, 8, 1, 9))
    println("Sum : $sum1")

    val sum2 = sortAndSum(listOf(3.5, 1.2, 7.8))
    println("Sum : $sum2")

    println()

    // * CONSTRAINED CLASS

    println("--- NumberBox ---")
    val intBox = NumberBox(42)
    val doubleBox = NumberBox(3.14)

    println("$intBox    -> toDouble: ${intBox.toDouble()}")
    println("$doubleBox -> toInt: ${doubleBox.toInt()}")

    // ! NumberBox("hello")   // Compile error! String is not Number

    println()

    // * REAL WORLD -> MinMaxFinder

    println("--- MinMaxFinder ---")

    val intFinder = MinMaxFinder<Int>()
    intFinder.addAll(45, 12, 78, 3, 99, 56)

    println("Items  : $intFinder")
    println("Min    : ${intFinder.min()}")
    println("Max    : ${intFinder.max()}")
    println("Sorted : ${intFinder.sorted()}")

    println()

    val strFinder = MinMaxFinder<String>()
    strFinder.addAll("banana", "apple", "cherry", "date")

    println("Items  : $strFinder")
    println("Min    : ${strFinder.min()}")
    println("Max    : ${strFinder.max()}")
    println("Sorted : ${strFinder.sorted()}")
}