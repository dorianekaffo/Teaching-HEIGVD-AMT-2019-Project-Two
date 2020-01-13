#!/bin/bash
./run.sh
cd ./docker-images/first-backend-specs/
./run-docker-image.sh
cd ../second-backend-specs/
./run-docker-image.sh
cd ../../docker-topologies/runtime
docker-compose down
