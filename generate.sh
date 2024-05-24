#!/bin/bash

# Compile main.cpp
g++ main.cpp -o generator.out

# Find files with "StructureTest.java" in their name
files=$(find . -name "*StructureTest.java")

# Call the binary with each file as a parameter
for file in $files; do
    ./generator.out "$file"
done
# Delete evidence
rm generator.out