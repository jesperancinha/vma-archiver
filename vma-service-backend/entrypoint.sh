#!/usr/bin/env bash

java -jar -Dapplication.schema.host=${VMA_SCHEMA_REGISTRY} -Dapplication.postgres.host=${VMA_HA_PROXY_IP} -Dspring.profiles.active=${VMA_BACKEND_PROFILE} -Dserver.port="${VMA_PORT}" vma-service-backend.jar