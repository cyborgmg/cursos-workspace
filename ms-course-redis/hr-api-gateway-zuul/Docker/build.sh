#!/bin/bash
rm -rf *.jar
cp -rf ../target/*.jar ./
docker build -f Dockerfile -t hr-api-gateway-zuul:latest .
rm -rf *.jar