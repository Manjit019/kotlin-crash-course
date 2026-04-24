/*
    ! Note :

    - In Kotlin, the "main" function is the entry point of the program.

    - You can define the main function in two ways:

        1. With arguments (command-line arguments):
            fun main(args: Array<String>) {
                // code
            }

        2. Without arguments (simpler form):
            fun main() {
                // code
            }

    --------------------------------------------------

    - You can also call (use) your own functions inside the main function.

    ? Example:

        fun greet() {
            println("Hello Kotlin")
        }

        fun main() {
            greet()
        }

    --------------------------------------------------

    ! Important Points:

    - args: Array<String> is optional in Kotlin
    - main() without parameters is commonly used for beginners
    - All program execution starts from main()
*/


// simple function
fun sayHello(){
    println("Hello, Kotlin!");
}

fun wifeNameFinder() {

    print("Enter your name: ")
    val name = readln()

    print("Enter your birth month (number): ")
    val month = readln().toInt()

    print("Enter your favorite number: ")
    val favNum = readln().toInt()

    val names = listOf("Ananya", "Priya", "Sneha", "Kavya", "Riya", "Muskan","Pooja")

    val index = (name.length + month + favNum) % names.size

    println("🔮 Calculating your future wife's name...")
    println("💖 Hmm... it's ${names[index]}!")
    println("😂 Don't take it seriously... or maybe you should 😏")
}

//parameterized function
fun greet(name: String){
    println("Hello, $name!");
}

// parameterized function with default value
fun greetWithDefault(name: String = "Guest"){
    println("Hello, $name!");
}

// named arguments
fun showDetails(name: String, age: Int){
    println("$name is $age years old");
}

// function with return type
fun sum(a: Int, b: Int): Int{
    return a + b;
}

// 

// single expression function
fun squareRoot(x : Double) = Math.sqrt(x);

// anonymous function
val multiply = fun(a: Int, b: Int): Int {
    return a * b;
}


fun main(args: Array<String>) {
    // // Calling a function
    // sayHello();

    // // Calling a parameterized function
    // greet("Muskan");

    // // Calling a parameterized function with default value
    // greetWithDefault("Gaurav");
    // greetWithDefault();

    // // Calling a function with return type
    // val result = sum(10, 20);
    // println("Sum: $result");

    // // Calling a function with named arguments
    // showDetails(name = "Muskan", age = 20);

    // // Calling a single expression function
    // val square = squareRoot(9.0);
    // println("Square Root: $square");

    // // Calling an anonymous function
    // val product = multiply(2, 3);
    // println("Product: $product");


    fun sumAll(vararg numbers: Int): Int {
        var result = 0
        for (number in numbers) {
            result += number
        }
        return result
    }

    // val sumResult = sumAll(1, 2, 3, 4, 5)
    // println("Sum: $sumResult")

    wifeNameFinder();
}