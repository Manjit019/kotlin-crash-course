/*
! SEALED CLASS IN KOTLIN

A sealed class is a restricted class hierarchy.

It allows you to define a fixed set of subclasses
under one parent class.

----------------------------------------
? WHY USE SEALED CLASSES?

- Represent restricted types (closed hierarchy)
- Perfect for state management (Success, Error, Loading)
- Works well with when expressions (no need for else branch)
- Improves type safety

----------------------------------------
* KEY FEATURES:

- All subclasses are known at compile time
- Cannot be instantiated directly
- Subclasses are usually defined in same file
- Great for handling UI states or API results

----------------------------------------
! COMMON USE CASES:

- Network Response handling
- UI State (Loading, Success, Error)
- Event handling systems
*/

// Code Example

sealed class Result{

    data class Success(val data: String): Result()
    data class Error(val error: String): Result()

    object Loading: Result()
}

fun handleResult(result: Result){
    when(result){
        is Result.Success -> println("Success: ${result.data}")
        is Result.Error -> println("Error: ${result.error}")
        Result.Loading -> println("Loading...")
    }
}

fun main(){
    val res : Result = Result.Success("Success Data")
    handleResult(res)
}


/*
   Example Explanation :

   - We have a sealed class named Result
   - It represents 3 possible states of an operation:
        1. Success
        2. Error
        3. Loading

   ----------------------------------------
   STEP 1: Sealed Class Structure

   Result can ONLY be one of these:

   ? Success → contains data (String)
   * Error   → contains error message (String)
   ! Loading → represents waiting state (no data)

   ----------------------------------------
   STEP 2: Object Creation

   In main():

   val res: Result = Result.Success("Success Data")

   ? Here we are creating a SUCCESS object
   * It stores "Success Data" inside it
   ! So current state = Success

   ----------------------------------------
   STEP 3: Function Call

   handleResult(res)

   ? The Result object is passed to function
   * Function checks which type it is using when

   ----------------------------------------
   STEP 4: when Expression Working

   when(result):

   * If Success:
        prints → "Success: Success Data"

   ! If Error:
        prints → "Error: <message>"

   ? If Loading:
        prints → "Loading..."

   ----------------------------------------
   FINAL OUTPUT:

   Success: Success Data

   ----------------------------------------
   KEY IDEA:

   ? Sealed class is used to handle fixed states safely
   * when expression automatically checks all cases
   ! No need for else because all possibilities are known
*/