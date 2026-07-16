/*
     ? KOTLIN — READING FILES
    
     * readText()       -> entire file as one String
     * readLines()      -> file as List<String>
     * forEachLine { }  -> process line by line (memory efficient)
     * bufferedReader() -> for large files
    
     ! readText() loads everything into memory
     ! Use forEachLine for large files
     ? Always handle exceptions (file might not exist)
 */

 import java.io.File
 import java.io.FileNotFoundException

 fun main(args: Array<String>) {
    // ? create a sample file
    val sampleFile = File("sample.txt")
    sampleFile.writeText("""
        Hello Koltlin
        This is line 2
        File I/O is fun!
        Kotlin makes it simple
        Line 5
    """.trimIndent())

    println("Created sample.txt with 5 lines.")
    println();

    //? Read entire file as one String

    val content = File("sample.txt").readText()
    println("Read entire file as one String:")
    println(content)
    println();

    //? Reading as list of lines

    val lines = File("sample.txt").readLines()
    println("Read file as List<String> (readLines()):")
    println("Total lines: ${lines.size}")
    for((index,line) in lines.withIndex()) {
        println("Line ${index + 1}: $line")
    }

    println();

    //? Read line by line (memory efficient)
    File("sample.txt").forEachLine{ line ->
        println("Line: $line")
    }

    println();

    //? BufferedReader for large files

    println("Buffer Reader (for large files):")

    File("sample.txt").bufferedReader().use { reader ->
        var line = reader.readLine()
        while (line != null) {
            println("  >> $line")
            line = reader.readLine()
        }
        
    }
    // * .use { } automatically closes the reader after use

    println()

    //? File Information
    val file = File("sample.txt")

    println("File Information:")
    println("Name : ${file.name}")
    println("Path : ${file.path}")
    println("Absolute Path : ${file.absolutePath}")
    println("Extension : ${file.extension}")
    println("Size : ${file.length()} bytes")
    println("Exists : ${file.exists()}")
    println("Is File : ${file.isFile}")
    println("Is Directory : ${file.isDirectory}")
    println("Can Read : ${file.canRead()}")
    println("Can Write : ${file.canWrite()}")

    println()

    //? Exception Handling
    try {
        val missingFile = File("missing.txt").readText()
        println(missingFile)   
    } catch(e : FileNotFoundException) {
        println("Error: File not found.")
    } catch(e : Exception) {
        println("Error: ${e.message}")
    }

    //* Safer way -> Check first
    val path = "missing.txt"
    val missingFile = File(path)

    val safeContent = if(missingFile.exists()) {
        missingFile.readText()
    } else {
        "File '$path' does not exist."
    }
    println(safeContent)

    println()

    //* Read with charset

    println("Read with charset (UTF-8):")
    val utf8Content = File("sample.txt").readText(Charsets.UTF_8)
    println(utf8Content.take(30) + "...") // print first 30 chars

    //! cleanup
    sampleFile.delete()
    println("Deleted sample.txt")
 }