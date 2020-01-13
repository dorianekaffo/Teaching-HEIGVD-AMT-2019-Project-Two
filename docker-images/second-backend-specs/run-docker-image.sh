#!/bin/bash
docker run --rm -v ~/.m2:/root/.m2 --net runtime_default amt2019/second-backend-specs 
