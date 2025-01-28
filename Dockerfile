FROM amazoncorretto:17 AS build
WORKDIR /app
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar
ENV QUARKUS_PROFILE=prod
ENTRYPOINT ["java", "-Dquarkus.profile=${QUARKUS_PROFILE}", "-jar", "app.jar"]
