/*
    ! Kotlin - Dispatchers

    * Dispatchers decide which thread  the coroutin runs on and how it behaves.

    ? Types:
    * Dispatchers.Default -> CPU intensive work (sorting, parsing)
    * Dispatchers.IO -> I/O operations (networking, files ,database)
    * Dispatchers.Main -> UI thread (Android only)
    * Dispatchers.Unconfined -> runs on caller thread (testing)

    ? withContext(dispatcher) -> switch dispatcher inside coroutine

    ! Default -> limited to number of CPU cores
    ! IO -> can have many threads (64 or more)
*/

import kotlinx.coroutines.*

fun main() = runBlocking {
    // * CHECKING THREAD NAMES

    println("--- Thread Names ---")

    launch(Dispatchers.Default) {
        println("  Default: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("  IO:      ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("  Unconfined: ${Thread.currentThread().name}")
    }

    launch {
        println("  No dispatcher: ${Thread.currentThread().name}")
    }

    delay(500)
    println()

    // * withContext -> SWITCH DISPATCHER

    println("--- withContext ---")

    launch {
        println("  Started on: ${Thread.currentThread().name}")

        val result = withContext(Dispatchers.IO) {
            println("  Switched to IO: ${Thread.currentThread().name}")
            delay(500)
            "Data from IO"
        }

        println("  Back to: ${Thread.currentThread().name}")
        println("  Result: $result")
    }.join()

    println()

    // * SIMULATING REAL WORLD PATTERNS

    println("--- Real World Pattern ---")

    // ? Simulate heavy CPU work
    suspend fun parseData(data: String): List<Int> = withContext(Dispatchers.Default) {
        println("  [CPU] Parsing on: ${Thread.currentThread().name}")
        delay(300)
        data.split(",").map { it.trim().toInt() }
    }

    // ? Simulate network call
    suspend fun fetchData(): String = withContext(Dispatchers.IO) {
        println("  [IO] Fetching on: ${Thread.currentThread().name}")
        delay(500)
        "1, 2, 3, 4, 5"
    }

    // ? Simulate database save
    suspend fun saveResult(result: List<Int>) = withContext(Dispatchers.IO) {
        println("  [IO] Saving on: ${Thread.currentThread().name}")
        delay(200)
        println("  [IO] Saved: $result")
    }

    // ? Pipeline: fetch -> parse -> save
    val rawData = fetchData()
    val parsed = parseData(rawData)
    saveResult(parsed)

    println()

    // * MULTIPLE IO OPERATIONS CONCURRENTLY

    println("--- Concurrent IO ---")

    suspend fun readFile(name: String): String = withContext(Dispatchers.IO) {
        delay((200L..500L).random())
        "Content of $name"
    }

    val start = System.currentTimeMillis()

    val files = listOf("file1.txt", "file2.txt", "file3.txt", "file4.txt")

    val contents = files.map { file ->
        async(Dispatchers.IO) { file to readFile(file) }
    }

    contents.forEach { deferred ->
        val (name, content) = deferred.await()
        println("  $name: $content")
    }

    val elapsed = System.currentTimeMillis() - start
    println("  All read in: ${elapsed}ms")

    println()

    // * DISPATCHERS COMPARISON

    println("--- Comparison ---")

    suspend fun cpuWork() = withContext(Dispatchers.Default) {
        var sum = 0L
        for (i in 1..1_000_000) sum += i
        sum
    }

    suspend fun ioWork() = withContext(Dispatchers.IO) {
        delay(100)
        "IO Result"
    }

    val cpuResult = cpuWork()
    val ioResult = ioWork()

    println("  CPU: $cpuResult")
    println("  IO:  $ioResult")

    println()
    println("--- When to use ---")
    println("  Default -> sorting, parsing, math, algorithms")
    println("  IO      -> network, files, database, APIs")
    println("  Main    -> UI updates (Android)")
    println("  Unconfined -> testing only")
}