# Lightweight DBMS Prototype

## Problem Statement

Build a prototype of a light-weight DBMS using the Java programming language, capable of performing at least three 
required functions and one optional function from a given list of DBMS functions.

## Environment

- Java IDE Used: IntelliJ
- JDK Version: OpenJDK 20
- JavaDocs Specification
  - Followed JavaDocs specification for commenting styles, incorporating `@param` and `@return` throughout the code.

## SOLID Principles

Implemented SOLID Principles:

1. **Single Responsibility Principle**: Ensured that each class has only one reason or purpose to change, adhering to the Single Responsibility Principle.

2. **Open Closed Principle**: Developed classes and modules to be open for extension but closed for modification, following the Open Closed Principle.

3. **Liskov Substitution Principle**: Ensured that objects of a superclass can be replaced with objects of a subclass without affecting program correctness, adhering to the Liskov Substitution Principle.

4. **Interface Segregation Principle**: Ensured no client is forced to depend on methods it does not use, following the Interface Segregation Principle.

5. **Dependency Inversion Principle**: Ensured high-level modules do not depend on low-level modules; both depend on abstractions, adhering to the Dependency Inversion Principle.

## Console-Based Application

The application is console-based, accepting user input in the form of SQL queries after the user has logged in.

- Users can create databases using queries, such as `CREATE DATABASE database_name;`.
  
## Two-Factor Authentication

- Users set up a username, password, and security question/answer during signup.
- After successful signup, users can log in using their credentials.
- The application supports multiple users.
- Hashing is performed using the standard Java library MD5.

## Persistent Storage

User account information, table data, meta-data, and logs are stored in files with delimiters.

## Implementation of Queries

### Create Query : CREATE DATABASE database_name;

The application uses the getDatabaseName() method to extract the database name from the query.
The createDatabase() function creates a folder with the given database name.

### Create Table Query: CREATE TABLE Persons (PersonID int, LastName varchar, FirstName varchar, Address varchar, City varchar);

The application uses the getTableName() method to extract the table name from the query.
The createTable() function creates a file with the given table name.

### Select Query: SELECT * FROM tableName;

The application uses the getTableNameSelect() method to extract the table name from the query.
The readFileContents() function reads the content of the file with the table name and prints a 2D array to the console.

### Insert Query: INSERT INTO books VALUES (12, newbook, 100);

The application uses the getTableName() method to extract the table name from the query.
The getValues() function splits the values between the round brackets and returns a string array.
The writeFileValues() function writes data below the columns.


### Update Query: UPDATE books SET bookName = 'Two states', pages = 300 WHERE bookId = 7;

The application uses the getTableName() method to extract the table name from the query.
The getUpdates() function splits column names and values, while getWhereUpdate() splits the WHERE condition part.
Values are then updated based on the given conditions.


### Delete Query: DELETE FROM books;

The query deletes the contents of the file.
The application uses the getTableName() method to extract the table name from the query.
The deleteFileContents() function deletes the data in the table.

