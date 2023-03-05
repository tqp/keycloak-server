#!/usr/bin/env bash

export IMAGE_TAG=keycloak-server
export IMAGE_VERSION=latest
echo "IMAGE: ${IMAGE_TAG}:${IMAGE_VERSION}"

## Detached (-d), interactive (-it), self-cleaning (--rm), on port 80 (-p)
docker run -it --rm -p 5000:5000/tcp --name keycloak-server ${IMAGE_TAG}:${IMAGE_VERSION}

