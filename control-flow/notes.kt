/*
  ! Control Flow in Kotlin :

    - Control flow refers to the order in which statements are executed in a program.
    - Kotlin provides different control flow statements to make decisions, repeat tasks, and control program execution.

    --------------------------------------------------

    ! 1. Conditional Statements (Decision Making)

    ? a) if statement
        - Executes block if condition is true

        Example:
        val age = 18

        if (age >= 18) {
            println("Adult")
        }

    --------------------------------------------------

    ? b) if-else statement
        - Executes one block if condition is true, otherwise another

        Example:
        val age = 16

        if (age >= 18) {
            println("Adult")
        } else {
            println("Minor")
        }

    --------------------------------------------------

    ? c) if-else if ladder
        - Used for multiple conditions

        Example:
        val marks = 75

        if (marks >= 90) {
            println("A Grade")
        } else if (marks >= 75) {
            println("B Grade")
        } else {
            println("C Grade")
        }

    --------------------------------------------------

    ! 2. when Expression (Switch alternative)

    - Used as a replacement for switch-case

    Example:
    val day = 3

    when (day) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        else -> println("Invalid day")
    }

    --------------------------------------------------

    ! 3. Loops (Iteration)

    ? a) for loop
        - Used when number of iterations is known

        Example:
        for (i in 1..5) {
            println(i)
        }

    --------------------------------------------------

    ? b) while loop
        - Runs while condition is true

        Example:
        var i = 1

        while (i <= 5) {
            println(i)
            i++
        }

    --------------------------------------------------

    ? c) do-while loop
        - Executes at least once

        Example:
        var i = 1

        do {
            println(i)
            i++
        } while (i <= 5)

    --------------------------------------------------

    ! 4. Jump Statements

    ? a) break
        - Exits the loop immediately

        Example:
        for (i in 1..5) {
            if (i == 3) break
            println(i)
        }

    --------------------------------------------------

    ? b) continue
        - Skips current iteration

        Example:
        for (i in 1..5) {
            if (i == 3) continue
            println(i)
        }

    --------------------------------------------------

    ! Important Points:

    - Kotlin uses expressions like if and when (they can return values)
    - Loops are used for repetition
    - break stops loop, continue skips iteration
*/