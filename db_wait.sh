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

checkServiceByNameAndMessage jofisaes-vma-postgres-1 'I am (postgresql1)'
checkServiceByNameAndMessage jofisaes-vma-postgres-2 'I am (postgresql2)'
checkServiceByNameAndMessage jofisaes-vma-postgres-3 'I am (postgresql3)'
