FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080
COPY target/cloud-gateway-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom  -DEUREKA_URI=${EUREKA_URI} -jar app.jar

