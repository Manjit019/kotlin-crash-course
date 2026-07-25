/*
    ! Kotlin - Reflection =>

    * Reflection is a feature in Kotlin that allows you to inspect and modify the structure of a program at runtime.
    * Use "kotlin.reflect" library to use reflection

    ? What can you do?
    * Get class name, properties , functions
    * Access property values dynamically
    * Call functions by name
    * Check types at runtime

    ? Key classes:
    * KClass    -> class info
    * KProperty -> property info
    * KFunction -> function info

    ? Two ways to get KClass :
    * MyClass::class    -> at compile time
    * instance::class   -> at runtime

    ! Need kotlin-reflect dependency for full features
*/

import kotlin.reflect.full.*
import kotlin.reflect.KVisibility

data class User(
    val name: String,
    val age: Int,
    val email: String,
    private val password: String = "secret"
) {
    fun greet() = "Hello, I'm $name"
    fun isAdult() = age >= 18
    private fun getPassword() = password
}

data class Product(val id: Int, val title: String, val price: Double)

fun main(args: Array<String>) {
    // * GETTING CLASS INFO

    println("--- Class Info ---")

    val klass = User::class

    println("Name:        ${klass.simpleName}")
    println("Qualified:   ${klass.qualifiedName}")
    println("Is data:     ${klass.isData}")
    println("Is abstract: ${klass.isAbstract}")
    println("Is sealed:   ${klass.isSealed}")

    println()

    // * LISTING PROPERTIES

    println("--- Properties ---")

    klass.memberProperties.forEach { prop ->
        println("  ${prop.name}: ${prop.returnType} (${prop.visibility})")
    }

    println()

    // * LISTING FUNCTIONS

    println("--- Functions ---")

    klass.memberFunctions
        .filter { it.name != "equals" && it.name != "hashCode" && it.name != "toString" && it.name != "copy" }
        .forEach { func ->
            val params = func.parameters.drop(1)   // ? drop "this"
                .joinToString(", ") { "${it.name}: ${it.type}" }
            println("  ${func.name}($params): ${func.returnType}")
        }

    println()

    // * ACCESSING PROPERTY VALUES

    println("--- Property Values ---")

    val user = User("Alice", 25, "alice@email.com")

    klass.memberProperties
        .filter { it.visibility == KVisibility.PUBLIC }
        .forEach { prop ->
            println("  ${prop.name} = ${prop.get(user)}")
        }

    println()

    // * CALLING FUNCTIONS

    println("--- Calling Functions ---")

    val greetFunc = klass.memberFunctions.find { it.name == "greet" }
    val result = greetFunc?.call(user)
    println("  greet() = $result")

    val isAdultFunc = klass.memberFunctions.find { it.name == "isAdult" }
    val adult = isAdultFunc?.call(user)
    println("  isAdult() = $adult")

    println()

    // * CREATING INSTANCES

    println("--- Create Instance ---")

    val constructor = klass.primaryConstructor
    println("  Constructor params:")
    constructor?.parameters?.forEach { param ->
        println("    ${param.name}: ${param.type} (optional: ${param.isOptional})")
    }

    val newUser = constructor?.call("Bob", 30, "bob@email.com", "pass123")
    println("  Created: $newUser")

    println()

    // * GENERIC OBJECT INSPECTOR

    fun inspect(obj: Any) {
        val klass = obj::class
        println("--- Inspecting ${klass.simpleName} ---")
        println("  Type: ${klass.qualifiedName}")

        klass.memberProperties
            .filter { it.visibility == KVisibility.PUBLIC }
            .forEach { prop ->
                try {
                    val value = prop.getter.call(obj)
                    println("  ${prop.name} = $value (${prop.returnType})")
                } catch (e: Exception) {
                    println("  ${prop.name} = <inaccessible>")
                }
            }
        println()
    }

    inspect(User("Carol", 28, "carol@email.com"))
    inspect(Product(1, "Laptop", 999.99))
    inspect("Hello Kotlin")


    // * COMPARING OBJECTS BY REFLECTION

    println("--- Compare ---")

    fun <T : Any> diff(obj1: T, obj2: T): List<String> {
        val diffs = mutableListOf<String>()
        obj1::class.memberProperties
            .filter { it.visibility == KVisibility.PUBLIC }
            .forEach { prop ->
                val val1 = prop.getter.call(obj1)
                val val2 = prop.getter.call(obj2)
                if (val1 != val2) {
                    diffs.add("${prop.name}: $val1 -> $val2")
                }
            }
        return diffs
    }

    val user1 = User("Alice", 25, "alice@email.com")
    val user2 = User("Alice", 26, "alice@new.com")

    diff(user1, user2).forEach { println("  Changed: $it") }
}
