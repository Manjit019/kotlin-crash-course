# Generics in Kotlin

## What are Generics?
Generics allow you to write code that works with ANY type.
Instead of writing separate functions for Int, String, Double...
you write ONE function that works with all of them.

## Why use Generics?
- Code reusability
- Type safety at compile time
- No need to cast types manually

## Syntax
```
fun <T> functionName(param: T): T       // Generic function
class Box<T>(val value: T)              // Generic class
fun <T : Number> sum(a: T, b: T)       // Constrained generic
```

## Variance (in / out)

| Keyword | Name            | Meaning                        |
|---------|-----------------|--------------------------------|
| out     | Covariance      | Can only PRODUCE T (read only) |
| in      | Contravariance  | Can only CONSUME T (write only)|
| neither | Invariant       | Can read and write             |

## Quick Rule
- out = Producer  = can return T    = List<out T>
- in  = Consumer  = can accept T    = Comparable<in T>