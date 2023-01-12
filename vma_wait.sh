#!/bin/bash

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker logs "$name" &> "logs"
    string=$(cat logs)
    counter=0
    while [[ "$string" != *"$message"* ]]
    do
      printf "."
      docker logs "$name" &> "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo "Failed after $counter tries! Cypress tests may fail!!"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo "Succeeded $name Service after $counter tries!"
}

checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server ReadWrite/postgres1 is UP,'
checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server Read/postgres3 is UP,'
checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server Read/postgres2 is UP,'
checkServiceByNameAndMessage jofisaes-vma-backend-img-1 'Started VmaServiceApplicationKt'
checkServiceByNameAndMessage jofisaes-vma-backend-img-2 'Started VmaServiceApplicationKt'
checkServiceByNameAndMessage jofisaes-vma-nginx-lb 'GET /api/vma/registry HTTP/1.1" 200'
checkServiceByNameAndMessage jofisaes-vma-postgres-1 'I am (postgresql1)'
checkServiceByNameAndMessage jofisaes-vma-postgres-2 'I am (postgresql2)'
checkServiceByNameAndMessage jofisaes-vma-postgres-3 'I am (postgresql3)'
checkServiceByNameAndMessage jofisaes-schemaregistry 'Server started, listening for requests...'
