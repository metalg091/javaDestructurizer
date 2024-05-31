#!/bin/bash

# Ask for mode (All, Class) Class is the default

echo "Do you want to clear all files or just the class files? (a/C)"

read -r response

if [ "$response" = "a" ]; then
    # Loop through each directory in the specified directory
    for dir in ./*; do
        # Check if the current item is a directory and its name is not "test"
        if [[ -d "$dir" && "$(basename "$dir")" != "test" ]]; then
            # Remove the directory
            rm -rf "$dir"
        fi
    done
fi

# Remove all .class files
find . -name "*.class" | while read -r file; do
    rm $file
done