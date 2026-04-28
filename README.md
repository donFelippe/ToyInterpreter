# Toy Language Interpreter

## Short CV-Style Summary
I built a small Java interpreter for a toy language for the Advanced Programming Course. It runs programs using a stack-based execution model and keeps program state in custom data structures.

## What I Built
- Expressions and statements (math, logic, if, while, print, assignments).
- A heap for reference values with new, read, and write operations.
- File handling with open, read, and close statements.
- A fork statement to run a new thread with shared heap and output.
- A simple text menu that runs several example programs.
- Logging of program execution to a file.

## Where Things Are
- src/Model: program state, expressions, statements, types, values, and custom ADTs.
- src/Controller: step execution and program management.
- src/Repository: storage and logging.
- src/View: menu and example programs.
