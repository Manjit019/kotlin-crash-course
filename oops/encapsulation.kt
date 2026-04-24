/*
    ! Encapsulation in Kotlin : ->

    - Encapsulation means "wrapping data and code together"
    - It restricts direct access to some parts of an object
    - It is used to protect data from unwanted modification
    - It is achieved using access modifiers (private, public, protected, internal)

    * Main Idea :

        Data hiding + Controlled access = Encapsulation

    --------------------------------------------
    * Visual Representation :

        Class: BankAccount
        -------------------------
        | private balance        |
        |                        |
        | public deposit()       |
        | public withdraw()      |
        -------------------------

        Outside world cannot directly access balance
        Only allowed methods can modify it

    --------------------------------------------
    * How Encapsulation works :

        1. Make variables PRIVATE
        2. Provide PUBLIC methods to access/update them

    --------------------------------------------
    * Access Modifiers :

        - private   → accessible only inside class
        - public    → accessible everywhere (default)
        - protected → accessible in subclass
        - internal  → accessible within same module

    --------------------------------------------
    * Benefits :

        - Data security
        - Control over data
        - Better maintainability
        - Flexibility to change internal code safely

    --------------------------------------------
    ! Important Points :

    - Encapsulation = data hiding + controlled access
    - Achieved using private variables and public methods
    - It protects object state from direct modification
*/

// Code Example

class BankAccount(private var balance:Double){

    fun deposit(amount:Double) {
        if(amount <= 0) return;
        balance += amount;
    }

     fun withdraw(amount:Double) {
        if (amount <= balance) {
            balance -= amount
        } else {
            println("Insufficient balance")
        }
    }


    fun getBalance():Double{
        return balance;
    }
}

fun main() {
    val acc = BankAccount(1000.0);
    println("Balance : ${acc.getBalance()}");
    println("Depositing 500.0");
    acc.deposit(500.0);
    println("Balance : ${acc.getBalance()}");
  
    println("Withdrawing 200.0");
    acc.withdraw(200.0);
    println("Balance : ${acc.getBalance()}");
}