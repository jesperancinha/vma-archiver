#!/bin/bash
export PGPASSWORD='admin'
status=1

while [ $status -ne 0 ]; do
  sleep 1
  psql -U postgres -p 5000 -h jofisaes-vma-haproxy-lb -v ON_ERROR_STOP=1 <<-EOSQL
                                         	    CREATE USER vma;
                                         	    CREATE DATABASE vma;
                                         	    GRANT ALL PRIVILEGES ON DATABASE vma TO postgres;
EOSQL
  psql -U postgres vma -p 5000 -h jofisaes-vma-haproxy-lb -c "SELECT 1"
  status=$?
done
