#!/bin/bash
mkdir tmp

mvn clean package -f ../../microservices/second-backend/pom.xml
cp ../../microservices/second-backend/target/second-backend-*.jar ./tmp/

docker build -t amt2019/second-backend .
