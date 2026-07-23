/*
    ! Kotlin Coroutines - Suspend functions

    * supend fun -> a function that can pause and resume

    ? Rules ->
    * Can only be called from  coroutine or another suspend function
    * Can use delay() and other suspend functions
    * Looks like regular function but can be paused

    ? Think of supend as "this function might take a while"
    * Network call
    * Database query 
    * File read
    * Heavy computation

    ! Cannot call suspend fun from regular fun directly
    ? Must be inside coroutine scope (launch , async, runBlocking)

*/

import kotlinx.coroutines.*

// * BASIC SUSPEND FUNCTION

suspend fun fetchUserName(): String {
    delay(1000)   //simulate network call
    return "Alice"
}

suspend fun fetchUserAge(): Int {
    delay(800)
    return 25
}

suspend fun fetchUserEmail(): String {
    delay(600)
    return "alice@email.com"
}


// * SUSPEND FUNCTION CALLING ANOTHER

data class User(val name: String, val age: Int, val email: String)

suspend fun fetchUser(): User {
    // ? These run sequentially
    val name = fetchUserName()
    val age = fetchUserAge()
    val email = fetchUserEmail()
    return User(name, age, email)
}


// * SIMULATING REAL WORLD APIs

suspend fun fetchWeather(city: String): String {
    delay(500)
    val temps = mapOf(
        "New York" to "22C",
        "London" to "15C",
        "Tokyo" to "28C"
    )
    return temps[city] ?: "Unknown"
}
suspend fun fetchNews(): List<String> {
    delay(700)
    return listOf(
        "Kotlin 2.0 released",
        "Android 15 announced",
        "JetBrains updates IntelliJ"
    )
}

suspend fun saveToDatabase(data: String) {
    delay(300)
    println("  [DB] Saved: $data")
}


fun main() = runBlocking{
    // * CALLING SUSPEND FUNCTIONS

    println("--- Basic Suspend ---")
    val start = System.currentTimeMillis()

    val name = fetchUserName()
    println("Name: $name")

    val elapsed = System.currentTimeMillis() - start
    println("Took: ${elapsed}ms")

    println()

    // * SEQUENTIAL SUSPEND CALLS

    println("--- Sequential (slow) ---")
    val start2 = System.currentTimeMillis()

    val user = fetchUser()
    println("User: $user")

    val elapsed2 = System.currentTimeMillis() - start2
    println("Took: ${elapsed2}ms")   // ~2400ms (1000 + 800 + 600)

    println()


    // * CONCURRENT SUSPEND CALLS (faster!)

    println("--- Concurrent (fast) ---")
    val start3 = System.currentTimeMillis()

    val dName = async { fetchUserName() }
    val dAge = async { fetchUserAge() }
    val dEmail = async { fetchUserEmail() }

    val fastUser = User(dName.await(), dAge.await(), dEmail.await())
    println("User: $fastUser")

    val elapsed3 = System.currentTimeMillis() - start3
    println("Took: ${elapsed3}ms")   // ~1000ms (max of all three)

    println()

    // * SUSPEND WITH PARAMETERS

    println("--- Weather ---")
    val cities = listOf("New York", "London", "Tokyo")

    // ? Sequential
    cities.forEach { city ->
        val temp = fetchWeather(city)
        println("  $city: $temp")
    }

    println()
    
    // ? Concurrent
    println("--- Weather (concurrent) ---")
    val start4 = System.currentTimeMillis()

    val results = cities.map { city ->
        async { city to fetchWeather(city) }
    }

    results.forEach { deferred ->
        val (city, temp) = deferred.await()
        println("  $city: $temp")
    }

    val elapsed4 = System.currentTimeMillis() - start4
    println("  Took: ${elapsed4}ms")   // ~500ms instead of ~1500ms

    println()

    // * SUSPEND FUNCTION PIPELINE

    println("--- Pipeline ---")
    val news = fetchNews()
    news.forEach { headline ->
        saveToDatabase(headline)
    }
    println("  All news saved!")
}