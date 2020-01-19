#!/bin/bash
cd ./docker-topologies/runtime
docker-compose up -d --remove-orphans --force-recreate
sleep 30
cd ../../docker-images/first-backend-specs/
./run-docker-image.sh
cd ../second-backend-specs/
./run-docker-image.sh
cd ../../docker-topologies/runtime
docker-compose down
