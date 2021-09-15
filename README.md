## What do you need to setup this application?

- JDK/Java 9
- Maven 3.6.2

## External dependencies used

- GSON - to simplify JSON conversion
- Apache Commons IO - to simplify file reading

## Build and Run
To build and run you just need to execute the following command:
Passing the path to find the file you want to test.

```maven
mvn compile exec:java -Dexec.arguments="/Users/caiosalgado/java-projects/authorizer/usecases/account_already_initialized.txt"
```

* Remember that unix file path starts with **/** like the example above and windows file path starts with **C://**
