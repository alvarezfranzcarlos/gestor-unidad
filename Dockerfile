FROM amazoncorretto:17 AS build
WORKDIR /app
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
