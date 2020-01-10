#!/bin/bash

mkdir tmp
rm -fr tmp/*

cp -r ../../microservices/first-backend-specs/* ./tmp/

docker build -t openaffect/first-backend-specs .
