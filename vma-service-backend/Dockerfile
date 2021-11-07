FROM openjdk:17-slim-buster

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/vma-service-backend-*.jar /root/vma-service-backend.jar

ENTRYPOINT ["/root/entrypoint.sh"]

EXPOSE 8085
