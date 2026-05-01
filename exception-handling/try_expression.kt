/*
    ! Kotlin - Try as Exception

    * In Java, try-catch is a statement (no return value)
    * In Kotlin, try-catch is an expression (returns a value)

    * val result = try {something} catch(e){default}
    ? Returns last expression from try block if sucess
    ? Returns last expression from catch block if error

    * Combine with ?. and ?: for clean error handling

*/

fun main(){
    //! Basic try expression
    println("-------- Basic try expression example: --------")
    val input = "42"
    val number = try{
        input.toInt()
    } catch(e: NumberFormatException){
        0 //? Default value if conversion fails
    }
    println("Number: $number")

    
    val badInput = "hello"

    val number2 = try {
        badInput.toInt()
    } catch (e: NumberFormatException) {
        -1   // ? default when parsing fails
    }

    println("Number2: $number2")   // -1

    println()

    //! Try expression with nullable types
    println("-------- Try expression with nullable types example: --------")

    val number3 : Int? = try{
        "abc".toInt()
    }
    catch(e: NumberFormatException){
        null //? Return null if conversion fails
    }
    println("Number3: $number3")
    println()

    //! Safe parsing functions
    println("-------- Safe parsing functions example: --------")

    fun safeParseInt(input: String): Int? = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        null
    }

    fun safeParseDouble(input: String): Double? = try {
        input.toDouble()
    } catch (e: NumberFormatException) {
        null
    }
    println(safeParseInt("42"))       // 42
    println(safeParseInt("abc"))      // null
    println(safeParseDouble("3.14"))  // 3.14
    println(safeParseDouble("xyz"))   // null

    // ? Combine with elvis for default
    val age = safeParseInt("abc") ?: 0
    println("Age with default: $age")   // 0

    println()

    //! Try expression in when
    println("-------- Try expression in when example: --------")
    fun parseValue(input : String):Any{
        return try{
            input.toInt()
        }catch(e: NumberFormatException){
            try{
                input.toDouble()
            }catch(e: NumberFormatException){
                input //? Return original string if all parsing fails
            }
        }
    }
    println("--- Parse Any Value ---")
    println("42    -> ${parseValue("42")}")       // 42 (Int)
    println("3.14  -> ${parseValue("3.14")}")     // 3.14 (Double)
    println("hello -> ${parseValue("hello")}")    // hello (String)

    println()


    // * REAL WORLD -> Config parser

    data class Config(
        val host: String,
        val port: Int,
        val maxRetries: Int,
        val timeout: Double
    )

    fun parseConfig(raw: Map<String, String>): Config {
        return Config(
            host = raw["host"] ?: "localhost",
            port = try {
                raw["port"]?.toInt() ?: 8080
            } catch (e: NumberFormatException) {
                8080
            },
            maxRetries = try {
                raw["maxRetries"]?.toInt() ?: 3
            } catch (e: NumberFormatException) {
                3
            },
            timeout = try {
                raw["timeout"]?.toDouble() ?: 30.0
            } catch (e: NumberFormatException) {
                30.0
            }
        )
    }

    // ? Good config
    val config1 = parseConfig(mapOf(
        "host" to "example.com",
        "port" to "9090",
        "maxRetries" to "5",
        "timeout" to "60.0"
    ))
    println("Config1: $config1")

    // ? Bad config -> uses defaults
    val config2 = parseConfig(mapOf(
        "host" to "example.com",
        "port" to "not_a_number",
        "timeout" to "invalid"
    ))
    println("Config2: $config2")

    println()

    //! Using toIntOrNull() -> Kotlin built-in alternative
    println("-------- Using toIntOrNull() example: --------")

    // ? Kotlin already has safe parsing built in
    println("42".toIntOrNull())      // 42
    println("abc".toIntOrNull())     // null
    println("3.14".toDoubleOrNull()) // 3.14
    println("xyz".toDoubleOrNull())  // null

    // ? With elvis
    val port = "not_a_number".toIntOrNull() ?: 8080
    println("Port: $port")   // 8080

    // ? These are cleaner than try-catch for simple parsing
    // ! Use try-catch for more complex error handling
}