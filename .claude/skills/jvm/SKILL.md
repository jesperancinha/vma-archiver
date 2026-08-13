---
name: jvm language patterns.
description: Conventions for using in all JVM languages. All of these conventions are applicable to Java, Kotlin, Scala, Groovy, and other JVM languages.
---

The following is a list of simple rules to apply to jvm code.


## 1. Remove all unused imports

If you find unused imports, please remove them. This is a good practice to keep the code clean and maintainable.

## 2. Use static imports when possible

When using static methods or constants from a class, it is recommended to use static imports instead of fully qualifying the class name. This can make the code more readable and easier to understand.
