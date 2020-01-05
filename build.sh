#!/bin/bash
cd first-backend
mvn clean install package
cd ../second-backend
mvn clean install package
