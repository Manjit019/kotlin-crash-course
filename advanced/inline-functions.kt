/*
    ! Kotlin - Inline Functions

    * "inline" copies function body into call site, preserving stack trace
    * Avoids creating lambda objects (better performance)

    ? When to use:
    * Higher-order functions with lambda
    * Small utility functions called frequently
    * Functions with reified type parameters

    ? Keywords:
    * inline -> inline entire function
    * noinline -> don't inline specific lambda param
    * crossinline -> lambda can't use non-local return


    ? Non-local return : 
    * In inline lambda, "return" returns form outer function
    * In regular lambda, "return" is not allowed

    
*/

// * BASIC INLINE

inline fun measureTime(action: () -> Unit): Long {
    val start = System.nanoTime()
    action()
    val end = System.nanoTime()
    return (end - start) / 1_000_000
}

// * INLINE vs NON-INLINE comparison

fun nonInlineRepeat(times: Int, action: (Int) -> Unit) {
    for (i in 0 until times) action(i)
}

inline fun inlineRepeat(times: Int, action: (Int) -> Unit) {
    for (i in 0 until times) action(i)
}


// * NOINLINE -> exclude specific lambda from inlining

inline fun doWork(
    inlineAction: () -> Unit,
    noinline savedAction: () -> Unit   // ? not inlined, can be stored
) {
    inlineAction()
    val saved = savedAction   // ? can assign because noinline
    saved()
}

// * CROSSINLINE -> prevent non-local return

inline fun runInThread(crossinline action: () -> Unit) {
    Thread {
        action()
        // ? action cant use "return" to exit outer function
        // ? because its running in a different thread
    }.start()
}

// * INLINE PROPERTY

class Circle(val radius: Double) {
    // ? inline property -> getter is inlined at call site
    inline val area: Double
        get() = Math.PI * radius * radius

    inline val circumference: Double
        get() = 2 * Math.PI * radius
}


// * PRACTICAL INLINE FUNCTIONS

inline fun <T> tryOrNull(action: () -> T): T? {
    return try {
        action()
    } catch (e: Exception) {
        null
    }
}

inline fun unless(condition: Boolean, action: () -> Unit) {
    if (!condition) action()
}

inline fun <T> List<T>.forEachReversed(action: (T) -> Unit) {
    for (i in this.size - 1 downTo 0) {
        action(this[i])
    }
}


inline fun benchmark(label: String, iterations: Int = 1000, action: () -> Unit) {
    val times = mutableListOf<Long>()
    repeat(iterations) {
        val start = System.nanoTime()
        action()
        times.add(System.nanoTime() - start)
    }
    val avg = times.average() / 1000   // ? microseconds
    println("  $label: avg ${"%.2f".format(avg)} us over $iterations iterations")
}

fun main(args: Array<String>) {
    // * BASIC INLINE

    println("--- measureTime ---")

    val time = measureTime {
        var sum = 0L
        for (i in 1..1_000_000) sum += i
        println("  Sum: $sum")
    }
    println("  Time: ${time}ms")

    println()

    // * NON-LOCAL RETURN (only works with inline)

    println("--- Non-local Return ---")

    fun findFirstEven(numbers: List<Int>): Int? {
        // ? "return" inside inline lambda returns from findFirstEven
        numbers.forEach { num ->
            if (num % 2 == 0) return num   // ? returns from findFirstEven
        }
        return null
    }

    println("  First even in [1,3,5,4,7]: ${findFirstEven(listOf(1, 3, 5, 4, 7))}")
    println("  First even in [1,3,5]:     ${findFirstEven(listOf(1, 3, 5))}")

    println()

    // * PRACTICAL INLINE

    println("--- tryOrNull ---")

    val number = tryOrNull { "42".toInt() }
    val bad = tryOrNull { "abc".toInt() }

    println("  Parse '42':  $number")
    println("  Parse 'abc': $bad")

    println()

    // * UNLESS

    println("--- unless ---")

    val isLoggedIn = false
    unless(isLoggedIn) {
        println("  Please log in!")
    }

    println()

    
    // * FOREACH REVERSED

    println("--- forEachReversed ---")

    listOf("a", "b", "c", "d").forEachReversed {
        print("  $it")
    }
    println()

    println()

    // * BENCHMARK

    println("--- Benchmark ---")

    benchmark("String concat", 10000) {
        var s = ""
        for (i in 1..100) s += "x"
    }

    benchmark("StringBuilder", 10000) {
        val sb = StringBuilder()
        for (i in 1..100) sb.append("x")
        sb.toString()
    }

    println()

    // * INLINE PROPERTY

    println("--- Inline Property ---")

    val circle = Circle(5.0)
    println("  Radius:        ${circle.radius}")
    println("  Area:          ${"%.2f".format(circle.area)}")
    println("  Circumference: ${"%.2f".format(circle.circumference)}")
}

