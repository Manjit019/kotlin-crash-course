# Exception Handling in Kotlin

## What is an Exception?
An unexpected error that occurs during program execution.
Without handling, the program crashes.

## Kotlin vs Java Exceptions

| Feature             | Java                    | Kotlin                  |
|---------------------|-------------------------|-------------------------|
| Checked exceptions  | Yes (forced to handle)  | No (all unchecked)      |
| try-catch           | Statement only          | Can be an expression    |
| throws keyword      | Required                | Not required            |
| finally             | Same                    | Same                    |

## Common Built-in Exceptions
- NullPointerException     -> accessing null
- IllegalArgumentException -> bad function argument
- IllegalStateException    -> object in wrong state
- IndexOutOfBoundsException -> bad index
- NumberFormatException    -> bad string to number
- ArithmeticException      -> math error (divide by 0)
- ClassCastException       -> wrong type cast
- UnsupportedOperationException -> not implemented

## Kotlin Helper Functions
- require(condition) { message }  -> throws IllegalArgumentException
- check(condition) { message }    -> throws IllegalStateException
- error(message)                  -> throws IllegalStateException