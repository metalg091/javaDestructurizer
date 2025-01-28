# Java Destructurizer

This is a tool that converts Java structure tests (given on Java exams in ELTE) to the skeleton of the Java Project.
The generated code *should* be able to pass the test suite immediately after generation!!!

It:

- creates the files
- sets packages
- creates classes, interfaces
- sets inheritance, implements
- properties with auto setter/getter generation
- creates the empty methods
- creates necessary test files
- and so on...

# Usage:
Place the main.cpp and the generate.sh in the same folder.
Place them in a director so that all the structure tests are in the same or a sub-directory.
Then just start the generat.sh and enjoy. For more manual usage compile the program, and give the test file as a parameter!

# PLEASE REPORT ANY UNCOVERED CASES!!!

Submit them through github as an issue it makes it easier to handle!
Also, attach the failing test file!

# Contributing:

This Project is basically abandoned, I will respond to any activity, but i will not develop the project further.

The main part of the project is the spagetti code found in main.cpp, I would suggest rewriting the whole thing from source, I really didn't understand the language when i wrote it.

If you want to fix it up, just open a pull request and i will check it and merge.
