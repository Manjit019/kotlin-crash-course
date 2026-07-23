/*
    ! Kotlin Coroutines :

    * Kotlin Coroutines is a new concurrency model introduced in Kotlin 1.3 that allows you to write asynchronous code in a way that is easier to read and maintain.

    ? runBlocking { } is a function that is used to run a coroutine synchronously.
        runBlocking{ } -> Bridges regular and coroutine world
    
    ? launch { } is a function that is used to run a coroutine asynchronously.
        launch { } -> Starts a new coroutine (no result)

    ? delay() -> non-blocking sleep (pauses coroutine,not thread)

    * Coroutine are not Threads
    ? Threads are expensive (OS Managed)
    ? Coroutines are cheap (Kotlin Managed)
    ? 100,000 coroutines = easy
    ? 100,000 threads = crash

    ! delay() is not Thread.sleep()
    - delay pauses coroutine, tread is free for other work.
*/

import kolinx.coroutines.*;

fun main(){

    //* First Coroutine
    println("---First Coroutine ---");
    runBlocking {
        println("Before Delay")
        delay(1000) //pauses 1 second(non-blocking)
        println("After Delay")
    }
    println("After runBlocking")

    println()

    //* Launch -> fire and forget */
    runBlocking{
        launch{
            delay(500)
            println(" Coroutine 1 done (500ms)")
        }
        launch {
            delay(300)
            println("  Coroutine 2 done (300ms)")
        }

        launch {
            delay(100)
            println("  Coroutine 3 done (100ms)")
        }

        println("  All launched!")
    }

    // ? Output order: All launched -> 3 -> 2 -> 1
    // ? They run concurrently!

    // * SEQUENTIAL vs CONCURRENT

    println("--- Sequential ---")
    runBlocking {
        val start = System.currentTimeMillis()

        // ? One after another
        delay(500)
        println("  Task 1 done")
        delay(500)
        println("  Task 2 done")

        val time = System.currentTimeMillis() - start
        println("  Total: ${time}ms")   // ~1000ms
    }

    println()

    println("--- Concurrent ---")
    runBlocking {
        val start = System.currentTimeMillis()

        // ? Both at same time
        val job1 = launch {
            delay(500)
            println("  Task 1 done")
        }
        val job2 = launch {
            delay(500)
            println("  Task 2 done")
        }

        job1.join()
        job2.join()

        val time = System.currentTimeMillis() - start
        println("  Total: ${time}ms")   // ~500ms (half the time!)
    }

    println()

    // * MANY COROUTINES (try this with threads!)

    println("--- 10,000 Coroutines ---")
    runBlocking {
        val start = System.currentTimeMillis()

        val jobs = List(10_000) {
            launch {
                delay(1000)
            }
        }

        jobs.forEach { it.join() }

        val time = System.currentTimeMillis() - start
        println("  10,000 coroutines finished in ${time}ms")
        // ? All 10,000 finish in ~1 second
        // ! 10,000 threads would crash or take much longer
    }

    println()

    // * COROUTINE IS LIGHTWEIGHT

    println("--- Thread Info ---")
    runBlocking {
        launch {
            println("  Coroutine running on: ${Thread.currentThread().name}")
        }
        println("  Main running on: ${Thread.currentThread().name}")
    }
}