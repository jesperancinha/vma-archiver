FROM postgres:15.2

COPY init-scripts/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/create-multiple-postgresql-databases.sh

RUN apt update

RUN apt-get install python3-pip python3-dev libpq-dev -y

RUN pip3 install --upgrade pip

RUN pip install patroni

RUN pip install python-etcd

RUN pip install psycopg2

RUN apt update

RUN apt-get install systemd -y

COPY postgresql.conf /etc/postgresql/postgresql.conf

COPY patroni.yml /var/lib/postgresql/patroni.yml

COPY patroni.service /etc/systemd/system/patroni.service

COPY command.sh /var/lib/postgresql

COPY entrypoint.sh /root

COPY update-schema.sh /root

RUN mkdir -p /data

RUN chmod 700 /data

RUN chown postgres:postgres /data

WORKDIR /root

ENTRYPOINT /root/entrypoint.sh

EXPOSE 5432

