/*
     ! Kotlin - Channels

     * Channel - a way for coroutines to communicate with each other
     

     ? Like a pipe:
     * One coroutine sends value
     * Another coroutine receives value
     

     ? Channel vs Flow
     * Flow -> cold, one collector, declarative
     * Channel -> hot, multiple receivers, imperative
     

     ? Types:
     * Channel()  -> rendezvous (no buffer)
     * Channel(capacity) -> buffered
     * Channel(UNLIMITED) -> unlimited buffer
     * Channel(CONFLATED)  -> keeps only latest value
     
*/

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking{
    
    // * BASIC CHANNEL

    println("--- Basic Channel ---")

    val channel = Channel<Int>()

    // ? Producer
    launch {
        for (i in 1..5) {
            println("  Sending: $i")
            channel.send(i)
            delay(200)
        }
        channel.close()   // ! Always close when done
    }

    // ? Consumer
    for (value in channel) {
        println("  Received: $value")
    }

    println()

    // * PRODUCE -> shorthand for producer

    println("--- produce ---")

    val numbers = produce {
        for (i in 1..5) {
            delay(100)
            send(i)
        }
    }

    for (num in numbers) {
        println("  Got: $num")
    }

    println()
    
    // * BUFFERED CHANNEL

    println("--- Buffered Channel ---")

    val buffered = Channel<Int>(capacity = 3)

    launch {
        for (i in 1..5) {
            println("  Sending $i")
            buffered.send(i)   // ? wont suspend until buffer full
            println("  Sent $i")
        }
        buffered.close()
    }

    delay(1000)   // ? let producer fill buffer

    for (value in buffered) {
        println("  Received: $value")
    }

    println()

    // * MULTIPLE PRODUCERS, ONE CONSUMER

    println("--- Multiple Producers ---")

    val shared = Channel<String>()

    // ? Producer 1
    launch {
        repeat(3) {
            delay(300)
            shared.send("From Producer-1: item $it")
        }
    }
    // ? Producer 2
    launch {
        repeat(3) {
            delay(400)
            shared.send("From Producer-2: item $it")
        }
    }
    // ? Consumer
    launch {
        repeat(6) {
            println("  ${shared.receive()}")
        }
        shared.close()
    }.join()

    println()
    
    // * PIPELINE PATTERN

    println("--- Pipeline ---")

    // ? Stage 1: produce numbers
    fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
        for (i in 1..5) {
            delay(100)
            send(i)
        }
    }

    // ? Stage 2: square them
    fun CoroutineScope.square(input: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
        for (value in input) {
            send(value * value)
        }
    }
    // ? Stage 3: format them
    fun CoroutineScope.format(input: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
        for (value in input) {
            send("Result: $value")
        }
    }

    val pipeline = format(square(produceNumbers()))

    for (result in pipeline) {
        println("  $result")
    }

    println()

    // * FAN-OUT (one producer, multiple consumers)

    println("--- Fan-Out ---")

    val tasks = produce {
        repeat(10) {
            delay(100)
            send("Task-$it")
        }
    }

    // ? 3 workers consuming from same channel
    repeat(3) { workerId ->
        launch {
            for (task in tasks) {
                println("  Worker-$workerId processing: $task")
                delay(300)
            }
        }
    }

    delay(2000)

    println()

    // * REAL WORLD -> Event bus

    println("--- Event Bus ---")

    data class Event(val type: String, val data: String)

    val eventBus = Channel<Event>(Channel.UNLIMITED)

    // ? Event producers
    launch {
        eventBus.send(Event("CLICK", "Button A"))
        delay(100)
        eventBus.send(Event("SCROLL", "Page down"))
        delay(100)
        eventBus.send(Event("CLICK", "Button B"))
        delay(100)
        eventBus.send(Event("INPUT", "Username typed"))
        delay(100)
        eventBus.send(Event("CLICK", "Submit"))
        eventBus.close()
    }
    // ? Event consumer
    for (event in eventBus) {
        when (event.type) {
            "CLICK"  -> println("  [Click Handler]  ${event.data}")
            "SCROLL" -> println("  [Scroll Handler] ${event.data}")
            "INPUT"  -> println("  [Input Handler]  ${event.data}")
        }
    }

    println()

    println("--- Summary ---")
    println("  Channel    -> hot, send/receive, bidirectional")
    println("  Flow       -> cold, emit/collect, unidirectional")
    println("  produce    -> easy way to create producer")
    println("  Pipeline   -> chain channels together")
    println("  Fan-out    -> multiple consumers, one producer")
}