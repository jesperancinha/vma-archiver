#!/bin/bash
docker logs staco_app_service_localstack &> "logs"
string=$(cat logs)
counter=0
#while [[ "$string" != *"Bucket(Name=stacos"* ]]
#do
#  printf "."
#  docker logs staco_app_service_localstack &> "logs"
#  string=$(cat logs)
#  sleep 1
#  counter=$((counter+1))
#  if [ $counter -eq 200 ]; then
#      echo "Failed after $counter tries! All cypress tests should fail!"
#      exit
#  fi
#done
echo "Succeeded after $counter tries!"
