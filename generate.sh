#!/bin/bash

# Compile main.cpp
g++ main.cpp -o generator.out

# Find files with "StructureTest.java" in their name
files=$(find . -name "*StructureTest.java")

# Call the binary with each file as a parameter
for file in $files; do
    ./generator.out "$file"
    if [ $? -ne 0 ]; then
        echo "Segfault occurred while processing $file"
    fi
done
# Delete evidence
rm generator.out