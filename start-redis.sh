#!/usr/bin/env bash

docker run \
  --name dedup-redis \
  -d \
  -v /var/dedup/redis:/data \
  -p 6379:6379 \
  redis:6.0.1 \
  redis-server --appendonly yes
