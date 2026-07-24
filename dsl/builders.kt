/*
    ! Builders :

    * Kotlin has built-in builder functions
    ? buildString { }
    ? buildList { }
    ? buildMap { }
    ? buildSet { }

    * These use lambda with receiver internally

    ? Builder pattern:
    * Create object
    * Configure inside lambda
    * Return finished object


*/

fun main(args: Array<String>) {
    println("--- buildString ---")

    val csv = buildString {
        appendLine("name,age,email")
        appendLine("Alice,25,alice@email.com")
        appendLine("Bob,30,bob@email.com")
        appendLine("Carol,28,carol@email.com")
    }

    println(csv)

    // * buildList

    println("--- buildList ---")

    val numbers = buildList {
        add(1)
        add(2)
        add(3)
        addAll(listOf(4, 5, 6))
        if (true) add(7)   // ? conditional adding
    }

    println("Numbers: $numbers")

    println()

    // * buildMap

    println("--- buildMap ---")

    val config = buildMap {
        put("host", "localhost")
        put("port", "8080")
        put("debug", "true")
        if (System.getProperty("os.name").contains("Windows")) {
            put("os", "windows")
        }
    }

    println("Config: $config")

    println()

    // * buildSet

    println("--- buildSet ---")

    val tags = buildSet {
        add("kotlin")
        add("jvm")
        add("android")
        add("kotlin")   // ? duplicate ignored
    }

    println("Tags: $tags")

    
    // * CUSTOM BUILDER -> HTML

    class Tag(val name : String) {
        val children = mutableListOf<Tag>()
        var text = ""
        val attributes = mutableMapOf<String, String>()

        fun tag(name: String, init: Tag.() -> Unit = {}) {
            val child = Tag(name)
            child.init()
            children.add(child)
        }

        fun attr(key: String, value: String) {
            attributes[key] = value
        }

        fun render(indent: Int = 0): String {
            val pad = "  ".repeat(indent)
            val attrs = if (attributes.isEmpty()) "" else
                " " + attributes.map { "${it.key}=\"${it.value}\"" }.joinToString(" ")

            return buildString {
                if (children.isEmpty() && text.isEmpty()) {
                    appendLine("$pad<$name$attrs/>")
                } else {
                    appendLine("$pad<$name$attrs>")
                    if (text.isNotEmpty()) appendLine("$pad  $text")
                    children.forEach { append(it.render(indent + 1)) }
                    appendLine("$pad</$name>")
                }
            }
        }
    }   
     
    fun html(init: Tag.() -> Unit): Tag {
        val root = Tag("html")
        root.init()
        return root
    }  
    
    // ? Use it like HTML DSL
    val page = html {
        tag("head") {
            tag("title") { text = "My Page" }
        }
        tag("body") {
            tag("h1") { text = "Hello Kotlin DSL" }
            tag("p") {
                text = "This is built with a DSL"
                attr("class", "intro")
            }
            tag("ul") {
                tag("li") { text = "Item 1" }
                tag("li") { text = "Item 2" }
                tag("li") { text = "Item 3" }
            }
        }
    }

    println("--- HTML Builder ---")
    println(page.render())

    // * CUSTOM BUILDER -> SQL-like query

    class Query {
        var table = ""
        var columns = mutableListOf<String>()
        var conditions = mutableListOf<String>()
        var orderBy = ""
        var limit = 0

        fun select(vararg cols: String) {
            columns.addAll(cols)
        }

        fun from(tableName: String) {
            table = tableName
        }

        fun where(condition: String) {
            conditions.add(condition)
        }

        fun orderBy(column: String) {
            orderBy = column
        }

        fun limit(n: Int) {
            limit = n
        }

        fun build(): String = buildString {
            val cols = if (columns.isEmpty()) "*" else columns.joinToString(", ")
            append("SELECT $cols FROM $table")
            if (conditions.isNotEmpty()) {
                append(" WHERE ${conditions.joinToString(" AND ")}")
            }
            if (orderBy.isNotEmpty()) {
                append(" ORDER BY $orderBy")
            }
            if (limit > 0) {
                append(" LIMIT $limit")
            }
        }
    }

    fun query(init: Query.() -> Unit): String {
        val q = Query()
        q.init()
        return q.build()
    }

    // ? Use it like SQL
    val sql = query {
        select("name", "age", "email")
        from("users")
        where("age > 18")
        where("active = true")
        orderBy("name")
        limit(10)
    }

    println("--- SQL Builder ---")
    println(sql)
}