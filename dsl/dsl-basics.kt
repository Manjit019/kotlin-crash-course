/*
        ! What is DSL? - Kotlin

        * DSL = Domain Specific Language
        ? A mini language for a specific purpose
        ? Makes code more readable and maintainable
        

        * Example : 
        ? SQL is a DSL for database
        ? HTML is a DSL for web pages
        ? Regex is a DSL for pattern matching
        ? Gradle is a DSL for build configuration


        * In Kotlin, you can build your own DSLs
        ? They looks like special syntax
        ? But they are just regular kotlin code


*/

//! Without DSL -> verbose, ugly

data class HtmlPage(
    var title: String = "",
    var body:String = "",
    var css:String = ""
)

fun buildPageUgly():HtmlPage {
    val page = HtmlPage()
    page.title = "My Page"
    page.body = "<h1> Hello </h1><p>World</p>"
    page.css = "body { color : red; }"
    return page
}

// ? With DSL -> clean, readable

fun page(init:HtmlPage.() -> Unit):HtmlPage {
    val page = HtmlPage()
    page.init()
    return page
}

// * ANOTHER EXAMPLE: Config

class ServerConfig {
    var host = "localhost"
    var port = 8080
    var maxConnections = 100
    var timeout = 30
    var debug = false

    override fun toString(): String {
        return "Server($host:$port, max=$maxConnections, timeout=$timeout, debug=$debug)"
    }
}

// ? Without DSL
fun configUgly(): ServerConfig {
    val config = ServerConfig()
    config.host = "example.com"
    config.port = 9090
    config.maxConnections = 500
    config.timeout = 60
    config.debug = true
    return config
}

// ? With DSL
fun server(init: ServerConfig.() -> Unit): ServerConfig {
    val config = ServerConfig()
    config.init()
    return config
}


fun main(args: Array<String>) {
    // * WITHOUT DSL

    println("--- Without DSL ---")
    val page1 = buildPageUgly()
    println(page1)
    println()

    // * With DSL
    println("--- With DSL ---")
    val page2 = page{
        title = "My Page"
        body = "<h1>Hello</h1><p>World</p>"
        css = "body { color: red; }"
    }
    println(page2)
    println()

    // * SERVER CONFIG

    println("--- Config Without DSL ---")
    println(configUgly())

    println()

    println("--- Config With DSL ---")
    val myServer = server {
        host = "example.com"
        port = 9090
        maxConnections = 500
        timeout = 60
        debug = true
    }
    println(myServer)

    println()
}