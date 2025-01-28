FROM amazoncorretto:17 AS build
WORKDIR /app
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar

# Exponer el puerto para Render (Render detecta automáticamente el puerto expuesto)
EXPOSE 8080

# Configura el punto de entrada. Render inyectará las variables de entorno en tiempo de ejecución.
ENTRYPOINT ["java", "-jar", "app.jar"]
