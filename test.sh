#!/bin/bash

# This script want 1 filename as argument then calls check.cmd with the filename and the package name

# Check if the filename is provided
if [ -z "$1" ]; then
    echo "No filename provided"
    exit 1
fi

# Check if the filename is a java file
if [[ "$1" != *.java ]]; then
    echo "Not a java file"
    exit 1
fi

# Create variable for the package name
package=""

# Convert the filename to package name, remove .java and replace / with .
package=${1%.java}
package=$(echo $package | sed 's/\//./g')

# Call check.cmd with the filename and the package name
./check.cmd "$1" "$package"
