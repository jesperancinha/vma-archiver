#!/usr/bin/env bash

java -jar -Dserver.port="${VMA_PORT}" vma-service-backend.jar
