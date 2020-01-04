#!/bin/bash
docker-compose -f docker/runtime/docker-compose.yml up
cd first-backend-specs
mvn goal test
cd ../second-backend-specs
mvn goal test
