#!/bin/bash
docker run --rm -it --name hr-api-gateway-zuul-2 --hostname hr-api-gateway-zuul-2 --env-file ./env-2.list -p 8766:8766 hr-api-gateway-zuul:latest