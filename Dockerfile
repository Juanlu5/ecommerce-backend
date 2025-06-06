# Imagen base con JDK 21
FROM eclipse-temurin:21-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiamos el JAR que generará Maven
COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]