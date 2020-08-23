#!/bin/bash

set -e

docker-compose build -q kekw

for TEST_NAME in ./kekw-examples/*.kekw; do
    echo "Running $TEST_NAME"
    cat $TEST_NAME | docker-compose run kekw || true
    echo "Finished $TEST_NAME"
    echo
    echo
done
