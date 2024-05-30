# JavaDeconstructor Documentation

## Overview

This C++ code is designed to generate Java files from a specified input format. It defines various classes and functions to parse input, create Java class, enum, and interface files, and output them with appropriate Java syntax.

## Table of Contents

1. [Classes](#classes)
   - [Field](#field)
   - [Method](#method)
   - [Constructor](#constructor)
   - [File](#file)
   - [Enum](#enum)
   - [Class](#class)
   - [Interface](#interface)
   - [Exception](#exception)
   - [Wrapper](#wrapper)
2. [Helper Functions](#helper-functions)
   - [split](#split)
   - [protlvl](#protlvl)
   - [removeQuotes](#removeQuotes)
   - [concat](#concat)
   - [createFile](#createFile)
   - [createField](#createField)
   - [createMethod](#createMethod)
   - [createConstructor](#createConstructor)
   - [getFile](#getFile)
   - [typeMaker](#typeMaker)
   - [typeNameSeparator](#typeNameSeparator)

# Classes

The uml class diagram can be found in javaDestructorizer.drawio (xml)

## Field

Represents a field in a Java class.

### Members

- string name
- string type
- string visibility
- bool isStatic
- bool isFinal
- bool hasGetter
- bool hasSetter

### Methods

- string toString(): Returns a string representation of the field, including visibility, static, and final modifiers, and optionally generates getter and setter methods.

## Method

Represents a method in a Java class.

### Members

- string name
- string visibility
- string returnType
- vector<string> paramTypes
- vector<string> paramNames
- bool isStatic

### Methods

- string toString(): Returns a string representation of the method, including visibility, static modifier, return type, and parameters.

## Constructor

Represents a constructor in a Java class.

### Members

- string name
- string visibility
- vector<string> paramTypes
- vector<string> paramNames

### Methods

- string toString(): Returns a string representation of the constructor, including visibility and parameters.

## File

Abstract base class for Java files.

### Members

- string testPath
- string name
- string package
- string visibility
- set<string> imports

### Methods

- virtual void process(): Pure virtual function to process the file.
- virtual void toFile(): Pure virtual function to write the file to disk.

## Enum

Represents a Java enum.

### Members

- vector<string> elements
- Methods
- string toString(): Returns a string representation of the enum.
- void toFile(): Writes the enum to a file.
- void process(): Processes the input file to extract enum elements.

## Class

Represents a Java class.

### Members

- string parent
- string interface
- vector<Field\*> fields
- vector<Method\*> methods
- vector<Constructor\*> constructors
- bool hasTextualRepresentation
- bool hasEqualityCheck
- bool hasHashCode

### Methods

- string toString(): Returns a string representation of the class.
- void toFile(): Writes the class to a file.
- void process(): Processes the input file to extract class details, fields, methods, and constructors.

## Interface

Represents a Java interface.

### Members

- string parentInterface
- vector<Field> Fields
- vector<Method> methods

### Methods

- string toString(): Returns a string representation of the interface (not implemented).
- void toFile(): Writes the interface to a file.
- void process(): Processes the input file to extract interface details (not implemented).

## Exception

Represents a Java exception class.

Members

- string parentException
- vector<Field> Fields
- vector<Method> methods
- vector<Constructor> constructors
  Methods
- string toString(): Returns a string representation of the exception (not implemented).
- void toFile(): Writes the exception to a file.
- void process(): Processes the input file to extract exception details (not implemented).

## Wrapper

Wrapper class to manage multiple Java files.

**This will be important in a later stage, it will help us overcome the limitations of a single StructureTest file**

### Members

- vector<File\*> files

### Methods

- void addFile(File\* file): Adds a file to the wrapper.
- void process(): Processes and writes all files managed by the wrapper.

# Helper Functions

### split

```cpp
vector<string> split(string s, string delimiter);
```

Splits a given string s using the specified delimiter and returns a vector of substrings.

### protlvl

```cpp
string protlvl(string line);
```

Determines the visibility level (public, protected, private) of a given line based on specific keywords.

### removeQuotes

```cpp
string removeQuotes(string line);
```

Removes all double quotes from a given string.

### concat

```cpp
string concat(vector<string> v, char delimiter);
```

Concatenates a vector of strings v into a single string with elements separated by the specified delimiter.

### createFile

```cpp
ofstream *createFile(string package, string name);
```

Creates a file for the specified package and name, replacing dots in the package name with slashes to create the appropriate directory structure. Returns a pointer to the created ofstream.

### createField

```cpp
Field *createField(fstream* infile, string line, File\* parent);
```

Creates a Field object based on the input line and parent file.

### createMethod

```cpp
Method *createMethod(fstream* infile, string line, File\* parent);
```

Creates a Method object based on the input line and parent file.

### createConstructor

```cpp
Constructor *createConstructor(fstream* infile, string line, File\* parent);
```

Creates a Constructor object based on the input line and parent file.

### getFile

```cpp
File *getFile(string inpth);
```

Returns a File object based on the input path. Returns nullptr on failure.

### typeMaker

```cpp
string typeMaker(string type, File\* parent);
```

Helper function to process type information based on the parent file.

### typeNameSeparator

```cpp
pair<string, string> typeNameSeparator(string line, File\* parent);
```

Separates a line into type and name components based on the parent file.
