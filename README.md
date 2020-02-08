# Microservice, Spring Boot, Spring Security,  JPA, Cassandra, PostgreSQL and Docker  

## Prerequisites:
* Docker 
* JDK 1.8 
* Maven 3.*

## Install and run the project 
1. download/clone the project 
2. Build the project using following maven command from project root folder where pom.xml file place.
  * `mvn clean install -Pdocker`
  
3. Run the docker-compose using the following command   
  * `docker-compose up -d` 
  
4. Open Url [http://localhost:8080/](http://localhost:8080/) in a browser 


5. you can use following user account to login the application.
   * Username = ahmed, password = secret, Role = Super Admin