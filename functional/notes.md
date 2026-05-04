# Functional Programming in Kotlin

## What is Functional Programming?
A programming style where:
- Functions are first-class citizens (can be stored, passed, returned)
- Data is immutable (prefer val over var)
- Functions have no side effects (pure functions)
- Functions can be composed together

## Core Concepts

| Concept          | Meaning                                      |
|------------------|----------------------------------------------|
| Pure Functions   | Same input always gives same output          |
| Immutability     | Data cannot be changed after creation        |
| Function Types   | Functions stored as variables                |
| Closures         | Functions that capture outer variables       |
| Currying         | f(a, b) becomes f(a)(b)                     |
| Composition      | Combining functions: f(g(x))                |

## Why Functional?
- Easier to test (pure functions)
- Easier to debug (no side effects)
- Thread safe (immutable data)
- More readable (composition)

## Kotlin is Multi-Paradigm
- Supports OOP AND Functional
- You can mix both styles
- Collections API is heavily functional (map, filter, reduce)