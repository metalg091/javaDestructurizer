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
# check for g++ v6.3.0
if [[ $(ls /usr/bin/g++* | grep "g++-6") == "" ]]; then
    echo "**WARNING** g++-6 not found, using default g++"
    g++ main.cpp -o generator.out # -pedantic -Wall -Wextra -Wcast-align -Wcast-qual -Wctor-dtor-privacy -Wdisabled-optimization -Wformat=2 -Winit-self -Wlogical-op -Wmissing-declarations -Wmissing-include-dirs -Wnoexcept -Wold-style-cast -Woverloaded-virtual -Wredundant-decls -Wshadow -Wsign-conversion -Wsign-promo -Wstrict-null-sentinel -Wstrict-overflow=5 -Wswitch-default -Wundef -Wno-unused
else
    g++-6 main.cpp -o generator.out # -pedantic -Wall -Wextra -Wcast-align -Wcast-qual -Wctor-dtor-privacy -Wdisabled-optimization -Wformat=2 -Winit-self -Wlogical-op -Wmissing-declarations -Wmissing-include-dirs -Wnoexcept -Wold-style-cast -Woverloaded-virtual -Wredundant-decls -Wshadow -Wsign-conversion -Wsign-promo -Wstrict-null-sentinel -Wstrict-overflow=5 -Wswitch-default -Wundef -Wno-unused

fi
# test flags
# -pedantic -Wall -Wextra -Wcast-align -Wcast-qual -Wctor-dtor-privacy -Wdisabled-optimization -Wformat=2 -Winit-self -Wlogical-op -Wmissing-declarations -Wmissing-include-dirs -Wnoexcept -Wold-style-cast -Woverloaded-virtual -Wredundant-decls -Wshadow -Wsign-conversion -Wsign-promo -Wstrict-null-sentinel -Wstrict-overflow=5 -Wswitch-default -Wundef -Wno-unused

# Check if compilation was successful
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

# If $dir is a file run test for only that file

if [ -f "$dir" ]; then
    # Check if the file is a java file
    if [[ "$dir" != *.java ]]; then
        echo "Not a java file"
        exit 1
    fi
    files=$dir
else
    # Find files with "StructureTest.java" in their name
    files=$(find "$dir" -name "*StructureTest.java")
    suite=$(find "$dir" -name "*Suite.java")
fi

# Call the binary with each file as a parameter
echo "################################################################################"
./generator.out $files $suite
echo "################################################################################"

# Delete evidence
rm generator.out

# Ask for Test
echo "Do you want to run the tests? (y/N)"
read -r response
if [ "$response" = "y" ]; then
    # create an array for compilation errors
    errors=()
    # Compile the files for testing with javac excluding the test directory
    find "." -name "*.java" -not -path "*/test/*" -exec javac {} \;
    # Check if compilation was successful and add to errors array if not
    if [ $? -ne 0 ]; then
        errors+=("$file")
    fi
    # Print errors
    if [ ${#errors[@]} -ne 0 ]; then
        echo "Errors occured while compiling the following files:"
        for error in "${errors[@]}"; do
            echo "$error"
        done
    fi
    # Run all Structure tests
    # They are run by ./check.cmd path/to/StructureTest.java path.to.StructureTest
    for file in $files; do
        # replace / with . and remove .java
        package=${file%.java}
        package=$(echo $package | sed 's/\//./g')
        read -r canIgo
        ./check.cmd "$file" "$package"
    done
fi

# Ask for cleanup
echo "Do you want to clean up the directory? (yes/No)"
read -r response
if [ "$response" = "yes" ]; then
    ./clearup.sh
fi
