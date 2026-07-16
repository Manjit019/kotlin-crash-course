# File I/O in Kotlin

## What is File I/O?
Reading from and writing to files on disk.
Kotlin makes file operations very simple compared to Java.

## Key Class
- java.io.File -> main class for file operations

## Common Operations

| Operation         | Function                          |
|-------------------|-----------------------------------|
| Read entire file  | File("x.txt").readText()          |
| Read lines        | File("x.txt").readLines()         |
| Read line by line | File("x.txt").forEachLine { }     |
| Write text        | File("x.txt").writeText("hello")  |
| Append text       | File("x.txt").appendText("more")  |
| Check exists      | File("x.txt").exists()            |
| Delete file       | File("x.txt").delete()            |
| File size         | File("x.txt").length()            |
| File name         | File("x.txt").name                |
| File extension    | File("x.txt").extension           |
| Parent directory  | File("x.txt").parent              |

## Important Notes
- readText() loads ENTIRE file into memory
- For large files, use forEachLine or bufferedReader
- writeText() OVERWRITES existing content
- appendText() ADDS to existing content
- Always handle FileNotFoundException