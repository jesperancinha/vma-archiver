FROM nginx:1.27.5

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN mv /etc/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf.disabled

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY entrypoint.sh /usr/local/bin/

COPY dist/vma-gui /usr/share/nginx/html

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8080
