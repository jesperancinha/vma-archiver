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

checkServiceByNameAndMessage jofisaes_vma_backend_img_1 'Started VmaServiceApplicationKt'
checkServiceByNameAndMessage jofisaes_vma_backend_img_2 'Started VmaServiceApplicationKt'
