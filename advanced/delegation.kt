/*
        ! Kotlin - Delegation

        * Delegation is a feature that allows you to delegate the implementation of a class to another object.

        * "by" keyword delegates work to another object

        ? Two types:
        * Class delegation -> delegates interface implementation
        * Property delegation -> delegates get/set behaviour

        ? Build-in property delegates: 
        * lazy  -> compute value on first access
        * observable    -> react when value changes
        * vetoable  -> reject invalid values
        * map   -> store properties in a map

        ? Why delegation?
        * Favor composition over inheritance
        * Reuse behavior without extending classes

*/

import kotlin.properties.Delegates

//* */ Class Delegation

interface Printer {
    fun print(message:String)
}
interface Logger {
    fun log(message:String)
}

class ConsolePrinter: Printer {
    override fun print(message:String){
        println("   [PRINT] $message")
    }
}

class FileLogger: Logger {
    override fun log(message:String){
        println("   [LOG] $message")
    }
}

// ? Without delegation -> manual forwarding
class ServiceManual(
    private val printer: Printer,
    private val logger: Logger
) : Printer, Logger {
    override fun print(message: String) = printer.print(message)
    override fun log(message: String) = logger.log(message)
}

// ? With delegation -> automatic forwarding
class ServiceDelegated(printer:Printer,logger:Logger): Printer by printer, Logger by logger


//* LAZY DELEGATION */

class ExpensiveResource {
    init {
        println(" Expensive resource created")
    }
    fun use() = println(" Using expensive resource")
}

// * OBSERVABLE DELEGATION

class UserProfile{
    var name:String by Delegates.observable("Guest"){ _ , old, new ->
        println("Name changed from $old to $new")
    }

    var age: Int by Delegates.vetoable(0) { _, _, new ->
        new >= 0   // ? reject negative age
    }
}

// * MAP DELEGATION

class Config(map:Map<String,Any?>){
    val host: String by map
    val port : Int by map
    val debug : Boolean by map
}

// * CUSTOM DELEGATE

class TrimmedString{
    private var value=""

    operator fun getValue(thisRef:Any?, property: kotlin.reflect.KProperty<*>):String{
        return value
    }

    operator fun setValue(thisRef:Any?, property: kotlin.reflect.KProperty<*>,newValue:String){
        value = newValue.trim()
    }
}

class UpperCaseString {
    private var value = ""

    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: String) {
        value = newValue.uppercase()
    }
}

class Form {
    var username: String by TrimmedString()
    var email: String by TrimmedString()
    var role: String by UpperCaseString()
}

fun main(args: Array<String>) {
    // * CLASS DELEGATION

    println("--- Class Delegation ---")

    val service = ServiceDelegated(ConsolePrinter(), FileLogger())
    service.print("Hello World")
    service.log("App started")

    println()

    // * LAZY

    println("--- Lazy ---")

    val resource by lazy {
        ExpensiveResource()
    }

    println("  Resource not created yet")
    println("  Accessing resource...")
    resource.use()   // ? created here on first access
    resource.use()   // ? already created, reused

    println()

    // * OBSERVABLE

    println("--- Observable ---")

    val profile = UserProfile()
    profile.name = "Alice"
    profile.name = "Bob"

    println()

    // * VETOABLE

    println("--- Vetoable ---")

    profile.age = 25
    println("  Age: ${profile.age}")   // 25

    profile.age = -5   // ? rejected!
    println("  Age after -5: ${profile.age}")   // still 25

    profile.age = 30
    println("  Age after 30: ${profile.age}")   // 30

    println()

    // * MAP DELEGATION

    println("--- Map Delegation ---")

    val configMap = mapOf(
        "host" to "localhost",
        "port" to 8080,
        "debug" to true
    )

    val config = Config(configMap)
    println("  Host:  ${config.host}")
    println("  Port:  ${config.port}")
    println("  Debug: ${config.debug}")

    println()

    // * CUSTOM DELEGATE

    println("--- Custom Delegate ---")

    val form = Form()

    form.username = "  Alice  "
    form.email = "  alice@email.com  "
    form.role = "admin"

    println("  Username: '${form.username}'")   // 'Alice'
    println("  Email:    '${form.email}'")       // 'alice@email.com'
    println("  Role:     '${form.role}'")        // 'ADMIN'

    println()

    // * REAL WORLD -> Cached property

    println("--- Cached Property ---")

    class DataService {
        val users: List<String> by lazy {
            println("  Loading users from database...")
            Thread.sleep(500)
            listOf("Alice", "Bob", "Carol")
        }

        val products: List<String> by lazy {
            println("  Loading products from database...")
            Thread.sleep(500)
            listOf("Laptop", "Phone", "Tablet")
        }
    }

    val service2 = DataService()
    println("  Service created, nothing loaded yet")
    println("  Users: ${service2.users}")      // ? loads here
    println("  Users: ${service2.users}")      // ? cached, no reload
    println("  Products: ${service2.products}") // ? loads here

}