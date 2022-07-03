#!/bin/bash
docker run --rm -it --name hr-config-server --hostname hr-config-server -p 8888:8888 hr-config-server:latest