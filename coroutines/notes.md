# Coroutines in Kotlin

## What are Coroutines?
Lightweight threads that can be suspended and resumed.
They allow you to write asynchronous code that looks synchronous.

## Why Coroutines?
- Threads are expensive (each thread = ~1MB memory)
- Coroutines are cheap (thousands can run on few threads)
- No callback hell
- Sequential looking code that runs asynchronously

## Key Concepts

| Concept         | Meaning                                      |
|-----------------|----------------------------------------------|
| suspend         | Function that can pause and resume            |
| launch          | Starts coroutine, returns Job (no result)     |
| async           | Starts coroutine, returns Deferred (result)   |
| runBlocking     | Bridges regular code and coroutines           |
| delay           | Non-blocking sleep                            |
| Job             | Handle to a coroutine (cancel, join)          |
| Deferred        | Job that returns a value (.await())           |
| CoroutineScope  | Defines lifetime of coroutines                |

## Dispatchers

| Dispatcher          | Use For                         |
|---------------------|---------------------------------|
| Dispatchers.Main    | UI updates (Android)            |
| Dispatchers.IO      | Network, file, database         |
| Dispatchers.Default | CPU heavy work                  |
| Dispatchers.Unconfined | Testing, not recommended     |

## Dependencies
Add to build.gradle:
```
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
```

Or compile with:
```
kotlinc file.kt -include-runtime -d file.jar -cp kotlinx-coroutines-core-1.7.3.jar
```