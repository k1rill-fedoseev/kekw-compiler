#!/bin/bash

set -e

docker-compose build -q kekw

cat ${1:-kekw-examples/prog.kekw} | docker-compose run kekw
