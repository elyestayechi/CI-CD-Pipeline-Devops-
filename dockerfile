# Utiliser une image de base Java
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans l'image
COPY target/Foyer-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port que l'application écoute
EXPOSE 8089

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]