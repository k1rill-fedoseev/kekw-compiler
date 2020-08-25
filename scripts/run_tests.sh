#!/bin/bash

set -e

if [[ "$1" = "verbose" ]]; then
  docker-compose build kekw
else
  docker-compose build -q kekw
fi

for TEST_NAME in ./kekw-examples/*.kekw; do
    echo "Running $TEST_NAME"
    cat $TEST_NAME | docker-compose run kekw || true
    echo "Finished $TEST_NAME"
    echo
    echo
done
