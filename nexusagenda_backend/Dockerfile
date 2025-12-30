# --- Etapa 1: Compilación (Build) ---
# Usamos una imagen con Maven y Java 21 para construir el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos el archivo de dependencias y el código fuente
COPY pom.xml .
COPY src ./src

# Ejecutamos el empaquetado (saltando los tests para ir más rápido)
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecución (Run) ---
# Usamos una imagen más ligera solo con el JRE (Java Runtime Environment) 21 para correr la app
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el archivo .jar generado en la etapa anterior a esta nueva imagen
# Lo renombramos a 'app.jar' para que el comando de inicio sea más fácil
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 (el estándar de Spring Boot)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]