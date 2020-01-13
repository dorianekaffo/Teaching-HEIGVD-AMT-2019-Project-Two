#!/bin/bash
docker --version
docker-compose --version
cd docker-images/first-backend/
./build-docker-image.sh
cd ../second-backend/
./build-docker-image.sh
cd ../first-backend-specs/
./build-docker-image.sh
cd ../second-backend-specs/
./build-docker-image.sh
