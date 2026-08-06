# Environment Setup & JDK Installation

## JDK Version Used

This project uses **Java SE Development Kit (JDK) 25.0.3 LTS**

```
Java Version: 25.0.3
Release Date: 2026-04-21
Type: Long-Term Support (LTS)
Compiler: javac 25.0.3
Runtime: Java HotSpot(TM) 64-Bit Server VM
```

---

## Installing and Configuring Java (JDK)

### Prerequisites
- Administrator access to your machine
- Internet connection for downloading JDK

### Step 1: Download JDK 25.0.3 LTS

Visit the official Java download page:
- **Oracle JDK**: https://www.oracle.com/java/technologies/downloads/
- Select JDK 25.0.3 LTS for your operating system (Windows, macOS, or Linux)

### Step 2: Install JDK

#### On Windows:
1. Download the `.exe` installer
2. Run the installer and follow the setup wizard
3. Choose installation directory (default: `C:\Program Files\Java\jdk-25.0.3`)
4. Complete the installation

#### On macOS:
```bash
# Using Homebrew (recommended)
brew install openjdk@25

# Or download DMG and follow installer instructions
```

#### On Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-25-jdk
```

### Step 3: Configure Environment Variables

#### On Windows:
1. Open System Properties (`Win + Pause`)
2. Click "Environment Variables"
3. Add new system variable:
   - **Variable name**: `JAVA_HOME`
   - **Variable value**: `C:\Program Files\Java\jdk-25.0.3`
4. Add to PATH:
   - Edit `Path` variable
   - Add: `%JAVA_HOME%\bin`

#### On macOS/Linux:
Add to `~/.bash_profile` or `~/.zshrc`:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
export PATH=$JAVA_HOME/bin:$PATH
```

Then reload:
```bash
source ~/.bash_profile  # or ~/.zshrc
```

### Step 4: Verify Installation

Open terminal/command prompt and run:
```bash
java -version
javac -version
```

Expected output:
```
java version "25.0.3" 2026-04-21 LTS
Java(TM) SE Runtime Environment (build 25.0.3+9-LTS-195)
Java HotSpot(TM) 64-Bit Server VM (build 25.0.3+9-LTS-195, mixed mode, sharing)

javac 25.0.3
```

---

## Understanding the "Hello World" Program

### The Program

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### What Happens When You Run It

#### Step 1: **Write the Code**
- Save the code as `HelloWorld.java` (filename MUST match class name)
- This is plain text source code that humans can read

#### Step 2: **Compile the Code**
```bash
javac HelloWorld.java
```
- **javac** (Java Compiler) reads your `.java` source file
- Converts it to **bytecode** (machine-independent intermediate code)
- Creates `HelloWorld.class` file
- This `.class` file is what the JVM understands

#### Step 3: **Run the Program**
```bash
java HelloWorld
```
- **java** (JVM) loads the `HelloWorld.class` bytecode
- Executes the bytecode on your machine
- Prints: `Hello, World!`

### Key Points

| Step | Tool | Input | Output |
|------|------|-------|--------|
| Write | Text Editor | Source code | `.java` file |
| Compile | `javac` | `.java` file | `.class` bytecode |
| Run | `java` (JVM) | `.class` file | Program output |

### Why This Two-Step Process?

**Separation of Concerns:**
- **Compilation** (once): Checks syntax and creates bytecode
- **Execution** (many times): JVM runs the same bytecode on any machine

This is the foundation of "Write Once, Run Anywhere" (WORA) principle!

---

## Next Steps

For **Compilation and Running** the application, see [README.md](../README.md).

For **Project Structure and Troubleshooting**, see [README.md](../README.md).

To understand **Design Decisions** behind the architecture, see [Design_Notes.md](Design_Notes.md).
