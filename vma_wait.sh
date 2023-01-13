#!/bin/bash
GITHUB_RUN_ID=${GITHUB_RUN_ID:-123}

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose -p "${GITHUB_RUN_ID}" logs "$name" > "logs"
    string=$(cat logs)
    counter=0
    echo "Project $GITHUB_RUN_ID"
    echo -n "Starting service $name "
    while [[ "$string" != *"$message"* ]]
    do
      echo -e -n "\e[93m-\e[39m"
      docker-compose -p "${GITHUB_RUN_ID}" logs "$name" > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo -e "\e[91mFailed after $counter tries! Cypress tests may fail!!\e[39m"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo -e "\e[92m Succeeded starting $name Service after $counter tries!\e[39m"
}

checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server ReadWrite/postgres1 is UP,'
checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server Read/postgres3 is UP,'
checkServiceByNameAndMessage jofisaes-vma-backend-img-1 'Started VmaServiceApplicationKt'
checkServiceByNameAndMessage jofisaes-vma-backend-img-2 'Started VmaServiceApplicationKt'
checkServiceByNameAndMessage jofisaes-vma-nginx-lb 'GET /api/vma/registry HTTP/1.1" 200'
checkServiceByNameAndMessage jofisaes-vma-postgres-1 'I am (postgresql1)'
checkServiceByNameAndMessage jofisaes-vma-postgres-2 'I am (postgresql2)'
checkServiceByNameAndMessage jofisaes-vma-postgres-3 'I am (postgresql3)'
checkServiceByNameAndMessage jofisaes-schemaregistry 'Server started, listening for requests...'
checkServiceByNameAndMessage jofisaes-vma-haproxy-lb 'Server Read/postgres2 is UP,'
