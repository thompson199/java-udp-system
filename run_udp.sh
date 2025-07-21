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

javac src/UDPSystem.java

# Run UDP system with [base|super] and <port_number>
java src.UDPSystem $1 $2
