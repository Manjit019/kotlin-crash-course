/*
    ! Jump Statements in Kotlin :

    - Jump statements are used to control the flow of loops
      by changing normal execution order.

    --------------------------------------------------

    ! 1. break statement :

    - Used to terminate the loop immediately
    - Control exits the loop completely

    ? Example:
        for (i in 1..5) {
            if (i == 3) break
            println(i)
        }

    --------------------------------------------------

    ! 2. continue statement :

    - Skips the current iteration of the loop
    - Moves control to the next iteration

    ? Example:
        for (i in 1..5) {
            if (i == 3) continue
            println(i)
        }

    --------------------------------------------------

    * Important Points:

    - break = exits loop completely
    - continue = skips current iteration only
*/

// Code Examples : 


fun main(){

    // break statement
    // println("Break statement:")
    // for (i in 1..5) {
    //     if (i == 3) break
    //     println(i)
    // }

    // continue statement
    // println("Continue statement:")
    // for (i in 1..5) {
    //     if (i == 3) continue
    //     println(i)
    // }


    // Return statement -> exits the function completely 
    // println("Return statement:")
    // for (i in 1..5) {
    //     if (i == 3) return
    //     println(i)
    // }


    val answers = listOf("right", "wrong", "right", "stop", "right")
    println("Combined Example : ")
    for (answer in answers) {

        if (answer == "wrong") {
            println("Wrong answer → skip")
            continue
        }

        if (answer == "stop") {
            println("Checking stopped")
            break
        }

        println("Correct answer checked")
    }
}