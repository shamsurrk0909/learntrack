# JVM Basics

## JDK, JRE and JVM

- **JVM (Java Virtual Machine):** the program that actually runs Java bytecode. It loads classes, checks them, and executes them on your computer. There is a different JVM build for each operating system.
- **JRE (Java Runtime Environment):** the JVM plus the standard class libraries (strings, collections, I/O, ...) needed to *run* Java programs.
- **JDK (Java Development Kit):** the JRE plus developer tools such as `javac` (the compiler). You need the JDK to *write and compile* Java programs.

A simple way to remember it: JDK contains JRE, and JRE contains JVM.

## What is Bytecode?

When you compile a `.java` file with `javac`, you do not get machine code for your specific processor. You get **bytecode** in a `.class` file: a compact, platform-independent set of instructions. The JVM reads this bytecode and translates it into instructions the current machine understands, often using a just-in-time (JIT) compiler to make hot code fast.

## Write Once, Run Anywhere

"Write once, run anywhere" means the same compiled Java program can run on Windows, macOS or Linux without being recompiled. This works because the compiler produces bytecode, not machine code, and every platform has its own JVM that knows how to run that bytecode.

So the developer only maintains one version of the program, while the platform-specific work is done by the JVM. The only requirement is that the target machine has a compatible JVM installed.
