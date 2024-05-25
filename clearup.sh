#!/bin/bash

# Loop through each directory in the specified directory
for dir in ./*; do
    # Check if the current item is a directory and its name is not "test"
    if [[ -d "$dir" && "$(basename "$dir")" != "test" ]]; then
        # Remove the directory
        rm -rf "$dir"
    fi
done

# Remove all .class files
find . -name "*.class" | while read -r file; do
    rm $file
done