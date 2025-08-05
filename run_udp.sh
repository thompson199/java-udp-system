#!/bin/sh

# Check the number of provided arguments
if [ $# -ne 2 ]; then
    echo "Usage: run_udp.sh [base|super] <port_number>"
    exit 1
fi

# Validate first argument is either "base" or "super"
if [ "$1" != "base" ] && [ "$1" != "super" ]; then
    echo "Error: First argument must be either 'base' or 'super'"
    echo "Usage: run_udp.sh [base|super] <port_number>"
    exit 1
fi

# Compile all Java files (top level, UDP System and sub-packages like common and nodes)
javac -cp src/main/java src/main/java/UDPSystem.java src/main/java/common/*.java src/main/java/nodes/*.java

# Run with correct classpath
java -cp src/main/java UDPSystem $1 $2
