#!/bin/bash
docker run --rm -it --name hr-worker-gw --hostname hr-worker-gw -p 8089:8089 hr-worker-gw:latest