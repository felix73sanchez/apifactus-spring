FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

# Copia todos los archivos (incluyendo .mvn/ y mvnw)
COPY . .

# Da permisos de ejecución al script mvnw
RUN chmod +x mvnw

# Ejecuta el comando
RUN ./mvnw clean package -DskipTests

# --- Etapa final ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Usa el puerto que necesites (Render suele usar $PORT)
EXPOSE 1221

ENTRYPOINT ["java", "-jar", "app.jar"]