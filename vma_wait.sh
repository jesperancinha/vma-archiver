#!/bin/bash
docker logs jofisaes_vma_backend_img_1 &> "logs"
string=$(cat logs)
counter=0
while [[ "$string" != *"Started VmaServiceApplicationKt"* ]]
do
  printf "."
  docker logs jofisaes_vma_backend_img_1 &> "logs"
  string=$(cat logs)
  sleep 1
  counter=$((counter+1))
  if [ $counter -eq 200 ]; then
      echo "Failed after $counter tries! All cypress tests should fail!"
      exit
  fi
done
counter=$((counter+1))
echo "Succeeded VMA BE 1 Service after $counter tries!"

docker logs jofisaes_vma_backend_img_2 &> "logs"
string=$(cat logs)
counter=0
while [[ "$string" != *"Started VmaServiceApplicationKt"* ]]
do
  printf "."
  docker logs jofisaes_vma_backend_img_2 &> "logs"
  string=$(cat logs)
  sleep 1
  counter=$((counter+1))
  if [ $counter -eq 200 ]; then
      echo "Failed after $counter tries! All cypress tests should fail!"
      exit
  fi
done
counter=$((counter+1))
echo "Succeeded VMA BE 2 Service after $counter tries!"
