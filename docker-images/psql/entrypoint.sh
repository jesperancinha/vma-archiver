#!/bin/bash

cd /var/lib/postgresql || exit

sed -i 's/${VMA_ETCD_IP}/'"$VMA_ETCD_IP"'/g' /var/lib/postgresql/patroni.yml
sed -i 's/${POSTGRES_MAIN_IP}/'"$POSTGRES_MAIN_IP"'/g' /var/lib//postgresql/patroni.yml
sed -i 's/${POSTGRES_SECOND_IP}/'"$POSTGRES_SECOND_IP"'/g' /var/lib/postgresql/patroni.yml
sed -i 's/${POSTGRES_THIRD_IP}/'"$POSTGRES_THIRD_IP"'/g' /var/lib/postgresql/patroni.yml
sed -i 's/${POSTGRES_NUMBER}/'"$POSTGRES_NUMBER"'/g' /var/lib/postgresql/patroni.yml

sleep "${POSTGRES_NUMBER}"
sleep "${POSTGRES_NUMBER}"
/root/update-schema.sh &
su - postgres -c "/var/lib/postgresql/command.sh"

tail -f /dev/null
