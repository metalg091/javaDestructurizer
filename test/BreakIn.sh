#!/bin/bash

# This script removes the package line of the structure test files
# This is necessary because the package line will not allow us to test the generated files

# find all files with "StructureTest.java" in their name
files=$(find . -name "*StructureTest.java")

# remove the package line from each file if they have one
for file in $files; do
    sed -i '/^package/d' "$file"
done
