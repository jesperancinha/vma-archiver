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
          echo "Failed after $counter tries! Cypress tests mail fail!!"
          exit
      fi
    done
    counter=$((counter+1))
    echo "Succeeded $name Service after $counter tries!"
}

checkServiceByNameAndMessage jofisaes_vma_zookeeper 'binding to port'
checkServiceByNameAndMessage jofisaes_vma_broker 'Created metrics reporter topic'
checkServiceByNameAndMessage jofisaes_schemaregistry 'started, listening for requests'
