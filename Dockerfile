FROM openjdk:17-jdk-alpine
COPY target/*.jar main.jar
ENTRYPOINT ["java","-jar","/main.jar"]
