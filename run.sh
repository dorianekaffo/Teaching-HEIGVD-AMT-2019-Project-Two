#!/bin/bash
cd ./docker-topologies/runtime
docker-compose up --remove-orphans --force-recreate
