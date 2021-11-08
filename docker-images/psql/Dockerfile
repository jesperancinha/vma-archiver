FROM postgres:11.5

COPY postgresql.conf /etc/postgresql/postgresql.conf

COPY init-scripts/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/create-multiple-postgresql-databases.sh

RUN apt update

RUN apt-get install python3-pip python3-dev libpq-dev -y

RUN pip3 install --upgrade pip

RUN pip install patroni

RUN pip install python-etcd

RUN pip install psycopg2

RUN mkdir -p /data/patroni

RUN chown postgres:postgres /data/patroni

RUN chmod 700 /data/patroni

COPY patroni.yml /etc/patroni.yml

EXPOSE 5432

