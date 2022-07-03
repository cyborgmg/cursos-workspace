#!/bin/bash
rm -rf *.jar
cp -rf ../target/*.jar ./
docker build -f Dockerfile -t hr-worker-gw:latest .
rm -rf *.jar