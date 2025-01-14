FROM openjdk:17-jdk-alpine
LABEL authors="Amitraj Jambure"
ARG JAR_FILE=target/*.jar
COPY ./target/Grocery-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]