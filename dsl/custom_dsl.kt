/*
    ! Custom DSl

    * Putting it all together
    * Building real-world DSls from scratch

    
*/

// * DSL 1: TEST FRAMEWORK

class TestSuite(val name:String){
    private val tests = mutableListOf<TestCase>()
    private var beforeEach: (() -> Unit)? = null

    fun beforeEach(action: () -> Unit) {
        beforeEach = action
    }

    fun test(name: String, action: () -> Unit) {
        tests.add(TestCase(name, action))
    }

    fun run() {
        println("Suite: $name")
        var passed = 0
        var failed = 0

        tests.forEach { testCase ->
            beforeEach?.invoke()
            try {
                testCase.action()
                println("  [PASS] ${testCase.name}")
                passed++
            } catch (e: AssertionError) {
                println("  [FAIL] ${testCase.name}: ${e.message}")
                failed++
            }
        }

        println("Results: $passed passed, $failed failed")
        println()
    }
}

data class TestCase(val name: String, val action: () -> Unit)

fun describe(name: String, init: TestSuite.() -> Unit) {
    val suite = TestSuite(name)
    suite.init()
    suite.run()
}

// ? Helper for assertions
infix fun <T> T.shouldBe(expected: T) {
    if (this != expected) {
        throw AssertionError("Expected $expected but got $this")
    }
}

infix fun <T> T.shouldNotBe(expected: T) {
    if (this == expected) {
        throw AssertionError("Expected NOT $expected but got $this")
    }
}

// * DSL 2: SCHEDULE BUILDER

class Schedule {
    private val events = mutableListOf<ScheduleEvent>()

    fun at(time: String, init: ScheduleEvent.() -> Unit) {
        val event = ScheduleEvent(time)
        event.init()
        events.add(event)
    }
    fun print() {
        println("--- Schedule ---")
        events.sortedBy { it.time }.forEach { event ->
            println("  ${event.time} | ${event.title}")
            if (event.description.isNotEmpty()) {
                println("           ${event.description}")
            }
            if (event.attendees.isNotEmpty()) {
                println("           with: ${event.attendees.joinToString(", ")}")
            }
        }
        println()
    }
}

class ScheduleEvent(val time: String) {
    var title = ""
    var description = ""
    var attendees = mutableListOf<String>()

    fun with(vararg people: String) {
        attendees.addAll(people)
    }
}

fun schedule(init: Schedule.() -> Unit): Schedule {
    val s = Schedule()
    s.init()
    return s
}

// * DSL 3: MENU BUILDER

class Menu(val name : String) {
    val items = mutableListOf<MenuItem>()
    val subMenus = mutableListOf<Menu>()

    fun item(name: String, price: Double, init: MenuItem.() -> Unit = {}) {
        val menuItem = MenuItem(name, price)
        menuItem.init()
        items.add(menuItem)
    }

    fun submenu(name: String, init: Menu.() -> Unit) {
        val sub = Menu(name)
        sub.init()
        subMenus.add(sub)
    }
    fun print(indent: Int = 0) {
        val pad = "  ".repeat(indent)
        println("$pad[$name]")
        items.forEach { item ->
            val tags = if (item.tags.isEmpty()) "" else " (${item.tags.joinToString(", ")})"
            println("$pad  ${item.name} - $${item.price}$tags")
        }
        subMenus.forEach { it.print(indent + 1) }
    }
}

class MenuItem(val name: String, val price: Double) {
    val tags = mutableListOf<String>()

    fun tag(vararg t: String) {
        tags.addAll(t)
    }
}

fun menu(name: String, init: Menu.() -> Unit): Menu {
    val m = Menu(name)
    m.init()
    return m
}

// * DSL 4: ROUTE BUILDER (like Ktor)

class Router {
    private val routes = mutableListOf<Route>()

    fun get(path: String, handler: () -> String) {
        routes.add(Route("GET", path, handler))
    }

    fun post(path: String, handler: () -> String) {
        routes.add(Route("POST", path, handler))
    }

    fun put(path: String, handler: () -> String) {
        routes.add(Route("PUT", path, handler))
    }

    fun delete(path: String, handler: () -> String) {
        routes.add(Route("DELETE", path, handler))
    }

    fun simulate(method: String, path: String) {
        val route = routes.find { it.method == method && it.path == path }
        if (route != null) {
            println("  $method $path -> ${route.handler()}")
        } else {
            println("  $method $path -> 404 Not Found")
        }
    }
}

data class Route(val method: String, val path: String, val handler: () -> String)

fun router(init: Router.() -> Unit): Router {
    val r = Router()
    r.init()
    return r
}

fun main(args: Array<String>) {
    // * DSL 1: TEST FRAMEWORK

    describe("Math Operations") {
        test("addition") {
            (2 + 3) shouldBe 5
        }

        test("subtraction") {
            (10 - 4) shouldBe 6
        }

        test("multiplication") {
            (3 * 4) shouldBe 12
        }

        test("this should fail") {
            (2 + 2) shouldBe 5
        }
    }

    describe("String Operations") {
        test("uppercase") {
            "hello".uppercase() shouldBe "HELLO"
        }

        test("length") {
            "kotlin".length shouldBe 6
        }

        test("not empty") {
            "hello" shouldNotBe ""
        }
    }

    // * DSL 2: SCHEDULE BUILDER

    val myDay = schedule {
        at("09:00") {
            title = "Team standup"
            description = "Daily sync meeting"
            with("Alice", "Bob", "Carol")
        }
        at("11:00") {
            title = "Code review"
            with("Dave")
        }
        at("13:00") {
            title = "Lunch"
        }
        at("14:00") {
            title = "Sprint planning"
            description = "Plan next sprint goals"
            with("Alice", "Bob", "Carol", "Dave", "Eve")
        }
        at("16:00") {
            title = "Deep work"
            description = "Focus time - no meetings"
        }
    }

    myDay.print()

    // * DSL 3: MENU BUILDER

    val restaurant = menu("Restaurant Menu") {
        submenu("Appetizers") {
            item("Soup", 5.99) { tag("vegetarian") }
            item("Salad", 7.99) { tag("vegetarian", "healthy") }
            item("Wings", 9.99) { tag("spicy") }
        }
        submenu("Main Course") {
            item("Steak", 24.99) { tag("popular") }
            item("Pasta", 16.99) { tag("vegetarian") }
            item("Fish", 19.99) { tag("healthy") }
        }
        submenu("Desserts") {
            item("Cake", 6.99)
            item("Ice Cream", 4.99) { tag("popular") }
        }
        submenu("Drinks") {
            item("Coffee", 3.99)
            item("Juice", 4.99) { tag("healthy") }
            item("Water", 0.00)
        }
    }

    println("--- Menu ---")
    restaurant.print()
    println()

    // * DSL 4: ROUTE BUILDER

    val api = router {
        get("/users") { "List of all users" }
        get("/users/1") { "User: Alice" }
        post("/users") { "Created new user" }
        put("/users/1") { "Updated user Alice" }
        delete("/users/1") { "Deleted user Alice" }
        get("/products") { "List of products" }
    }

    println("--- API Routes ---")
    api.simulate("GET", "/users")
    api.simulate("GET", "/users/1")
    api.simulate("POST", "/users")
    api.simulate("PUT", "/users/1")
    api.simulate("DELETE", "/users/1")
    api.simulate("GET", "/products")
    api.simulate("GET", "/unknown")
}