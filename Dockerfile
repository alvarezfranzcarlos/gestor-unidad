FROM amazoncorretto:17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw package

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/gestor-unidad-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
