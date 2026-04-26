# Null Safety in Kotlin 🛡️

## What is Null?
Null means "no value" or "nothing here".
In Java, any variable can be null → causes NullPointerException (NPE) 💥
In Kotlin, null is handled at COMPILE TIME → no surprise crashes!

## The Core Idea
```
Non-Nullable  →  val name: String   = "John"  // Can NEVER be null
Nullable      →  val name: String?  = null     // CAN be null
```

## Operators

| Operator | Name             | Use                              |
|----------|------------------|----------------------------------|
| ?        | Nullable Type    | Declare a nullable variable      |
| ?.       | Safe Call        | Call only if not null            |
| ?:       | Elvis Operator   | Default value if null            |
| !!       | Not-Null Assert  | Force unwrap (dangerous!)        |
| as?      | Safe Cast        | Cast safely, returns null if fail|
| let      | Scope Function   | Execute block only if not null   |

## The Golden Rules
  1. Always prefer Non-Nullable types
  2. Use ?. instead of checking null manually
  3. Use ?: to provide default values
  4. AVOID !! as much as possible
  5. Use let for null-safe code blocks