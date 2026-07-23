/*
    ! Kotlin - Async / Await

    * async { } -> starts coroutine that return a value
    * await() -> waits for the result


    ? launch vs async
    * launch -> no return value(job)
    * async -> returns a value (Deferred<T>)


    * Use async when you need the result
    * Use launch when you just need to do something.

    ! async starts immediately
    ! await suspends until result is ready
*/

import kotlinx.coroutines.*

suspend fun fetchPrice(product: String): Double {
    val prices = mapOf(
        "Laptop" to 999.99,
        "Phone" to 699.99,
        "Tablet" to 449.99,
        "Watch" to 299.99
    )
    delay((300L..800L).random())   // ? simulate variable network speed
    return prices[product] ?: 0.0
}


suspend fun fetchDiscount(product: String): Double {
    delay((200L..500L).random())
    val discounts = mapOf(
        "Laptop" to 10.0,
        "Phone" to 15.0,
        "Tablet" to 5.0,
        "Watch" to 20.0
    )
    return discounts[product] ?: 0.0
}

suspend fun fetchReviews(product: String): Int {
    delay((100L..400L).random())
    val reviews = mapOf(
        "Laptop" to 1250,
        "Phone" to 3400,
        "Tablet" to 890,
        "Watch" to 2100
    )
    return reviews[product] ?: 0
}

fun main() = runBlocking{
    // * BASIC ASYNC / AWAIT

    println("--- Basic Async ---")

    val deferred: Deferred<Double> = async {
        fetchPrice("Laptop")
    }

    println("Doing other work...")
    val price = deferred.await()   // ? waits for result
    println("Laptop price: $$price")

    println()

    // * MULTIPLE ASYNC (concurrent)

    println("--- Multiple Async ---")
    val start = System.currentTimeMillis()

    val laptopPrice = async { fetchPrice("Laptop") }
    val phonePrice = async { fetchPrice("Phone") }
    val tabletPrice = async { fetchPrice("Tablet") }
    val watchPrice = async { fetchPrice("Watch") }

    // ? All 4 requests run concurrently
    println("Laptop : $${laptopPrice.await()}")
    println("Phone  : $${phonePrice.await()}")
    println("Tablet : $${tabletPrice.await()}")
    println("Watch  : $${watchPrice.await()}")

    val elapsed = System.currentTimeMillis() - start
    println("All fetched in: ${elapsed}ms")
    // ? ~800ms instead of ~2400ms sequential

    println()

    // * ASYNC WITH COMPUTATION

    println("--- Compute with Async ---")

    val product = "Phone"

    val dPrice = async { fetchPrice(product) }
    val dDiscount = async { fetchDiscount(product) }
    val dReviews = async { fetchReviews(product) }

    val finalPrice = dPrice.await() * (1 - dDiscount.await() / 100)
    val reviews = dReviews.await()

    println("$product:")
    println("  Original:  $${dPrice.await()}")
    println("  Discount:  ${dDiscount.await()}%")
    println("  Final:     $${"%.2f".format(finalPrice)}")
    println("  Reviews:   $reviews")

    println()
    
    // * LAUNCH vs ASYNC

    println("--- Launch vs Async ---")

    // ? launch -> no result
    val job = launch {
        delay(100)
        println("  Launch: just does work, no return")
    }
    job.join()

    // ? async -> has result
    val result = async {
        delay(100)
        42   // ? returns this value
    }
    println("  Async: returned ${result.await()}")

    println()

    // * STRUCTURED CONCURRENCY

    println("--- Structured ---")

    suspend fun fetchProductInfo(name: String) = coroutineScope {
        val price = async { fetchPrice(name) }
        val discount = async { fetchDiscount(name) }
        val reviews = async { fetchReviews(name) }

        "  $name: $${"%.2f".format(price.await())}, " +
        "${discount.await()}% off, " +
        "${reviews.await()} reviews"
    }

    val products = listOf("Laptop", "Phone", "Tablet", "Watch")

    products.map { product ->
        async { fetchProductInfo(product) }
    }.forEach { deferred ->
        println(deferred.await())
    }

    println()

    // * ASYNC LAZY (starts only when await is called)

    println("--- Lazy Async ---")

    val lazyResult = async(start = CoroutineStart.LAZY) {
        println("  Computing...")
        delay(500)
        99
    }

    println("  Created but not started yet")
    delay(1000)
    println("  Now starting...")
    println("  Result: ${lazyResult.await()}")
}