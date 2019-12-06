Homework assignment no. 2, URL Crawler
====================================

**Publication date:** 11th November 

**Submission deadline:** 2nd December 23:59

General information
-------------------
In this assignment you will write an application which can crawl a net of web pages and build either an ```index``` or ```reverse index``` of links for each page (represented by URL)

### Index
An index is a ```Map``` where the ```key``` is a URL of a web page and the ```value``` is a list of all links contained at this page.

```
A: [B, C, D]
B: [C]
C: [A, C, D]
D: []
```

### Reverse Index
A reverse index is once again a ``Map`` where ``key`` is a URL of a web page. However this time the ```value``` is a list of pages  **containing the link used as the key**`

For example a reverse-index for the index above would look like this. 
```
A: [C]
B: [A]
C: [A, B, C]
D: [A, C]
```

### Few Rules & Tips
For the purpose of this implementation, students are forbidden to use any existing library which provides a complex solution to this problem (e.g. existing crawlers or services).
If you are not sure whether it's ok to use some class, **it's always better to ask**.

The assignment project contains a dependency definition for a http client library called ```OkHttp```

```
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>${version.okhttp}</version>
</dependency>
```

The docs for this library cant be found on [OkHttp web page](https://square.github.io/okhttp/). You are encouraged to use this library -- although you don't have to. 

### Evaluation
The maximum number of points for this assignment is **9**.

- **6 points** for passing tests (attached tests do not guarantee a 100% correctness).
- **3 points** for correct and clean implementation (evaluated by your class teacher).


In cases **when provided tests do not pass** with submitted solution you can be granted no more than **4 points** (this means that you can be granted **0 points** as well)!

### Preconditions
To successfully implement this assignment you need to know the following

1. Working with Interfaces
2. Understanding of method overloading
3. Working with collections
4. Ability to learn and understand external code. 

### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw02``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.hw02.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to  package ```cz.muni.fi.pb162.hw02``` or ```cz.muni.fi.pb162.hw02.impl``` for classes implemented as part of your solution.

### Compiling the project
The project can be compiled and packaged in the same way you already know 

```bash
$ mvn clean compile
```

The only difference is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error. 
You can temporarily disable this behavior when running this command. 

```bash
$ mvn clean compile -Dcheckstyle.fail=false
```

You can consult your seminar teacher to help you set the ```checkstyle.fail``` property in your IDE (or just google it). 


### Submitting the assignment
The procedure to submit your solution may differ based on your seminar group. However generally it should be ok to submit ```target/homework01-2019-1.0-SNAPSHOT-sources.jar``` to the homework vault.

Step 1: 
---------------------------
Create ```Crawler``` class by implementing the ```SmartCrawler``` interface. 
All the necessary implementation info is in JavaDocs. 
