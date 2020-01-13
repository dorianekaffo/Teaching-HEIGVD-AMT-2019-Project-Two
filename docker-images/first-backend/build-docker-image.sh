#!/bin/bash

mkdir tmp

mvn clean package -f ../../microservices/first-backend/pom.xml
cp ../../microservices/first-backend/target/first-backend-*.jar ./tmp/

docker build -t amt2019/first-backend .
