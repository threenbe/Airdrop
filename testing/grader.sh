#!/bin/bash
# This script is for grading.
# Put your submission to submission folder and run the script, see the output.
rm -f "./src/main/java/Assignment2.java"

find "submissions"  -name "Assignment*.java" | while read line; do
        trap "echo Exited!; exit;" SIGINT SIGTERM

        IFS='_' read -ra arr <<<"${line}"

        cp "$line" "./src/main/java/Assignment2.java"

        mvn test

        rm -f "./src/main/java/Assignment2.java"
done
