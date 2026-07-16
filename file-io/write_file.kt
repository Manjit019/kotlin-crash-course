/*
     ?! KOTLIN — WRITING FILES
    
     * writeText(text)   -> write (overwrites existing)
     * appendText(text)  -> add to end of file
     * printWriter { }   -> formatted writing
     * bufferedWriter { } -> efficient writing
    
     ! writeText() OVERWRITES the entire file
     ? appendText() ADDS to the end
     ? If file doesnt exist, it gets created

 */

 import java.io.File

 fun main(args: Array<String>) {
    
    //? Basic Write 

    File("output.txt").writeText("Hello Kotlin\n")
    println("Wrote to output.txt using writeText() (overwrites existing content).")
    println("Content : ${File("output.txt").readText()}")

    //? Overwrite warning
   File("output.txt").writeText("First write")
   println("After first write: ${File("output.txt").readText()}")

   File("output.txt").writeText("Second write")
   println("After second write: ${File("output.txt").readText()}")

   println()

   //? Append text
   File("log.txt").writeText("Log start\n")
   File("log.txt").appendText("Entry 1 : App Launched\n")
   File("log.txt").appendText("Entry 2 : User logged in\n")
   File("log.txt").appendText("Entry 3 : User performed action\n")

   println("Wrote to log.txt using appendText() (adds to end of file).")
   println(File("log.txt").readText())

   // write multiple lines
   val names = listOf("Alice", "Bob", "Charlie", "David")
   File("name.txt").writeText(names.joinToString(separator = "\n"))
   println("name.txt content:\n${File("name.txt").readText()}")
   println()

   //? Print Writer (formatted output)

   File("report.txt").printWriter().use { writer -> 
      writer.println("=== Monthly Report ===")
      writer.println()
      writer.printf("%-10s %10s%n", "Item", "Price")
      writer.printf("%-10s %10s%n", "----", "-----")
      writer.printf("%-10s %10.2f%n", "Laptop", 999.99)
      writer.printf("%-10s %10.2f%n", "Mouse", 29.99)
      writer.printf("%-10s %10.2f%n", "Keyboard", 79.99)
      writer.println()
      writer.printf("%-10s %10.2f%n", "Total", 1109.97)
   }

   println("report.txt content:\n${File("report.txt").readText()}")

   println()

   //? Buffered writer (efficient for large writes)
   File("large.txt").bufferedWriter().use { writer -> 
      for (i in 1..10){
         writer.write("Line $i: This is a large file example.")
         writer.newLine()
      }
   }
   println("large.txt content:\n${File("large.txt").readText()}")


   //? Write data class to file
   data class User(val id:Int, val name:String, val email:String)

   val users = listOf(
      User(1, "Alice", "alice@gmail.com"),
      User(2, "Bob", "bob@gmail.com"),
      User(3, "Charlie", "charlie@gmail.com")
   )

   //Write as CSV
   File("users.csv").printWriter().use { writer ->
      writer.println("id,name,email")
      users.forEach { user -> 
         writer.println("${user.id},${user.name},${user.email}")
      }
   }
   println("users.csv content:\n${File("users.csv").readText()}")


   //? Real World Example: Logging

   class SimpleLogger(private val filename : String){
      init {
         File(filename).writeText("=== Log Start ===\n")
      }
      fun log(level: String, message : String){
         val timestamp = System.currentTimeMillis()
         File(filename).appendText("[$timestamp] [$level] $message\n")
      }

      fun info(message : String)= log("INFO", message)
      fun error(message : String)= log("ERROR", message)
      fun warn(message : String)= log("WARN", message)

      fun printLogs(){
         println("Logs from $filename:")
         println(File(filename).readText())
      }
   }

   val logger = SimpleLogger("app.log")
   logger.info("Application started")
   logger.info("Loading config")
   logger.warn("Config file not found, using defaults")
   logger.error("Connection Failed")
   logger.info("Retrying connection...")
   logger.printLogs()



    // ! cleanup

    listOf("output.txt","log.txt","name.txt","report.txt","large.txt","users.csv","app.log").forEach {
      File(it).delete()
    }

    println("Cleaned up all created files.")
 }
