# Usar una imagen base con Java 17
FROM amazoncorretto:17

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en la carpeta target
COPY target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]