# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Define a build argument for the JAR file name
ARG JAR_FILE

# Copy the JAR file from the build directory to the container
COPY target/${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
