/*
    ! Kotlin - Flow

    * Flow = asynchronous stream of values

    * Regular function -> return one value
    * Flow -> emits multiple values over time


    ? Key functions :
    * flow { } -> creates a flow
    * emit(value) -> send a value
    * collect { } -> receive values


    * Flow is cold -> doesn't run until collected
    * Flow is sequential by default

    ? Flow operators :
    * map, filter, take, zip, combine
    * onEach, onStart, onCompletion, catch
*/

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // * BASIC FLOW

    println("--- Basic Flow ---")

    val numberFlow = flow {
        for (i in 1..5) {
            delay(300)
            emit(i)   // ? send value
        }
    }

    // ? collect -> receive values
    numberFlow.collect { value ->
        println("  Received: $value")
    }

    println()

    // * FLOW IS COLD (doesnt run until collected)

    println("--- Cold Flow ---")

    val coldFlow = flow {
        println("  Flow started!")
        emit(1)
        emit(2)
        emit(3)
    }

    println("  Flow created, but NOT started yet")
    println("  Now collecting...")
    coldFlow.collect { println("  Got: $it") }

    println()

    // * FLOW BUILDERS

    println("--- Flow Builders ---")

    // ? flowOf -> from fixed values
    val flow1 = flowOf(1, 2, 3, 4, 5)

    // ? .asFlow() -> from collection
    val flow2 = listOf("a", "b", "c").asFlow()

    // ? range as flow
    val flow3 = (1..10).asFlow()

    print("  flowOf:    ")
    flow1.collect { print("$it ") }
    println()

    print("  asFlow:    ")
    flow2.collect { print("$it ") }
    println()

    print("  range:     ")
    flow3.collect { print("$it ") }
    println()

    println()

    // * FLOW OPERATORS

    println("--- Operators ---")

    // ? map
    print("  map:       ")
    (1..5).asFlow()
        .map { it * it }
        .collect { print("$it ") }
    println()

    // ? filter
    print("  filter:    ")
    (1..10).asFlow()
        .filter { it % 2 == 0 }
        .collect { print("$it ") }
    println()

    // ? take
    print("  take(3):   ")
    (1..100).asFlow()
        .take(3)
        .collect { print("$it ") }
    println()

    // ? transform
    print("  transform: ")
    (1..3).asFlow()
        .transform { value ->
            emit("${value}a")
            emit("${value}b")
        }
        .collect { print("$it ") }
    println()

    println()

    // * TERMINAL OPERATORS

    println("--- Terminal Operators ---")

    val nums = (1..10).asFlow()

    println("  toList:  ${nums.toList()}")
    println("  first:   ${nums.first()}")
    println("  count:   ${nums.count()}")
    println("  reduce:  ${nums.reduce { a, b -> a + b }}")
    println("  fold:    ${nums.fold(0) { acc, v -> acc + v }}")

    println()

    // * onEach, onStart, onCompletion

    println("--- Lifecycle ---")

    (1..3).asFlow()
        .onStart { println("  Flow starting...") }
        .onEach { println("  Processing: $it") }
        .onCompletion { println("  Flow completed!") }
        .collect()

    println()

    // * ERROR HANDLING WITH catch

    println("--- Error Handling ---")

    flow {
        emit(1)
        emit(2)
        throw RuntimeException("Something broke!")
        emit(3)   // ? never reached
    }
        .catch { e -> println("  Caught: ${e.message}") }
        .collect { println("  Value: $it") }

    println()

    // * REAL WORLD -> Stock price stream

    println("--- Stock Prices ---")

    fun stockPrices(symbol: String): Flow<Double> = flow {
        var price = 100.0
        repeat(5) {
            price += (-5..5).random()
            delay(300)
            emit(price)
        }
    }

    stockPrices("KTLN")
        .onStart { println("  Watching KTLN...") }
        .onEach { price -> println("  KTLN: $${"%.2f".format(price)}") }
        .onCompletion { println("  Stream ended") }
        .collect()

    println()

    // * REAL WORLD -> Search with debounce

    println("--- Search Simulation ---")

    fun searchQueries(): Flow<String> = flow {
        val queries = listOf("k", "ko", "kot", "kotl", "kotli", "kotlin")
        queries.forEach {
            delay(200)
            emit(it)
        }
    }

    suspend fun search(query: String): List<String> {
        delay(100)
        return listOf("$query result 1", "$query result 2")
    }

    searchQueries()
        .filter { it.length >= 3 }   // ? only search if 3+ chars
        .map { query -> query to search(query) }
        .collect { (query, results) ->
            println("  '$query' -> $results")
        }

    println()
}