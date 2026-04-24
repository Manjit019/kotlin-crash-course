/*
! OOPS in Kotlin : ->

OOPS (Object-Oriented Programming System) is a programming paradigm
based on objects and classes. It helps organize code in a reusable,
maintainable, and scalable way.

Kotlin supports OOPS with a concise and modern syntax.

----------------------------------------
* FOUR PILLARS OF OOPS:

 ? 1. ENCAPSULATION
   - Wrapping data (variables) + methods (functions) together
   - Restricting direct access using access modifiers
   - Achieved using:
        private, public, protected, internal

   Goal: Data hiding & controlled access

----------------------------------------

 ? 2. INHERITANCE
   - One class acquires properties/behavior of another
   - Represents "is-a" relationship

   Kotlin specifics:
   - Classes are 'final' by default
   - Use 'open' to allow inheritance
   - Use ':' for extending classes

   Goal: Code reusability

----------------------------------------

 ? 3. POLYMORPHISM
   - "Many forms" → same function behaves differently

   Types:
   - Compile-time → Function Overloading
   - Runtime → Method Overriding (common in Kotlin)

   Goal: Flexibility & dynamic behavior

----------------------------------------

 ? 4. ABSTRACTION
   - Hiding implementation details
   - Showing only essential features

   Achieved using:
   - abstract classes
   - interfaces

   Goal: Reduce complexity

----------------------------------------
! CORE BUILDING BLOCKS:

 * CLASS
   - Blueprint/template for objects

 * OBJECT
   - Instance of a class

 * CONSTRUCTOR
   - Initializes object (primary + secondary)

 * INIT BLOCK
   - Runs during object creation

----------------------------------------
! KOTLIN-SPECIFIC FEATURES:

 * Data Classes
   - Auto-generates:
     toString(), equals(), hashCode(), copy()

 * Object Keyword
   - Used for singleton pattern

 * Sealed Classes
   - Restrict class hierarchy
   - Useful for state handling

 * Interfaces
   - Can contain default method implementations

----------------------------------------
 ? WHY USE OOPS?

- Code reusability
- Better structure and readability
- Easier debugging
- Scalable applications
- Real-world modeling

----------------------------------------
* REAL-WORLD ANALOGY:

Class → Car
Objects → BMW, Audi

Properties → color, speed
Functions → drive(), brake()

----------------------------------------
* TIPS:

- Prefer composition over inheritance
- Use data classes for models
- Use sealed classes for predictable states
- Keep classes small and focused (SRP)

*/