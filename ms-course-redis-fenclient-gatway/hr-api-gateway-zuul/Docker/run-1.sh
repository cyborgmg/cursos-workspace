#!/bin/bash
docker run --rm -it --name hr-api-gateway-zuul-1 --hostname hr-api-gateway-zuul-1 --env-file ./env-1.list -p 8765:8765 hr-api-gateway-zuul:latest