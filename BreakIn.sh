#!/bin/bash

# This script removes the package line of the structure test files
# This is necessary because the package line will not allow us to test the generated files

# find all files with "StructureTest.java" in their name
files=$(find . -name "*StructureTest.java")

# remove the package line from each file if they have one
for file in $files; do
    sed -i '/^package/d' "$file"
done

# add the package line to the files
for file in $files; do
    # remove everything after the last / 
    path=${file%/*}
    # replace / with .
    path=$(echo $path | sed 's/\//./g')
    # remove 2 character from the beginning
    path=${path:2}
    echo  $path
    echo "package $path;" | cat - "$file" > temp && mv temp "$file"
done