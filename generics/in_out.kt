/*
    ! Kotin Variance(in / out) -->

    * The Problem :
     ? Is List<Dog> a subtype of List<Animal>
     ? In Java : No (invarient by default)
     ? In kotlin : Depends on in / out

    * out T (Coveriance) = Producer
     ? Can only return T, never take T as parameter
     ? Makes Box<Dog> a subtype of Box<Animal>
     ? Think : "out = output = read only"

    * in T (Contravarience ) = Consumer
     ? Can only Accept T as parameter, never return T
     ? Makes Handler<Animal> a subtype of Handler <Dog>
     ? think : "in = input = write only"

    * neither (Invarient)
     ? Can both read and write T
     ? No subtype relationship

    
*/


// * SETUP

open class Animal(val name: String) {
    override fun toString() = name
}

class Dog(name: String) : Animal(name)
class Cat(name: String) : Animal(name)

// * INVARIANT (no in/out) -> default

class InvariantBox<T>(var value: T)   // ? can read AND write

// * COVARIANT (out) -> Producer / Read Only

class ProducerBox<out T>(private val value: T) {
    fun get(): T = value                // ? can RETURN T
    // fun set(item: T) {}              // ! CANNOT accept T as param
}

// * CONTRAVARIANT (in) -> Consumer / Write Only

class ConsumerBox<in T> {
    fun accept(item: T) {              // ? can ACCEPT T as param
        println("Accepted: $item")
    }
    // fun get(): T = ...              // ! CANNOT return T
}

// * REAL WORLD -> Covariant List (read only)

interface Source<out T> {
    fun next(): T
}

class AnimalSource : Source<Animal> {
    private val animals = listOf(Dog("Rex"), Cat("Whiskers"), Dog("Buddy"))
    private var index = 0

    override fun next(): Animal {
        val animal = animals[index % animals.size]
        index++
        return animal
    }
}

// * REAL WORLD -> Contravariant Comparator (consumer)

interface Printer<in T> {
    fun print(item: T)
}

class AnimalPrinter : Printer<Animal> {
    override fun print(item: Animal) {
        println("  Animal: ${item.name}")
    }
}

fun main() {

    // * INVARIANT -> No subtype relationship

    println("--- Invariant ---")

    val dogBox = InvariantBox(Dog("Rex"))
    // val animalBox: InvariantBox<Animal> = dogBox  // ! Compile error

    // ? InvariantBox<Dog> is NOT a subtype of InvariantBox<Animal>
    println("InvariantBox<Dog> cannot be assigned to InvariantBox<Animal>")

    println()

    // * COVARIANT (out) -> Producer

    println("--- Covariant (out) ---")

    val dogProducer = ProducerBox(Dog("Rex"))
    val animalProducer: ProducerBox<Animal> = dogProducer  // ? Works!

    // ? ProducerBox<Dog> IS a subtype of ProducerBox<Animal>
    println("Got: ${animalProducer.get()}")

    println()

    // * CONTRAVARIANT (in) -> Consumer

    println("--- Contravariant (in) ---")

    val animalConsumer = ConsumerBox<Animal>()
    val dogConsumer: ConsumerBox<Dog> = animalConsumer  // ? Works!

    // ? ConsumerBox<Animal> IS a subtype of ConsumerBox<Dog>
    dogConsumer.accept(Dog("Buddy"))

    println()

    // * KOTLIN BUILT-IN EXAMPLES

    println("--- Built-in Examples ---")

    // ? List<out E> -> covariant (read only)
    val dogs: List<Dog> = listOf(Dog("Rex"), Dog("Buddy"))
    val animals: List<Animal> = dogs   // ? Works because List is out

    println("Animals: $animals")

    // ? MutableList<E> -> invariant (read + write)
    val mutableDogs: MutableList<Dog> = mutableListOf(Dog("Rex"))
    // val mutableAnimals: MutableList<Animal> = mutableDogs  // ! Error

    println()

    // * SOURCE EXAMPLE (out)

    println("--- Source Example ---")
    val source: Source<Animal> = AnimalSource()
    println("Next: ${source.next()}")
    println("Next: ${source.next()}")
    println("Next: ${source.next()}")

    println()

    // * PRINTER EXAMPLE (in)

    println("--- Printer Example ---")
    val animalPrinter: Printer<Animal> = AnimalPrinter()
    val dogPrinter: Printer<Dog> = animalPrinter  // ? Works because in

    dogPrinter.print(Dog("Rex"))
    dogPrinter.print(Dog("Buddy"))

    println()

    // * SUMMARY

    println("--- Summary ---")
    println("out T  -> Producer  -> can return T     -> Box<Dog> is subtype of Box<Animal>")
    println("in T   -> Consumer  -> can accept T     -> Box<Animal> is subtype of Box<Dog>")
    println("neither -> Invariant -> can read & write -> no subtype relationship")

    println()

    // * CHEAT SHEET

    println("--- When to use what? ---")
    println("Read only  collection -> use out (like List)")
    println("Write only handler    -> use in  (like Comparable)")
    println("Read + Write          -> use neither (like MutableList)")
}
