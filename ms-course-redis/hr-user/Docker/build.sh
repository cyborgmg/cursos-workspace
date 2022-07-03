#!/bin/bash
rm -rf *.jar
cp -rf ../target/*.jar ./
docker build -f Dockerfile -t hr-user:latest .
rm -rf *.jar