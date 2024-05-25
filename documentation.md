# Code Generator

## Overview

This C++ program is designed to generate Java class files from a specified input format. The input file describes various aspects of a Java class, including its fields, methods, constructors, and other properties. The program reads this input, processes the information, and generates a corresponding Java class file with the appropriate structure and content.

## File Description

### File Name

`main.cpp`

### Purpose

The purpose of this file is to automate the generation of Java class files based on a specific input format. This tool simplifies the creation of boilerplate code for Java classes, ensuring consistency and reducing manual coding efforts.

### Location

The input file path should be provided as a command-line argument when running the executable.

## Dependencies

- Linux (the program is dependent on the mkdir system command)

### Standard Libraries

- `<iostream>`
- `<fstream>`
- `<vector>`
- `<string>`
- `<cstdlib>`
- `<utility>`
- `<tuple>`

These standard libraries are used for basic input/output operations, file handling, string manipulation, and other fundamental tasks.

## Usage

To use this program, compile it with a C++ compiler and run the executable with the path to the input file as an argument. Or use the generate.sh file!

### Compilation

```sh
g++ -o code_generator main.cpp
```

### Command-Line Usage

```sh
./code_generator path/to/structure_test.java
```

## Functions / Classes

### `enum ProtectionLevel`

Defines the visibility levels for class members: (This should be rewritten)

- `Public`
- `Protected`
- `Private`
- `Default`

### `void testNull(size_t pos)`

Checks if a position is `string::npos` and exits the program if true.
Use in cases where the program can't continue when encountering this thing.

### `vector<string> split(string s, string delimiter)`

Splits a string `s` into a vector of substrings based on the specified `delimiter`.

### `ProtectionLevel protlvl(string line)`

Determines the protection level from a given line of text. (This should return a string instead)

### `string removeQuotes(string line)`

Removes quotes from a given string.

### `string typeMaker(string type)`

Processes a type string to convert it into a valid Java type declaration.

### `pair<string, string> typeNameSeparator(string line)`

Separates a type and name from a given line of text.

### `int createVar(fstream *infile, ofstream *outfile, string line)`

Creates a variable declaration in the output Java file based on the input line.
Also generates getter and setter functions.

### `int createMethod(fstream *infile, ofstream *outfile, string line)`

Creates a method declaration in the output Java file based on the input line.

### `int createConstructor(fstream *infile, ofstream *outfile, string line, string name)`

Creates a constructor declaration in the output Java file based on the input line and class name.

### `string concat(vector<string> v, char delimiter)`

Concatenates elements of a vector into a single string with the specified delimiter.

### `std::tuple<ofstream *, string, string> createFile(string line)`

Creates a new Java file based on the input line and returns a tuple containing the file stream, class name, and package name.

### `int main(int argc, char **args)`

The main function that drives the program. It reads the input file, processes each line, and generates the corresponding Java class file.

## Examples

To illustrate how the program works, here is an example usage scenario:

1. Prepare an input file `ExampleStructureTest.java` with the following content:

   ```
   ...
   CheckThat.theClass("my.package.MyClass")
   ...
   ```

2. Compile and run the program:

   ```sh
   g++ -o code_generator main.cpp
   ./code_generator ExampleStructureTest.java
   ```

3. The program will generate a Java class file `MyClass.java` in the appropriate package directory.

## Testing

To test the functionality of the program, prepare various input files with different class configurations and ensure that the generated Java files match the expected output.

### Running Tests

Use the following command to compile and run the program with all test input files:

```sh
./generate.sh test/
```

Verify the output Java files manually or write automated scripts to compare the generated files with expected results.

## Contributors

- **Original Author** - Provided the initial implementation and logic.

- **Typo Fixes**
  - Tóth Zalán

## License

```
MIT License
```

## Additional Information

For more information, refer to the project documentation or related files.

Feel free to customize the documentation further based on any additional information or requirements.
