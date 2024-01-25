# email-app

This project uses JavaSE. The service takes standard input, where each line contains an email address eg.
`joeblogs@saldoaaps.com`
`andrew.smith@gmail.com`
and outputs the 10 domains (or less if there aren't that many) that appear the most often with a
count of the number of times it appears after each domain. eg.
`saldoaaps.com 2553`
`yahoo.com 1315`
and up to 8 more lines.

## Prerequisites

1. JDK 17+ installed with JAVA_HOME configured appropriately
2. Apache Maven 3.9.6 

Preparation:
```
./mvnw clean compile
```
```
./mvnw test
```

## Packaging and running the application

The application can be packaged using:
```
./mvnw package
```
It produces the `email-app-1.0-SNAPSHOT-jar-with-dependencies.jar` file in the `target/` directory.

The application is now runnable using
```
java  -jar .\target\email-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

If run the application with `"-Dmax.domain.info.console.rows=1"` property,
it will change the count of output lines in console.
```
java "-Dmax.domain.info.console.rows=1" -jar .\target\email-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

