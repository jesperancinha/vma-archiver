#!/bin/bash
cd /var/lib/postgresql || exit
#systemctl daemon-reload
#systemctl start patroni
#systemctl start postgresql
#systemctl status patroni
/usr/local/bin/patroni /var/lib/postgresql/patroni.yml
