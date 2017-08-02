#!/bin/bash

set -e -u

[[ -d $PWD/maven && ! -d $HOME/.m2 ]] && ln -s $PWD/maven $HOME/.m2

./mvnw clean install
