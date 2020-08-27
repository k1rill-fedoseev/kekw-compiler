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
