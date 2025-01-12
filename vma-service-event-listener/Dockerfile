FROM eclipse-temurin:21-alpine

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/vma-service-event-listener-*.jar /root/vma-service-event-listener.jar

ENTRYPOINT ["/root/entrypoint.sh"]
