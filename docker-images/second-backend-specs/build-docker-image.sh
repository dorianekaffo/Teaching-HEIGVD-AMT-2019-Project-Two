#!/bin/bash

mkdir tmp
rm -fr tmp/*

cp -r ../../microservices/second-backend-specs/* ./tmp/

docker build -t amt2019/second-backend-specs .
