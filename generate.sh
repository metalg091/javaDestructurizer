#!/bin/bash

# Check if $1 is set
if [ -z "$1" ]; then
    echo "No directory provided using default. Usage: ./generate.sh <directory>"
    dir="."
else
    # manual flag
    if [[ "manual" == "$1" || "-m" == "$1" || "m" == "$1" ]]; then
        g++ main.cpp -o generator.out
        exit 0
    else
        dir=$1
    fi
fi

# Compile main.cpp
g++ main.cpp -o generator.out

# Check if compilation was successful
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# Find files with "StructureTest.java" in their name
files=$(find "$dir" -name "*StructureTest.java")

# Call the binary with each file as a parameter
for file in $files; do
    ./generator.out "$file"
    if [ $? -ne 0 ]; then
        echo "Error occured $file"
    fi
done
# Delete evidence
rm generator.out

# Ask for cleanup
echo "Do you want to clean up the directory? (yes/No)"
read -r response
if [ "$response" = "yes" ]; then
    ./clearup.sh
fi