/*
    ! Kotlin - Annotations =>

    * Annotations are a way to attach metadata to code elements, such as classes, functions, properties, etc.

    * Doesn't change behaviour directly
    * Used by tools, frameworks , compilers, and other libraries

    * Built-in annotations:
       ? @Deprecated     -> mark as deprecated
       ? @Suppress       -> suppress warnings
       ? @JvmStatic      -> Java interop
       ? @JvmOverloads   -> Java interop
       ? @Throws         -> Java interop
    
    * Custom annotations:
       ? annotation class MyAnnotation
       ? Can have parameters
       ? Can specify targets (class, function, property...)
*/

// * CUSTOM ANNOTATIONS

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Entity(val tableName: String)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Column(val name: String = "", val required: Boolean = true)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class PrimaryKey

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Validate

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MaxLength(val value: Int)

// * USING ANNOTATIONS

@Entity(tableName = "users")

data class User(
    @PrimaryKey
    @Column(name = "user_id")
    val id: Int,

    @Column(required = true)
    @MaxLength(50)
    val name: String,

    @Column(name = "email_address", required = true)
    @MaxLength(100)
    val email: String,

    @Column(required = false)
    val nickname: String? = null
)

// * BUILT-IN ANNOTATIONS

class OldApi {
    @Deprecated(
        message = "Use newMethod() instead",
        replaceWith = ReplaceWith("newMethod()"),
        level = DeprecationLevel.WARNING
    )
    fun oldMethod() {
        println("  Old method called")
    }

    fun newMethod() {
        println("  New method called")
    }

    @Suppress("UNUSED_VARIABLE")
    fun suppressExample() {
        val unused = 42   // ? no warning because of @Suppress
        println("  No warning for unused variable")
    }
}

// * READING ANNOTATIONS AT RUNTIME (reflection)

fun processEntity(klass: kotlin.reflect.KClass<*>) {
    val entity = klass.annotations.filterIsInstance<Entity>().firstOrNull()

    if (entity != null) {
        println("Table: ${entity.tableName}")

        klass.members
            .filter { member ->
                member.annotations.any { it is Column || it is PrimaryKey }
            }
            .forEach { member ->
                val column = member.annotations.filterIsInstance<Column>().firstOrNull()
                val isPK = member.annotations.any { it is PrimaryKey }
                val maxLen = member.annotations.filterIsInstance<MaxLength>().firstOrNull()

                val colName = if (column?.name?.isNotEmpty() == true) column.name else member.name
                val required = column?.required ?: false

                print("  Column: $colName")
                if (isPK) print(" [PK]")
                if (required) print(" [required]")
                if (maxLen != null) print(" [maxLength=${maxLen.value}]")
                println()
            }
    } else {
        println("Not an entity class")
    }
}

// * ANNOTATION FOR VALIDATION

fun validate(obj: Any): List<String> {
    val errors = mutableListOf<String>()

    obj::class.members.forEach { member ->
        val maxLen = member.annotations.filterIsInstance<MaxLength>().firstOrNull()
        val column = member.annotations.filterIsInstance<Column>().firstOrNull()

        if (maxLen != null) {
            try {
                val value = (member as kotlin.reflect.KProperty<*>).getter.call(obj)
                if (value is String && value.length > maxLen.value) {
                    errors.add("${member.name}: exceeds max length ${maxLen.value} (actual: ${value.length})")
                }
            } catch (e: Exception) { }
        }

        
        if (column?.required == true) {
            try {
                val value = (member as kotlin.reflect.KProperty<*>).getter.call(obj)
                if (value == null || (value is String && value.isBlank())) {
                    errors.add("${member.name}: required but empty")
                }
            } catch (e: Exception) { }
        }
    }

    return errors
}


fun main(args: Array<String>) {
    // * READING ANNOTATIONS

    println("--- Entity Info ---")
    processEntity(User::class)

    println()

    // * BUILT-IN ANNOTATIONS

    println("--- Built-in ---")
    val api = OldApi()
    api.oldMethod()   // ? shows deprecation warning in IDE
    api.newMethod()
    api.suppressExample()

    println()

    // * VALIDATION

    println("--- Validation ---")

    val validUser = User(1, "Alice", "alice@email.com")
    val errors1 = validate(validUser)
    println("Valid user errors: $errors1")

    val invalidUser = User(
        2,
        "A".repeat(60),   // ! exceeds MaxLength(50)
        "bob@email.com"
    )
    val errors2 = validate(invalidUser)
    println("Invalid user errors: $errors2")

    println()

    // * CREATING YOUR OWN ANNOTATION TARGETS

    println("--- Annotation Targets ---")
    println("  CLASS          -> classes, interfaces, objects")
    println("  PROPERTY       -> properties")
    println("  FUNCTION       -> functions")
    println("  VALUE_PARAMETER -> function parameters")
    println("  CONSTRUCTOR    -> constructors")
    println("  FIELD          -> backing fields")
    println("  LOCAL_VARIABLE -> local variables")
    println("  EXPRESSION     -> expressions")
    println("  FILE           -> entire file")
    println("  TYPE           -> type usage")
}