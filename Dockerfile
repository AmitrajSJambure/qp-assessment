FROM amazoncorretto:17

LABEL author="amitrajasj1@gmail.com"

WORKDIR /application

COPY target/Grocery-0.0.1-SNAPSHOT.jar /application/GroceryApp.jar
ENTRYPOINT ["java", "-jar", "GroceryApp.jar"]