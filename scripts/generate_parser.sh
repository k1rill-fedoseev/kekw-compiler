#!/bin/bash

set -e

docker-compose build -q kekw-builder

docker-compose run kekw-builder -c "cat src/Parser.java" > src/Parser.java
