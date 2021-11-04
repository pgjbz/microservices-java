# Microservices

This repository is based on Udemy course: https://www.udemy.com/course/microsservicos-java-spring-cloud


### Build project and run

To build this project is necessary Maven 3 and JDK 11, Docker and Docker compose.

To build project run that command:

```bash
mvn clean package -DskipTests
```

When build this project are generated 7 docker images, one per service, to run all projects
simple run 
```bash
docker-compose up -d
```