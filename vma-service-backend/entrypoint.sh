#!/usr/bin/env bash

java -jar -Dserver.port="${VMA_PORT}" vma-service-event-listener.jar
