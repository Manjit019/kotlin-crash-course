/*
     ?! KOTLIN — READING AND PROCESSING LINES
    
     * readLines()      -> List<String> of all lines
     * forEachLine { }  -> process one line at a time
     * useLines { }     -> lazy sequence of lines
    
     * Combine with collection operations:
       ? filter, map, count, groupBy, etc.
    
     ? useLines is best for large files
     ? readLines is best for small files
 */

 import java.io.File

 fun main(args: Array<String>) {
    
    //? setup : create a sample data file

    val dataFile = File("students.csv")
    dataFile.writeText("""
        id,name,grade,score
        1,Alice,A,95
        2,Bob,B,82
        3,Carol,A,91
        4,Dave,C,73
        5,Eve,A,97
        6,Frank,B,85
        7,Grace,C,70
        8,Henry,B,88
        9,Ivy,A,93
        10,Jack,C,68
    """.trimIndent())

    println("Created students.csv")
    println()


    //? Read all lines
    val allLines = File("students.csv").readLines()
    println("All lines in students.csv:")
    println("Total Lines : ${allLines.size} bytes")
    allLines.take(3).forEach{ println(it) }
    println(" ...")
    println()


    //? Skip header and parse
    
    data class Student(
        val id: Int,
        val name: String,
        val grade: String,
        val score: Int
    )

    val students = File("students.csv").readLines()
        .drop(1) // skip header
        .map { line -> 
            val parts = line.split(",")
            Student(
                id = parts[0].toInt(),
                name = parts[1],
                grade = parts[2],
                score = parts[3].toInt()
            )
        }
    println("Parsed students:")
    students.forEach { println(it) }
    println()

    //? Filter and query
    println("Students with grade A:")
    students.filter { it.grade == "A" }
        .forEach { println("    ${it.name} : ${it.score}") }
    println()
    println("--- Score > 85 ---")
    students
        .filter { it.score > 85 }
        .sortedByDescending { it.score }
        .forEach { println("  ${it.name}: ${it.score}") }

    println()

    //? Statistics 
    println("--- Statistics ---")
    println("Average score: ${students.map { it.score }.average()}")
    println("Highest score: ${students.maxOf { it.score }}")
    println("Lowest score:  ${students.minOf { it.score }}")
    println("Total students: ${students.size}")

    println()

    // * GROUP BY

    println("--- Group by Grade ---")
    students
        .groupBy { it.grade }
        .forEach { (grade, group) ->
            val avg = group.map { it.score }.average()
            println("  Grade $grade: ${group.size} students, avg: ${"%.1f".format(avg)}")
        }

    println()

    // ? useLines -> lazy, memory efficient

    println("--- useLines (lazy) ---")
    File("students.csv").useLines { lines ->
        lines
            .drop(1)
            .map { it.split(",") }
            .filter { it[3].toInt() > 80 }
            .forEach { println("  ${it[1]}: ${it[3]}") }
    }
    // ? File is automatically closed after useLines

    println()

    //? Word count from file

    val textFile = File("article.txt")
    textFile.writeText("""
        Kotlin is a modern programming language
        Kotlin runs on the JVM
        Kotlin is concise and safe
        Kotlin supports functional programming
        Kotlin is great for Android development
    """.trimIndent())

    println("--- Word Count ---")

    val wordCount = File("article.txt").readText()
        .lowercase()
        .split("\\s+".toRegex())
        .groupBy{ it }
        .mapValues { it.value.size }
        .toList()
        .sortedByDescending { it.second }

    wordCount.take(5).forEach { (word, count) ->
        println("  $word: $count")
    }
    println()

    //? Search in File

    fun searchFile(filename:String, query:String):List<Pair<Int, String>>{
        return File(filename).readLines()
            .withIndex()
            .filter { it.value.contains(query, ignoreCase = true) }
            .map{ it.index + 1 to it.value } // line number starts from 1
        
    }

    println("--- Search 'programming' in article.txt ---")
    searchFile("article.txt", "programming").forEach { (lineNumber, line) ->
        println("  Line $lineNumber: $line")
    }
    println()

    // ? REAL WORLD -> Log file analyzer

    val logFile = File("server.log")
    logFile.writeText("""
        [INFO] Server started
        [INFO] User alice logged in
        [ERROR] Database connection failed
        [INFO] Retrying connection
        [ERROR] Timeout after 30s
        [WARN] High memory usage
        [INFO] Connection restored
        [ERROR] File not found: config.yaml
        [INFO] Using default config
        [WARN] Deprecated API called
    """.trimIndent())

    println("--- Log Analysis ---")
    val logs = File("server.log").readLines()

    val grouped = logs.groupBy { line -> 
        when {
            "[ERROR]" in line -> "ERROR"
            "[WARN]" in line -> "WARN"
            "[INFO]" in line -> "INFO"
            else -> "OTHER"
        }
    }

    grouped.forEach { (level, entries) ->
        println("  $level: ${entries.size} entries")
    }
    println()
    println("Errors : ")
    grouped["ERROR"]?.forEach { println("  $it") }


    // ! CLEANUP

    listOf("students.csv", "article.txt", "server.log").forEach {
        File(it).delete()
    }
    println("\nCleaned up all files")
 }