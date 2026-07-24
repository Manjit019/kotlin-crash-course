# DSL in Kotlin

## What is a DSL?
DSL = Domain Specific Language
A mini-language designed for a specific task.
Kotlin's syntax makes it easy to build DSLs.

## Examples of DSLs You Already Use
- HTML builders
- Gradle build scripts (build.gradle.kts)
- Ktor routing
- Jetpack Compose UI
- Android ViewGroup layouts

## What Makes Kotlin Good for DSLs?
- Lambda with receiver (the core feature)
- Extension functions
- Infix functions
- Operator overloading
- Last lambda outside parentheses

## Key Concept: Lambda with Receiver
```kotlin
// Normal lambda
val greet: (String) -> String = { name -> "Hello $name" }

// Lambda with receiver
val greet: String.() -> String = { "Hello $this" }
```

## Pattern
```kotlin
fun buildSomething(init: SomeClass.() -> Unit): SomeClass {
    val obj = SomeClass()
    obj.init()
    return obj
}

// Usage looks like a language:
buildSomething {
    property = "value"
    doSomething()
}
```