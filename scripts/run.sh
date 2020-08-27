#!/bin/bash

set -e

if [[ "$2" = "verbose" ]]; then
  docker-compose build kekw
else
  docker-compose build -q kekw
fi

cat ${1:-kekw-examples/prog.kekw} | docker-compose run kekw
