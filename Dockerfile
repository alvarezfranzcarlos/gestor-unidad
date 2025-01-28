FROM amazoncorretto:17 AS build
WORKDIR /app
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar
ENV QUARKUS_PROFILE=prod
# Configurar variables de entorno de Quarkus en el Dockerfile
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_USER=${DATABASE_USER}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV PORT=${PORT}
ENTRYPOINT ["java", "-Dquarkus.datasource.jdbc.url=${DATABASE_URL}", \
                 "-Dquarkus.datasource.username=${DATABASE_USER}", \
                 "-Dquarkus.datasource.password=${DATABASE_PASSWORD}", \
                 "-Dquarkus.http.port=${PORT}", \
                 "-Dquarkus.profile=${QUARKUS_PROFILE}", "-jar", "app.jar"]
