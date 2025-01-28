# Usar una imagen base con Java 17
FROM amazoncorretto:17

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el wrapper de Maven y el archivo de configuración
COPY mvnw .
COPY .mvn .mvn

# Copiar el archivo pom.xml
COPY pom.xml .

# Descargar las dependencias necesarias
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente de la aplicación
COPY src src

# Compilar la aplicación
RUN ./mvnw package -DskipTests

# Exponer el puerto en el que correrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/quarkus-app/quarkus-run.jar"]