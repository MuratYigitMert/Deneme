# Use a base image with Java 17 (or your JDK version)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the jar file from target folder into the container
COPY target/Deneme-0.0.1-SNAPSHOT.jar app.jar


# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
