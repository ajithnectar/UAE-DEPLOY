# Use an OpenJDK base image
FROM openjdk:17-jdk-slim
 
# Set the working directory
WORKDIR /app
 
# Copy the built WAR file
COPY target/user_project-0.0.1-SNAPSHOT.war app.war
 
# Expose the port your app runs on
EXPOSE 8081
 
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.war", "--server.port=8081"]