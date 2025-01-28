FROM amazoncorretto:17 AS build
WORKDIR /app
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar

# Especifica que Quarkus debe ejecutarse en el perfil de producción
ENV QUARKUS_PROFILE=prod

# Exponer el puerto para Render (Render detecta automáticamente el puerto expuesto)
EXPOSE 8080

# Configura el punto de entrada. Render inyectará las variables de entorno en tiempo de ejecución.
ENTRYPOINT ["java", \
            "-Dquarkus.http.host=0.0.0.0", \
            "-Dquarkus.http.port=${PORT}", \
            "-Dquarkus.datasource.jdbc.url=${DATABASE_URL}", \
            "-Dquarkus.datasource.username=${DATABASE_USER}", \
            "-Dquarkus.datasource.password=${DATABASE_PASSWORD}", \
            "-Dquarkus.profile=${QUARKUS_PROFILE}", \
            "-jar", "app.jar"]
