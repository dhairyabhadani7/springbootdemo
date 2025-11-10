# 1️⃣ Base image (contains JDK)
FROM eclipse-temurin:21-jdk-jammy

# 2️⃣ Set working directory inside container
WORKDIR /app

# 3️⃣ Copy the built JAR into container
ARG JAR_FILE=target/springbootdemo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 4️⃣ Expose Spring Boot’s default port
EXPOSE 8080

# 5️⃣ Define command to run app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]