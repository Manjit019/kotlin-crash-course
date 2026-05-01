/*
    ! Kotin - Custom Exceptions

    * Create you own exception classes 
    * Extend Exception or RuntimeException

    ? When to use custom exceptions?
    -> When built-in exceptions are not specific enough for your use case
    -> When you need extra data with the error
    -> When different errors need diffent handling

    ? Convention : ->
    * Name ends with "Exception"
    * Pass message to parent constructor
*/

// ! Basic Custom Exception

class InvalidAgeException(message : String) : Exception(message)

class InsufficientFundsException(
    val balance : Double,
    val amount : Double
): Exception("Insufficient funds : balance=$balance, requested=$amount")


//? Sealed class for related exceptions

sealed class AuthException(message : String) : Exception(message)

class InvalidCredentialsException(val username : String):AuthException("Invalid credentials for user: $username")


class AccountLockedException(
    val username: String,
    val lockDuration: Int
) : AuthException("Account $username locked for $lockDuration minutes")

class TokenExpiredException(
    val token: String
) : AuthException("Token expired: ${token.take(10)}...")


// * REAL WORLD -> Validation exceptions
class ValidationException(
    val field: String,
    val value: Any?,
    message: String
) : Exception("Validation failed for '$field': $message")

// * BANK ACCOUNT EXAMPLE

class BankAccount(val owner: String, private var balance: Double) {

    fun deposit(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Deposit must be positive: $amount")
        }
        balance += amount
        println("  Deposited $$amount -> Balance: $$balance")
    }

    fun withdraw(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Withdrawal must be positive: $amount")
        }
        if (amount > balance) {
            throw InsufficientFundsException(balance, amount)
        }
        balance -= amount
        println("  Withdrew $$amount -> Balance: $$balance")
    }

    fun getBalance() = balance
}

// * AUTH SERVICE EXAMPLE
class AuthService {
    private val users = mapOf(
        "alice" to "pass123",
        "bob" to "secret456"
    )
    private val lockedUsers = setOf("carol")
    private val validTokens = setOf("token_abc_123")

    fun login(username: String, password: String): String {
        if (username in lockedUsers) {
            throw AccountLockedException(username, 30)
        }
        val correctPassword = users[username]
            ?: throw InvalidCredentialsException(username)
        if (password != correctPassword) {
            throw InvalidCredentialsException(username)
        }
        return "token_${username}_${System.currentTimeMillis()}"
    }

    fun validateToken(token: String) {
        if (token !in validTokens) {
            throw TokenExpiredException(token)
        }
        println("  Token valid")
    }
}


// * FORM VALIDATOR
class FormValidator {
    fun validateName(name: String) {
        if (name.isBlank()) {
            throw ValidationException("name", name, "cannot be blank")
        }
        if (name.length < 2) {
            throw ValidationException("name", name, "too short (min 2)")
        }
    }

    fun validateEmail(email: String) {
        if (!email.contains("@")) {
            throw ValidationException("email", email, "must contain @")
        }
    }

    fun validateAge(age: Int) {
        if (age < 0 || age > 150) {
            throw ValidationException("age", age, "must be 0-150")
        }
    }
}


fun main() {

    // ! BASIC CUSTOM EXCEPTION

    println("--- Custom Exception ---")
    try {
        val age = -5
        if (age < 0) throw InvalidAgeException("Age cannot be negative: $age")
    } catch (e: InvalidAgeException) {
        println("Caught: ${e.message}")
    }

    println()

    // ! BANK ACCOUNT

    println("--- Bank Account ---")
    val account = BankAccount("Alice", 100.0)

    try {
        account.deposit(50.0)
        account.withdraw(30.0)
        account.withdraw(200.0)
    } catch (e: InsufficientFundsException) {
        println("  Error: ${e.message}")
        println("  Balance: ${e.balance}, Requested: ${e.amount}")
    } catch (e: IllegalArgumentException) {
        println("  Error: ${e.message}")
    }

    println()

    // ! AUTH SERVICE

    println("--- Auth Service ---")
    val auth = AuthService()

    // ? Successful login
    try {
        val token = auth.login("alice", "pass123")
        println("  Login OK, token: $token")
    } catch (e: AuthException) {
        println("  Auth error: ${e.message}")
    }

    // ? Wrong password
    try {
        auth.login("alice", "wrong")
    } catch (e: InvalidCredentialsException) {
        println("  Bad credentials for: ${e.username}")
    }

    // ? Locked account
    try {
        auth.login("carol", "pass")
    } catch (e: AccountLockedException) {
        println("  Locked: ${e.username} for ${e.lockDuration} min")
    }

    // ? Expired token
    try {
        auth.validateToken("expired_token_xyz")
    } catch (e: TokenExpiredException) {
        println("  Expired: ${e.message}")
    }

    println()

    // ! HANDLING SEALED EXCEPTION HIERARCHY

    println("--- Sealed Exception ---")
    fun handleAuth(username: String, password: String) {
        try {
            val token = auth.login(username, password)
            println("  Welcome! Token: $token")
        } catch (e: AuthException) {
            // ? when on sealed class -> exhaustive
            when (e) {
                is InvalidCredentialsException -> println("  Check your password, ${e.username}")
                is AccountLockedException -> println("  Try again in ${e.lockDuration} min")
                is TokenExpiredException -> println("  Please login again")
            }
        }
    }

    handleAuth("alice", "pass123")
    handleAuth("alice", "wrong")
    handleAuth("carol", "pass")

    println()

    // ! FORM VALIDATOR

    println("--- Form Validator ---")
    val validator = FormValidator()

    fun validateForm(name: String, email: String, age: Int) {
        try {
            validator.validateName(name)
            validator.validateEmail(email)
            validator.validateAge(age)
            println("  Form valid: $name, $email, $age")
        } catch (e: ValidationException) {
            println("  Invalid '${e.field}' = '${e.value}': ${e.message}")
        }
    }

    validateForm("Alice", "alice@email.com", 25)
    validateForm("", "alice@email.com", 25)
    validateForm("Alice", "invalid-email", 25)
    validateForm("Alice", "alice@email.com", -5)
}