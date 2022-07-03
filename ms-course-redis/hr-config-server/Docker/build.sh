#!/bin/bash
rm -rf *.jar
cp -rf ../target/*.jar ./
docker build -f Dockerfile -t hr-config-server:latest .
rm -rf *.jar