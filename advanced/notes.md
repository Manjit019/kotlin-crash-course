# Advanced Kotlin

## Topics

| Topic               | What it does                                    |
|---------------------|-------------------------------------------------|
| Delegation          | Reuse implementation via "by" keyword           |
| Operator Overloading| Define +, -, *, [] etc for your classes          |
| Reflection          | Inspect classes/functions at runtime            |
| Annotations         | Add metadata to code                            |
| Inline Functions    | Inline lambda code at call site (performance)   |
| Reified Types       | Access generic type info at runtime             |

## When to Use

- Delegation      -> avoid inheritance, compose behavior
- Operators       -> make classes feel natural (math, collections)
- Reflection      -> frameworks, serialization, testing
- Annotations     -> marking code for tools/frameworks
- Inline          -> high-order functions with zero overhead
- Reified         -> when you need T::class inside generic function

## Dependencies for Reflection
```
implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
```