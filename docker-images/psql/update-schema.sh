#!/bin/bash
sleep 10
export PGPASSWORD='admin'
psql -U postgres -p 5000 -h 192.168.0.15 -v ON_ERROR_STOP=1 <<-EOSQL
                                         	    CREATE USER vma;
                                         	    CREATE DATABASE vma;
                                         	    GRANT ALL PRIVILEGES ON DATABASE vma TO postgres;
EOSQL
