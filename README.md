# 🚀 Kotlin Crash Course (Beginner to Advanced)

A structured and practical crash course designed to take you from **Kotlin basics to advanced concepts** with clear explanations and real coding examples.

---

## 📌 Overview

This repository provides a complete learning path for Kotlin, starting from fundamental programming concepts and gradually progressing to advanced topics.

It is designed for:

* Beginners starting programming with Kotlin
* Developers transitioning to Kotlin
* Students preparing for Android or backend development

---

## 🧠 What You Will Learn

* Kotlin syntax and fundamentals
* Variables, data types, and operators
* Control flow (if/else, loops, when)
* Functions and higher-order functions
* Object-Oriented Programming (OOP)
* Null safety and type system
* Collections and standard library
* Advanced Kotlin concepts

---

## 💡 About Kotlin

Kotlin is a modern, statically typed programming language developed by JetBrains. It is widely used for:

* Android development 📱
* Backend development 🌐
* Cross-platform applications ⚡

---

## ▶️ How to Run Kotlin Code

### 1. Compile a Kotlin file

```bash
kotlinc FileName.kt -include-runtime -d output.jar
```

### 2. Run the compiled program

```bash
java -jar output.jar
```


---

## ✨ Example

```kotlin
fun main() {
    println("Welcome to Kotlin Crash Course 🚀")
}
```

## ⚙️ CLI Tool Included (krun)

This project includes a custom Node.js CLI tool to simplify Kotlin execution.

### 🧰 Script: `compile-run-clean.js`

A lightweight automation script that:

* Compiles Kotlin files
* Runs the generated program
* Cleans up `.jar` files automatically

---

## ▶️ Usage

### Run directly:

```bash
npm run kc FileName  # .kt extension is optional
```

### Or with shortcut (krun)

After linking or setting alias:

```bash
krun FileName.kt
```

You can also:

```bash
krun FileName        # .kt extension optional
krun FileName --keep # keeps compiled jar
krun FileName --verbose # shows full compiler output
```

---

## ⚙️ What It Does Internally

1. Detects Kotlin file (with or without `.kt`)
2. Compiles using:

```bash
kotlinc FileName.kt -include-runtime -d output.jar
```

3. Runs program:

```bash
java -jar output.jar
```

4. Cleans temporary build files automatically



---

## 🎯 Course Goal

The goal of this crash course is to help you:

* Build strong programming fundamentals
* Understand Kotlin in a practical way
* Write clean and efficient code
* Prepare for real-world development

---

## 🤝 Contributions

Contributions are welcome. You can:

* Improve existing examples
* Add new practice programs
* Fix issues or optimize code

---

## 📜 License

This project is open-source and intended for educational purposes.

---

## ⭐ Support

If you find this helpful, consider starring the repository ⭐