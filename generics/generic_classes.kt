/*
    ! Generic Classes in Kotlin

    * class Box<T>(val value:T)
    ? T can be any type
    ? Decided when you create the object

    * You can have multiple type params
    ? class Pair<A,B>(val first:A,val second:B)

    ! without generics you would use Any
    ! But then you lose type safety

*/

//! Basic generic class
class Box<T>(val value:T){
    fun display(){
        println("Box contains : $value (${value!!::class.simpleName})")
    }
}

//! generic clss with multiple type params

class MyPair<A,B>(val first:A,val second  :B){
    override fun toString() = "($first,$second)"
}

//! Generic stack (real use case)
class Stack<T> {
    private val items  = mutableListOf<T>()

    fun push(item:T){
        items.add(item)
    }
    fun pop():T{
        if(items.isEmpty()) throw RuntimeException("Stack is empty")
        return items.removeAt(items.size - 1)
    }

    fun peek():T{
        if (items.isEmpty()) throw RuntimeException("Stack is empty")
        return items.last()
    }
    fun isEmpty() = items.isEmpty()
    fun size() = items.size

    override fun toString() = items.toString()
}

// ! generic repository 

data class User(val id: Int , val name : String)
data class Product(val id : Int, val title : String)

class Repository<T>{
    private val items = mutableListOf<T>()
    fun add(item : T) {items.add(item)}
    fun getAll():List<T> = items.toList()
    fun getById(idx : Int):T = items[idx]
    fun count() = items.size

    override fun toString() = items.toString()
}


fun main() {

    // basic generic class
    val intBox = Box(42)
    val strBox = Box("Hello")
    val boolBox = Box(true)

    intBox.display()
    strBox.display()
    boolBox.display()


    // * Multiple types parmas
    val pair1 = MyPair("name","Alice")
    val pair2 = MyPair(1,true)
    val pair3 = MyPair("PI",3.14)

    println("Pair 1 : $pair1")
    println("Pair 2 : $pair2")
    println("Pair 3 : $pair3")

    // * Stack Usages
    println("+++ Int Stack +++")
    val intStack = Stack<Int>()
    intStack.push(10)
    intStack.push(20)
    intStack.push(30)

    println("Stack : $intStack")
    println("Peek   : ${intStack.peek()}")
    println("Pop   : ${intStack.pop()}")
    println("Stack  : ${intStack}")

    println("+++ String Stack +++")
    val strStack = Stack<String>()
    strStack.push("first")
    strStack.push("second")
    strStack.push("third")

    println("Stack  : $strStack")
    println("Pop    : ${strStack.pop()}")
    println("Stack  : $strStack")

    // * Repository Usages

    println("+++ Repository Usage +++")
    val productRepo = Repository<Product>()
    productRepo.add(Product(1,"Laptop"))
    productRepo.add(Product(2,"Phone"))

    println("All products : ${productRepo.getAll()}")

    //? Same repository class works for User and Product
    // ? No code duplication
}