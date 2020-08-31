## KEKW lang interpreter

![CI](https://github.com/k1rill-fedoseev/kekw-compiler/workflows/CI/badge.svg?branch=master)

## Language examples
KEKW programs examples are located in the `./kekw-examples`.

## Implementation details
Interpreter uses hand-written lexer which is based on the Java's `StreamTokenizer`.

Parser was generated using GNU Bison 3.7.1.
YACC file for generation with detailed grammar description can be found at `./src/parser/parser.y`.

## Run interpreter for some .kekw file
```shell script
./scripts/run.sh
./scripts/run.sh ./kekw-examples/prog.kekw  
```

To run all tests:
```shell script
./scripts/run_tests.sh
```

Both commands support `verbose` option for enhanced logging.


## Generate java class for parser
```shell script
./scripts/generate_parser.sh
```


## Extension of specification
### Comments
All symbols on the line after the symbol *#* will be ignored by the interpreter.
### String
String is any sequence of characters enclosed in double quotes.
### Print
Built-in function that takes one argument. 
The function prints the argument to the user terminal. 
If the argument is a *Literal*, it prints the value of that literal.
In any other case, it prints the representation of the lexem in AST.
