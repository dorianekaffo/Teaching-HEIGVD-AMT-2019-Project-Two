#!/bin/bash
# docker-compose -f docker/runtime/docker-compose.yml up
cd first-backend-specs
mvn test
cd ../second-backend-specs
mvn test
