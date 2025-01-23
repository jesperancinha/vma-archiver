#!/bin/sh

sed -i 's/${VMA_BACKEND_1}/'"$(getent hosts jofisaes-vma-backend-img-1 | cut -d' ' -f1)"'/g' /etc/nginx/nginx.conf
sed -i 's/${VMA_BACKEND_2}/'"$(getent hosts jofisaes-vma-backend-img-2 | cut -d' ' -f1)"'/g' /etc/nginx/nginx.conf
sed -i 's/${VMA_SOCKETS_1}/'"$(getent hosts jofisaes-vma-websockets-1 | cut -d' ' -f1)"'/g' /etc/nginx/nginx.conf
sed -i 's/${VMA_SOCKETS_2}/'"$(getent hosts jofisaes-vma-websockets-2 | cut -d' ' -f1)"'/g' /etc/nginx/nginx.conf

nginx -t

nginx

tail -f /dev/null
