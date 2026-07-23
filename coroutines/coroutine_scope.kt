/*
    ! Kotlin - Coroutine Scope and Jobs

    * CoroutineScope -> Defines the lifetime of coroutines
    * Job -> Handle to a coroutine

    ? Key concepts:
    * Parent-child relationship
    * Cancelling parent cancels all children
    * Parent waits for all children to complete
    * Exception in child cancels parent (by default)

    * coroutineScope { } -> Creates scope, waits for all children
    * supervisorScope { } -> child failure doesn't cancel siblings

*/

import kotlinx.coroutines.*;

fun main() = runBlocking{
    // * JOB BASICS

    println("--- Job Basics ---")

    val job = launch {
        println("  Job started")
        delay(1000)
        println("  Job finished")
    }

    println("  Job is active: ${job.isActive}")
    job.join()   // ? wait for job to finish
    println("  Job is completed: ${job.isCompleted}")

    println()

    // * CANCELLING A JOB

    println("--- Cancel ---")

    val longJob = launch {
        repeat(10) { i ->
            println("  Working... $i")
            delay(300)
        }
    }

    delay(1000)
    println("  Cancelling!")
    longJob.cancel()
    longJob.join()   // ? wait for cancellation to complete
    println("  Job cancelled: ${longJob.isCancelled}")

    println()

    // * CANCEL WITH REASON

    println("--- Cancel with Reason ---")

    val job2 = launch {
        try {
            repeat(100) { i ->
                println("  Processing $i")
                delay(200)
            }
        } catch (e: CancellationException) {
            println("  Cancelled: ${e.message}")
        } finally {
            println("  Cleanup done")
        }
    }

    delay(600)
    job2.cancel(CancellationException("Timeout"))
    job2.join()

    println()

    
    println("--- Timeout ---")

    try {
        withTimeout(1000) {
            repeat(10) { i ->
                println("  Step $i")
                delay(300)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("  Timed out!\n")
    }
    // ? withTimeoutOrNull -> returns null instead of throwing
    val result = withTimeoutOrNull(500) {
        delay(1000)
        "Completed"
    }
    println("  Result: ${result ?: "Timed out"}")

    println()

    // * PARENT-CHILD RELATIONSHIP

    println("--- Parent-Child ---")

    val parent = launch {
        println("  Parent started")

        val child1 = launch {
            delay(500)
            println("  Child 1 done")
        }

        val child2 = launch {
            delay(300)
            println("  Child 2 done")
        }

        // ? Parent waits for ALL children automatically
    }

    parent.join()
    println("  Parent done (waited for all children)")

    println()

    
    println("--- Cancel Parent ---")

    val parentJob = launch {
        launch {
            delay(1000)
            println("  Child A done")   // ? wont print
        }
        launch {
            delay(1000)
            println("  Child B done")   // ? wont print
        }
    }

    delay(200)
    parentJob.cancel()
    parentJob.join()
    println("  Parent cancelled, all children cancelled too")

    println()


    // * coroutineScope -> structured scope

    println("--- coroutineScope ---")

    suspend fun doWork() = coroutineScope {
        launch {
            delay(300)
            println("  Task A done")
        }
        launch {
            delay(200)
            println("  Task B done")
        }
        println("  All tasks launched")
        // ? coroutineScope waits for ALL children
    }

    doWork()
    println("  All work done")

    println()

    // * supervisorScope -> child failure doesnt affect siblings

    println("--- supervisorScope ---")

    supervisorScope {
        val good = launch {
            delay(500)
            println("  Good child finished")
        }

        val bad = launch {
            delay(200)
            throw RuntimeException("Bad child failed")
        }

        try {
            bad.join()
        } catch (e: Exception) {
            println("  Bad child error: ${e.message}")
        }

        good.join()
        // ? Good child still finishes despite bad child failing
    }

    println()



    // * REAL WORLD -> Download manager

    println("--- Download Manager ---")

    suspend fun downloadFile(name:String,timeMs : Long):String{
        println("  Downloading $name")
        delay(timeMs)
        return `$name downloaded`
    }

    coroutineScope {
        val files = listOf(
            "photo.jpg" to 500L,
            "video.mp4" to 800L,
            "doc.pdf" to 300L,
            "music.mp3" to 600L
        )
        val results = files.map { (name,time) -> async {
            downloadFile(name, time)
        }}

        results.forEach { deferred -> 
            println(" ${deferred.await()}")
        }

        println("  All downloads done")
    }
}