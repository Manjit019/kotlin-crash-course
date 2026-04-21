/*
  ! Conditional Statements in Kotlin:

  1. if statement - Executes block if condition is true
  2. if-else statement - Executes one block if condition is true, otherwise another
  3. if-else-if statement - Uses multiple conditions to execute different blocks

  - Kotlin provides different control flow statements to make decisions, repeat tasks, and control program execution.


*/

fun main() {
    var age = 18

    // if statement
    if (age >= 18) {
        println("Adult")
    } 

    // if-else statement
    age = 14
    if (age >= 18) {
        println("Adult")
    } else {
        println("Minor")
    }

    // if-else-if statement(nested if-else)
    age = 16
    if (age >= 18) {
        println("Adult")
    } else if (age >= 13) {
        println("Teenager")
    } else {
        println("Child")
    }
}