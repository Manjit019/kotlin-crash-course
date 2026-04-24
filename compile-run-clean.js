#!/usr/bin/env node

const { execSync } = require("child_process");
const fs = require("fs");
const path = require("path");

//  ANSI Colors 
const c = {
  reset: "\x1b[0m",
  bold: "\x1b[1m",

  red: "\x1b[31m",
  green: "\x1b[32m",
  yellow: "\x1b[33m",
  blue: "\x1b[34m",
  magenta: "\x1b[35m",
  cyan: "\x1b[36m",
  gray: "\x1b[90m",
};

//  Helpers 
function color(text, code) {
  return code + text + c.reset;
}

function resolveFile(input) {
  if (!input) return null;

  if (!input.endsWith(".kt")) {
    input += ".kt";
  }

  const fullPath = path.resolve(input);

  if (!fs.existsSync(fullPath)) {
    console.error(color(`❌ File not found: ${fullPath}`, c.red));
    process.exit(1);
  }

  return fullPath;
}

function runCommand(command, verbose = false) {
  try {
    execSync(command, {
      stdio: verbose ? "inherit" : "pipe",
    });
  } catch (err) {
    console.error(color("❌ Command failed", c.red));
    console.log(color(command, c.gray));

    if (!verbose) {
      console.error(color(err.stderr?.toString() || err.message, c.yellow));
    }
    process.exit(1);
  }
}

//  Main 
function runKotlin(fileInput, options = {}) {
  const filePath = resolveFile(fileInput);

  const dir = path.dirname(filePath);
  const baseName = path.basename(filePath, ".kt");
  const jarName = `${baseName}.jar`;
  const jarPath = path.join(dir, jarName);

  const { keep = false, verbose = false } = options;

  console.log(color(`\n📄 File: ${filePath}\n`, c.cyan + c.bold));

  try {
    // Compile
    console.log(color("🔧 Compiling...", c.blue));
    runCommand(
      `kotlinc "${filePath}" -include-runtime -d "${jarPath}"`,
      verbose,
    );
    console.log(color("✔ Compilation successful\n", c.green));

    if (!fs.existsSync(jarPath)) {
      throw new Error("JAR not created.");
    }

    // Run
    console.log(color("🚀 Running...\n", c.magenta));
    execSync(`java -Dfile.encoding=UTF-8 -jar "${jarPath}"`, {
      stdio: "inherit",
    });
  } finally {
    // Clean
    if (!keep && fs.existsSync(jarPath)) {
      fs.unlinkSync(jarPath);
      console.log(color(`\n🧹 Cleaned: ${jarName}`, c.gray));
    } else if (keep) {
      console.log(color(`\n📦 Kept: ${jarName}`, c.yellow));
    }
  }
}

//  CLI 
const args = process.argv.slice(2);

if (args.length === 0) {
  console.log(
    color(
      `
🚀 Kotlin Runner CLI

Usage:
  krun <file> [options]

Examples:
  krun hello
  krun hello.kt
  krun hello --keep
  krun hello --verbose

Options:
  --keep     Keep generated .jar
  --verbose  Show full compiler output
`,
      c.bold,
    ),
  );

  process.exit(0);
}

const fileArg = args.find((arg) => !arg.startsWith("--"));
const options = {
  keep: args.includes("--keep"),
  verbose: args.includes("--verbose"),
};

runKotlin(fileArg, options);
