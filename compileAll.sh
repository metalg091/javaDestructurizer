# Compile all java files in the current directory and subdirectories, excluding the test directory and any files ending with "Test.java"

find "." -name "*.java" -not -path "*/test/*" -not -name "*Test.java" -exec javac {} \;
