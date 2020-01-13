#!/bin/sh

cd specs
mvn clean test -Dio.openaffect.server.url=http://first-backed:8080 -Djava.security.egd=file:/dev/./urandom
