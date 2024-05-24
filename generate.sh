#!/bin/bash

# Check if $1 is set
if [ -z "$1" ]; then
    echo "No directory provided using default. Usage: ./generate.sh <directory>"
    dir="."
else
    dir=$1
fi

# Compile main.cpp
g++ main.cpp -o generator.out

# Find files with "StructureTest.java" in their name
files=$(find "$dir" -name "*StructureTest.java")

# Call the binary with each file as a parameter
for file in $files; do
    ./generator.out "$file"
    if [ $? -ne 0 ]; then
        echo "Segfault occurred while processing $file"
    fi
done
# Delete evidence
rm generator.out

# Ask for cleanup
echo "Do you want to clean up the directory? (yes/(no))"
read -r response
if [ "$response" = "yes" ]; then
    ./clearup.sh
fi