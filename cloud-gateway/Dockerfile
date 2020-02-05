FROM openjdk:8-jdk-alpine
MAINTAINER mukhtiar
COPY target/cloud-gateway-1.0.jar /opt/lib/


ENTRYPOINT exec /usr/bin/java $JAVA_OPTS  -jar /opt/lib/cloud-gateway-1.0.jar

EXPOSE 4000