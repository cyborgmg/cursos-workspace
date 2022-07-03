#!/bin/bash
rm -rf *.jar
cp -rf ../target/*.jar ./
docker build -f Dockerfile -t hr-oauth:latest .
rm -rf *.jar