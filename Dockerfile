# 1️⃣ Use base image with Java 21
FROM eclipse-temurin:21-jdk-jammy

# 2️⃣ Set working directory inside container
WORKDIR /app

# 3️⃣ Copy the JAR from the current folder (no target/)
ARG JAR_FILE=target/springbootdemo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 4️⃣ Expose Spring Boot’s port
EXPOSE 8080

# 5️⃣ Run the Spring Boot JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
