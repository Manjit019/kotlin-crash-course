/*
    ! Kotlin - Reified Type Parameters 

    * Problem : 
    ? Gereric type are Erased at runtime (JVM Limitation)
    ? You can't do : T::class inside generic function

    * Solution : 
    ? "reified" keyword preserves type info at runtime
    ? Only works with inline functions

    * What you can do with reified : 
    ? T::class  -> get class of T
    ? is T  -> check if something is T
    ? as T  -> cast to T
    ? T::class.simpleName   -> get type name

*/

// * WITHOUT REIFIED -> need to pass class manually

fun <T> filterByTypeManual(list: List<Any>, clazz: Class<T>): List<T> {
    return list.filter { clazz.isInstance(it) }.map { clazz.cast(it) }
}

// * WITH REIFIED -> clean and simple

inline fun <reified T> filterByType(list: List<Any>): List<T> {
    return list.filterIsInstance<T>()
}

// ? Same thing manually
inline fun <reified T> filterByType2(list: List<Any>): List<T> {
    return list.filter { it is T }.map { it as T }
}

// * TYPE CHECKING

inline fun <reified T> isType(value: Any): Boolean {
    return value is T
}

inline fun <reified T> typeName(): String {
    return T::class.simpleName ?: "Unknown"
}


// * SAFE CAST

inline fun <reified T> safeCast(value: Any): T? {
    return value as? T
}


// * REAL WORLD -> JSON-like parser

class JsonObject(
    @PublishedApi
    internal val data: Map<String, Any?>
) {

    inline fun <reified T> get(key: String): T? {
        val value = data[key] ?: return null
        return value as? T
    }

    inline fun <reified T> getOrDefault(key: String, default: T): T {
        return get<T>(key) ?: default
    }

    override fun toString() = data.toString()
}

// * REAL WORLD -> Service locator

class ServiceLocator {
    @PublishedApi
    internal val services = mutableMapOf<String, Any>()

    inline fun <reified T : Any> register(service: T) {
        services[T::class.simpleName!!] = service
    }

    inline fun <reified T : Any> get(): T? {
        return services[T::class.simpleName!!] as? T
    }
}

// ? Services
class DatabaseService {
    fun query(sql: String) = "Results for: $sql"
}

class EmailService {
    fun send(to: String, msg: String) = "Email sent to $to: $msg"
}

class CacheService {
    private val cache = mutableMapOf<String, String>()
    fun put(key: String, value: String) { cache[key] = value }
    fun get(key: String) = cache[key]
}

// * REAL WORLD -> Event system

class EventBus {
    @PublishedApi
    internal val listeners = mutableMapOf<String, MutableList<(Any) -> Unit>>()
    inline fun <reified T : Any> on(noinline handler: (T) -> Unit) {
        val key = T::class.simpleName!!
        listeners.getOrPut(key) { mutableListOf() }
            .add { handler(it as T) }
    }

    inline fun <reified T : Any> emit(event: T) {
        val key = T::class.simpleName!!
        listeners[key]?.forEach { it(event) }
    }
}

data class UserCreated(val name: String)
data class OrderPlaced(val orderId: Int, val total: Double)
data class ErrorOccurred(val message: String)

fun main(args: Array<String>) {
    // * FILTER BY TYPE

    println("--- Filter by Type ---")

    val mixed: List<Any> = listOf(1, "hello", 2.5, "world", 3, true, "kotlin", 4.0)

    val strings = filterByType<String>(mixed)
    val ints = filterByType<Int>(mixed)
    val doubles = filterByType<Double>(mixed)

    println("Strings: $strings")
    println("Ints:    $ints")
    println("Doubles: $doubles")

    // ! Without reified you would need:
    val stringsOld = filterByTypeManual(mixed, String::class.java)
    println("Old way: $stringsOld")

    println()

    // * TYPE CHECKING

    println("--- Type Checking ---")

    println("42 is Int:    ${isType<Int>(42)}")
    println("42 is String: ${isType<String>(42)}")
    println("hi is String: ${isType<String>("hi")}")

    println()

    println("Type of Int:    ${typeName<Int>()}")
    println("Type of String: ${typeName<String>()}")
    println("Type of List:   ${typeName<List<*>>()}")

    println()

    // * SAFE CAST

    println("--- Safe Cast ---")

    val value: Any = "Hello Kotlin"

    val asString: String? = safeCast<String>(value)
    val asInt: Int? = safeCast<Int>(value)

    println("As String: $asString")
    println("As Int:    $asInt")

    println()

    // * JSON-LIKE OBJECT

    println("--- JsonObject ---")

    val json = JsonObject(mapOf(
        "name" to "Alice",
        "age" to 25,
        "score" to 98.5,
        "active" to true,
        "address" to null
    ))

    val name: String? = json.get<String>("name")
    val age: Int? = json.get<Int>("age")
    val score: Double? = json.get<Double>("score")
    val missing: String = json.getOrDefault("city", "Unknown")

    println("Name:    $name")
    println("Age:     $age")
    println("Score:   $score")
    println("Missing: $missing")

    println()


    // * SERVICE LOCATOR

    println("--- Service Locator ---")

    val locator = ServiceLocator()

    locator.register(DatabaseService())
    locator.register(EmailService())
    locator.register(CacheService())

    val db = locator.get<DatabaseService>()
    val email = locator.get<EmailService>()
    val cache = locator.get<CacheService>()

    println(db?.query("SELECT * FROM users"))
    println(email?.send("alice@email.com", "Hello!"))

    cache?.put("key1", "value1")
    println("Cache: ${cache?.get("key1")}")

    println()

    // * EVENT BUS

    println("--- Event Bus ---")

    val bus = EventBus()

    // ? Register handlers by type
    bus.on<UserCreated> { event ->
        println("  [User Handler] User created: ${event.name}")
    }

    bus.on<OrderPlaced> { event ->
        println("  [Order Handler] Order #${event.orderId}: $${event.total}")
    }

    bus.on<ErrorOccurred> { event ->
        println("  [Error Handler] Error: ${event.message}")
    }

    // ? Emit events -> correct handler is called
    bus.emit(UserCreated("Alice"))
    bus.emit(OrderPlaced(101, 299.99))
    bus.emit(ErrorOccurred("Connection timeout"))
    bus.emit(UserCreated("Bob"))

    println()

    // * SUMMARY

    println("--- Summary ---")
    println("  reified -> preserves generic type at runtime")
    println("  Only works with inline functions")
    println("  Enables: is T, as T, T::class")
    println("  Use for: type filtering, casting, service lookup")

}