FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 80
COPY target/client-1.0.war app.war
RUN sh -c 'touch /app.war'
ENTRYPOINT java  -Djava.security.egd=file:/dev/./urandom -DPAGE_SIZE=10  -jar app.war