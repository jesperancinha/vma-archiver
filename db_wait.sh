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
          echo "Failed after $counter tries! All cypress tests should fail!"
          exit
      fi
    done
    counter=$((counter+1))
    echo "Succeeded $name Service after $counter tries!"
}

checkServiceByNameAndMessage jofisaes_vma_postgres_1 'I am (postgresql1)'
checkServiceByNameAndMessage jofisaes_vma_postgres_2 'I am (postgresql2)'
checkServiceByNameAndMessage jofisaes_vma_postgres_3 'I am (postgresql3)'
