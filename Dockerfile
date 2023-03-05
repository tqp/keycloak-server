FROM openjdk:17-jdk-alpine
COPY target/*.jar main.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/main.jar"]
