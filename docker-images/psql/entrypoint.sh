#!/bin/bash

cd /var/lib/postgresql || exit

sed -i 's/jofisaes-vma-etcd/'"$VMA_ETCD_IP"'/g' /var/lib/postgresql/patroni.yml
sed -i 's/${POSTGRES_MAIN_IP}/'"$(getent hosts "$POSTGRES_MAIN_IP" | cut -d' ' -f1)"'/g' /var/lib//postgresql/patroni.yml
sed -i 's/${POSTGRES_SECOND_IP}/'"$(getent hosts "$POSTGRES_SECOND_IP" | cut -d' ' -f1)"'/g' /var/lib//postgresql/patroni.yml
sed -i 's/${POSTGRES_THIRD_IP}/'"$(getent hosts "$POSTGRES_THIRD_IP" | cut -d' ' -f1)"'/g' /var/lib//postgresql/patroni.yml
sed -i 's/${POSTGRES_NUMBER}/'"$POSTGRES_NUMBER"'/g' /var/lib/postgresql/patroni.yml

sleep "${POSTGRES_NUMBER}"
sleep "${POSTGRES_NUMBER}"
/root/update-schema.sh &
su - postgres -c "/var/lib/postgresql/command.sh"

tail -f /dev/null
