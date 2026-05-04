/*    
     ! KOTLIN — FUNCTION COMPOSITION
    
     * Composition = combining simple functions
       to build complex ones
    
     * f(g(x)) = "first apply g, then apply f"
    
     * compose(f, g) = f(g(x))
     * pipe(f, g)    = g(f(x))   (left to right)
    
     * Why composition?
       ? Build complex behavior from small pieces
       ? Each piece is easy to test
       ? Easy to reorder, add, remove steps
*/

fun main() {

    // * BASIC COMPOSITION -> manually

    fun double(x: Int) = x * 2
    fun addOne(x: Int) = x + 1
    fun square(x: Int) = x * x

    // ? Manual composition
    val result = addOne(double(square(3)))
    // square(3) = 9 -> double(9) = 18 -> addOne(18) = 19

    println("Manual: square -> double -> addOne")
    println("Result: $result")

    println()

    // * COMPOSE HELPER FUNCTION

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    // ? f(g(x)) = addOne(double(x))
    val doubleAndAddOne = compose(::addOne, ::double)

    println("--- Compose ---")
    println("doubleAndAddOne(5) = ${doubleAndAddOne(5)}")   // 11

    // ? Chaining composes
    val fullPipeline = compose(::addOne, compose(::double, ::square))
    println("square -> double -> addOne (3) = ${fullPipeline(3)}")   // 19

    println()

    // * PIPE (left to right, more readable)

    fun <A, B, C> pipe(f: (A) -> B, g: (B) -> C): (A) -> C {
        return { x -> g(f(x)) }
    }

    // ? Reads left to right: square -> double -> addOne
    val pipeline = pipe(pipe(::square, ::double), ::addOne)

    println("--- Pipe ---")
    println("square -> double -> addOne (3) = ${pipeline(3)}")   // 19

    println()

    // * STRING PROCESSING PIPELINE

    fun trim(s: String) = s.trim()
    fun lowercase(s: String) = s.lowercase()
    fun removeSpaces(s: String) = s.replace(" ", "_")
    fun addPrefix(s: String) = "user_$s"

    val processUsername = pipe(
        pipe(
            pipe(::trim, ::lowercase),
            ::removeSpaces
        ),
        ::addPrefix
    )

    println("--- String Pipeline ---")
    println(processUsername("  John Doe  "))    // user_john_doe
    println(processUsername(" ALICE Smith"))     // user_alice_smith

    println()

    // * EASIER WAY -> using extension function

    infix fun <A, B, C> ((A) -> B).then(f: (B) -> C): (A) -> C {
        return { x -> f(this(x)) }
    }

    val process = ::trim then ::lowercase then ::removeSpaces then ::addPrefix

    println("--- Using 'then' ---")
    println(process("  John Doe  "))    // user_john_doe
    println(process(" ALICE Smith"))    // user_alice_smith

    println()

    // * COMPOSING WITH LISTS

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // ? Each step is a pure function
    val isEven: (Int) -> Boolean = { it % 2 == 0 }
    val tripleIt: (Int) -> Int = { it * 3 }

    val result2 = numbers
        .filter(isEven)
        .map(tripleIt)
        .filter { it > 10 }

    println("--- List Composition ---")
    println("Original: $numbers")
    println("Result:   $result2")

    println()

    // * REAL WORLD -> Data processing pipeline

    data class Product(
        val name: String,
        val price: Double,
        val category: String
    )

    val products = listOf(
        Product("Laptop", 999.0, "electronics"),
        Product("Book", 15.0, "books"),
        Product("Phone", 699.0, "electronics"),
        Product("Pen", 2.0, "stationery"),
        Product("Tablet", 450.0, "electronics"),
        Product("Notebook", 5.0, "stationery"),
    )

    // ? Each step is a reusable function
    val onlyElectronics: (List<Product>) -> List<Product> = { list ->
        list.filter { it.category == "electronics" }
    }

    val sortByPrice: (List<Product>) -> List<Product> = { list ->
        list.sortedBy { it.price }
    }

    val formatPrices: (List<Product>) -> List<String> = { list ->
        list.map { "${it.name}: $${it.price}" }
    }

    // ? Compose the pipeline
    val processCatalog = onlyElectronics then sortByPrice then formatPrices

    println("--- Product Pipeline ---")
    processCatalog(products).forEach { println("  $it") }

    println()

    // * SUMMARY

    println("--- Summary ---")
    println("compose(f, g) = f(g(x))     -> right to left")
    println("pipe(f, g)    = g(f(x))     -> left to right")
    println("f then g      = g(f(x))     -> infix, readable")
    println("Each piece is small, testable, reusable")
}