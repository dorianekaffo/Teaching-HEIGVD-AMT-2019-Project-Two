#!/bin/sh

cd specs
mvn clean test -Dch.heigvd.amt.p2.first-backend.server.url=http://first-backend:8080 -Dch.heigvd.amt.p2.second-backend.server.url=http://second-backend:8080 -Djava.security.egd=file:/dev/./urandom
