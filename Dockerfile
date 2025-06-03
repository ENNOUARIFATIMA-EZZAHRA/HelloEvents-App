# 1. Image Java
FROM openjdk:21-jdk-slim


# 2. Copier le fichier JAR compilé
COPY target/eventManagement-0.0.1-SNAPSHOT.jar app.jar



# 3. Point d'entrée
ENTRYPOINT ["java", "-jar", "/app.jar"]
