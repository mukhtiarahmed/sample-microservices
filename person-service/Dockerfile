FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 9876
COPY target/person-service-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT java  -Djava.security.egd=file:/dev/./urandom -DPAGE_SIZE=10  -DDATABASE_URL=jdbc:postgresql://person_db:5432/person_local -DDATABASE_USER=postgres  -DDATABASE_SEC=docker -jar app.jar

