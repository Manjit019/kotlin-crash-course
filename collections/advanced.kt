/*
  ! Advanced collection operations in Kotlin :

     Kotlin collections are powerful due to the ability to perform advanced operations on them, such as filtering, mapping, and folding.
     These operations allow you to perform complex operations on collections and produce new collections with the desired results.

  --------------------------------------------------

  * Important functions:

    ? filter()  -> returns a new collection containing only the elements that match the given predicate.

    ? map()     -> returns a new collection containing the results of applying the given function to each element of the original collection.

    ? reduce()  -> returns a single value that is the result of applying the given function to the elements of the original collection.

    ? fold()    -> returns a single value that is the result of applying the given function to the elements of the original collection.

    ? groupBy() -> returns a map where the keys are the results of applying the given function to the elements of the original collection.

    ? sortedBy() -> returns a new collection containing the elements of the original collection sorted by the given function.

  --------------------------------------------------

  * Syntaxes : 

    ? filter()  -> val filteredCollection = collection.filter(predicate)
    ? map()     -> val mappedCollection = collection.map(transform)
    ? reduce()  -> val reducedValue = collection.reduce(operation)
    ? fold()    -> val foldedValue = collection.fold(initialValue, operation)
    ? groupBy() -> val groupedCollection = collection.groupBy(keySelector)
    ? sortedBy() -> val sortedCollection = collection.sortedBy(keySelector)

  --------------------------------------------------

   ? reduce() vs fold() :
    - reduce() is a terminal operation that returns a single value.
    - fold() is a non-terminal operation that returns a value that is the result of applying the given function to the elements of the original collection.

  --------------------------------------------------


*/

// Code Example :

fun main(){

    // ! filter() example
    val numbers = listOf(1, 2, 0, 9,3, 4, 5)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers) // Output: [2,0, 4]

    // ! map() example
    val squares = numbers.map { it * it }
    println(squares) // Output: [1, 4, 0, 81, 9, 16, 25]

    // ! reduce() example
    val sum = numbers.reduce { acc, num -> acc + num }
    println(sum) // Output: 24

    // ! fold() example
    val product = numbers.fold(1) { acc, num -> acc * num }
    println(product) // Output: 0

    // ! groupBy() example
    val groupedNumbers = numbers.groupBy { it % 2 }
    println(groupedNumbers) // Output: {1=[1, 9, 3, 5], 0=[2, 0, 4]}

    // ! sortedBy() example
    val sortedNumbers = numbers.sortedBy { it }
    println(sortedNumbers) // Output: [0, 1, 2, 3, 4, 5, 9]


}