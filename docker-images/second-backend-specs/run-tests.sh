#!/bin/sh

cd specs
mvn clean test -Dio.openaffect.server.url=http://second-backend:8080 -Djava.security.egd=file:/dev/./urandom
