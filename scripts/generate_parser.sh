#!/bin/bash

set -e

docker-compose build -q kekw-builder

docker-compose run kekw-builder -c "cat src/parser/Parser.java" > src/parser/Parser.java
