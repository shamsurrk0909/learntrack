# Setup Instructions

## JDK Version Used

```
java version "27" 2026-09-15
javac 27
```

## Installing the JDK

1. Download a JDK (27 or newer) from oracle.com/java/technologies/downloads or your OS package manager.
2. Install it and make sure `java` and `javac` are on your `PATH`.
3. Verify:

```bash
java -version
javac -version
```

## Hello World Run

Create `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java    # produces HelloWorld.class (bytecode)
java HelloWorld          # the JVM runs the bytecode
```

Output:

```
Hello, World!
```

`javac` turned the source file into bytecode (`HelloWorld.class`), and the `java` command started the JVM, which loaded that class and ran its `main` method.

## Running LearnTrack

See the [README](../README.md) for compile and run commands.
