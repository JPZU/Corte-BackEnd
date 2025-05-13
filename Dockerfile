# Etapa de construcción
FROM eclipse-temurin:22-jdk AS builder

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build -x test --no-daemon

# Etapa de ejecución
FROM eclipse-temurin:22-jdk
WORKDIR /app

COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
