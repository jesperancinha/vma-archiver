FROM eclipse-temurin:21-alpine

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/vma-service-websockets.jar /root/vma-service-websockets.jar

ENTRYPOINT ["/root/entrypoint.sh"]

EXPOSE 8081
