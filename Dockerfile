FROM openjdk:21-jdk
MAINTAINER "manthanpatidar@gmail.com"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]