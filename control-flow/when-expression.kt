/*
   ! When Expression (Switch Statement Alternative) in Kotlin :
   1. When Expression is a control flow statement that allows you to perform different actions based on different conditions.
   2. When Expression is a more concise and readable way to write a series of if-else statements.
   3. When Expression is a good choice when you have a large number of conditions to check.


    syntax:
        when (expression) {
            condition1 -> {
                // code to be executed if condition1 is true
            }
            condition2 -> {
                // code to be executed if condition2 is true
            }
            else -> {
                // code to be executed if none of the conditions are true
            }
        }


 
*/


fun main() {
    val number = 5

    when (number) {
        1 -> println("One")
        2 -> println("Two")
        3 -> println("Three")
        else -> println("Invalid number")
    }


    // weekday example
    val day = 4
    when (day) {
        1 -> println("Sunday")
        2 -> println("Monday")
        3 -> println("Tuesday")
        4 -> println("Wednesday")
        5 -> println("Thursday")
        6 -> println("Friday")
        7 -> println("Saturday")
        else -> println("Invalid day")
    }
}