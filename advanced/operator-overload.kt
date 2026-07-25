/*
    ! Kotlin - Operator Overloading =>

    * Operator Overloading is a feature in Kotlin that allows you to define how operators work with custom classes.
    * Use "operator" keyword before fun to define operator overloading

    * Common operators:
       ? plus      -> a + b
       ? minus     -> a - b
       ? times     -> a * b
       ? div       -> a / b
       ? get       -> a[i]
       ? set       -> a[i] = v
       ? contains  -> x in a
       ? invoke    -> a()
       ? compareTo -> a > b, a < b
       ? equals    -> a == b
       ? rangeTo   -> a..b
       ? unaryMinus -> -a
       ? inc/dec   -> a++, a--

*/

// * VECTOR2D

data class Vector2D(val x: Double, val y: Double) {

    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)
    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector2D(x * scalar, y * scalar)
    operator fun div(scalar: Double) = Vector2D(x / scalar, y / scalar)
    operator fun unaryMinus() = Vector2D(-x, -y)

    fun length() = Math.sqrt(x * x + y * y)

    override fun toString() = "($x, $y)"
}

// * MONEY

data class Money(val amount: Double, val currency: String) {

    operator fun plus(other: Money): Money {
        require(currency == other.currency) { "Cannot add $currency and ${other.currency}" }
        return Money(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        require(currency == other.currency) { "Cannot subtract $currency and ${other.currency}" }
        return Money(amount - other.amount, currency)
    }

    operator fun times(factor: Double) = Money(amount * factor, currency)

    operator fun compareTo(other: Money): Int {
        require(currency == other.currency) { "Cannot compare $currency and ${other.currency}" }
        return amount.compareTo(other.amount)
    }

    override fun toString() = "${"%.2f".format(amount)} $currency"
}

// * MATRIX

class Matrix(private val rows: Int, private val cols: Int) {
    private val data = Array(rows) { DoubleArray(cols) }

    operator fun get(row: Int, col: Int): Double = data[row][col]
    operator fun set(row: Int, col: Int, value: Double) { data[row][col] = value }

    operator fun plus(other: Matrix): Matrix {
        require(rows == other.rows && cols == other.cols)
        val result = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result[i, j] = this[i, j] + other[i, j]
            }
        }
        return result
    }

    override fun toString(): String {
        return data.joinToString("\n") { row ->
            row.joinToString(", ") { "%.1f".format(it) }
        }
    }
}

// * CUSTOM COLLECTION WITH OPERATORS

class ShoppingCart {
    private val items = mutableMapOf<String, Double>()

    // ? cart += "item" to price
    operator fun plusAssign(pair: Pair<String, Double>) {
        items[pair.first] = pair.second
    }

    // ? cart -= "item"
    operator fun minusAssign(item: String) {
        items.remove(item)
    }

    // ? "item" in cart
    operator fun contains(item: String) = item in items

    // ? cart["item"]
    operator fun get(item: String) = items[item]

    // ? cart()
    operator fun invoke(): Double = items.values.sum()

    fun print() {
        items.forEach { (item, price) ->
            println("  $item: $${"%.2f".format(price)}")
        }
        println("  Total: $${"%.2f".format(this())}")
    }
}

// * INVOKE OPERATOR -> make object callable

class Validator(private val pattern: Regex) {
    operator fun invoke(input: String): Boolean {
        return pattern.matches(input)
    }
}

fun main(args: Array<String>) {
    // * VECTOR2D

    println("--- Vector2D ---")

    val v1 = Vector2D(3.0, 4.0)
    val v2 = Vector2D(1.0, 2.0)

    println("v1:      $v1")
    println("v2:      $v2")
    println("v1 + v2: ${v1 + v2}")
    println("v1 - v2: ${v1 - v2}")
    println("v1 * 2:  ${v1 * 2.0}")
    println("v1 / 2:  ${v1 / 2.0}")
    println("-v1:     ${-v1}")
    println("length:  ${v1.length()}")

    println()

    
    // * MONEY

    println("--- Money ---")

    val price1 = Money(29.99, "USD")
    val price2 = Money(15.50, "USD")

    println("Price1:    $price1")
    println("Price2:    $price2")
    println("Total:     ${price1 + price2}")
    println("Diff:      ${price1 - price2}")
    println("Tax (10%): ${price1 * 0.10}")
    println("p1 > p2:   ${price1 > price2}")

    try {
        Money(10.0, "USD") + Money(5.0, "EUR")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }

    println()

    // * MATRIX

    println("--- Matrix ---")

    val m1 = Matrix(2, 2)
    m1[0, 0] = 1.0; m1[0, 1] = 2.0
    m1[1, 0] = 3.0; m1[1, 1] = 4.0

    val m2 = Matrix(2, 2)
    m2[0, 0] = 5.0; m2[0, 1] = 6.0
    m2[1, 0] = 7.0; m2[1, 1] = 8.0

    println("M1:")
    println(m1)
    println("M2:")
    println(m2)
    println("M1 + M2:")
    println(m1 + m2)

    println()

    // * SHOPPING CART

    println("--- Shopping Cart ---")

    val cart = ShoppingCart()

    cart += "Laptop" to 999.99
    cart += "Mouse" to 29.99
    cart += "Keyboard" to 79.99

    println("Has Laptop: ${"Laptop" in cart}")
    println("Mouse price: ${cart["Mouse"]}")

    cart.print()

    cart -= "Mouse"
    println("\nAfter removing Mouse:")
    cart.print()

    println()

    // * INVOKE OPERATOR

    println("--- Invoke ---")

    val isEmail = Validator(Regex("^[\\w.-]+@[\\w.-]+\\.\\w+$"))
    val isPhone = Validator(Regex("^\\d{3}-\\d{3}-\\d{4}$"))

    println("alice@email.com: ${isEmail("alice@email.com")}")
    println("not-an-email:    ${isEmail("not-an-email")}")
    println("555-123-4567:    ${isPhone("555-123-4567")}")
    println("12345:           ${isPhone("12345")}")

}