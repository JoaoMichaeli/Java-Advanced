# Etapa de build
FROM gradle:8.10.2-jdk17 AS builder
WORKDIR /app

# Copia tudo e faz o build do JAR
COPY . .
RUN gradle clean bootJar --no-daemon

# Etapa de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado do builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Render usa PORT, então expõe dinamicamente
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
