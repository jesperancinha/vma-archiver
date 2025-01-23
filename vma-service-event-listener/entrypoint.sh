#!/usr/bin/env sh

java -jar -Dapplication.schema.host=${VMA_SCHEMA_REGISTRY} -Dapplication.postgres.host=jofisaes-vma-haproxy-lb -Dspring.profiles.active=docker -Dserver.port="${VMA_PORT}" vma-service-event-listener.jar
