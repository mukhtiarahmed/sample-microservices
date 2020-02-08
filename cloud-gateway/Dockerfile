FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080
COPY target/auth-service-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom  -jar app.jar

