# Base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Create folder for H2 persistent DB
RUN mkdir -p /app/data

# Copy pre-built JAR
COPY target/studentapp-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 2025

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
