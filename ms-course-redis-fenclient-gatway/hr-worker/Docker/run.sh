#!/bin/bash
docker run --rm -it --name hr-worker --hostname hr-worker -p 8083:8083 hr-worker:latest