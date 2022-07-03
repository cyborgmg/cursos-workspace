#!/bin/bash
docker run --rm -it --name hr-eureka-server --hostname hr-eureka-server -p 8761:8761 hr-eureka-server:latest