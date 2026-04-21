/*
    ! Operators in Kotlin :
    1. Arithmetic Operators: +, -, *, /, %
    2. Comparison Operators: ==, !=, <, >, <=, >=
    3. Logical Operators: &&, ||, !
    4. Assignment Operators: =, +=, -=, *=, /=, %=
    5. Bitwise Operators: &, |, ^, ~, <<, >>

    - In Kotlin, operators are used to perform operations on variables and values, such as adding, subtracting, multiplying, dividing, and comparing values.
    - Kotlin has a rich set of operators, which can be used to perform various operations on variables and values.
    - Operators are used to perform operations on variables and values, such as adding, subtracting, multiplying, dividing, and comparing values.

*/

// Code Example:

fun main(){
    
    //Arithmetic Operators
    // val a = 10
    // val b = 5
    // val c = a + b
    // val d = a - b
    // val e = a * b
    // val f = a / b
    // val g = a % b

    // println("Sum: $c")
    // println("Difference: $d")
    // println("Product: $e")
    // println("Quotient: $f")
    // println("Remainder: $g")

    //Comparison Operators
    // val x = 10
    // val y = 5
    // val z = x == y
    // val k = x != y
    // val l = x < y
    // val m = x > y
    // val n = x <= y
    // val o = x >= y

    // println("x == y: $z")
    // println("x != y: $k")
    // println("x < y: $l")
    // println("x > y: $m")
    // println("x <= y: $n")
    // println("x >= y: $o")

    //Logical Operators
    // val p = true
    // val q = false
    // val r = p && q
    // val s = p || q
    // val t = !p

    // println("Logical AND: $r")
    // println("Logical OR: $s")
    // println("Logical NOT: $t")

    //Assignment Operators
    // var u = 10
    // u += 5
    // u -= 3
    // u *= 2
    // u /= 4
    // u %= 3

    // println("Assignment Operators: $u")

    //Bitwise Operators
    val v = 10;
    val w = 5;
    val x = v and w;
    val y = v or w;
    val z = v xor w;
    val a1 = v.inv();
    val b1 = v shl 2;
    val c1 = v shr 1;

    println("Bitwise AND: $x")
    println("Bitwise OR: $y")
    println("Bitwise XOR: $z")
    println("Bitwise NOT: $a1")
    println("Bitwise Left Shift: $b1")
    println("Bitwise Right Shift: $c1")


}