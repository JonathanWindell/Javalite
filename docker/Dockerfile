# Build (Uses JDK 21 & Maven)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build JAR-file (Assume that tests have already passed)
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Steg 2: Compiling phase (Using slim JRE 21)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy only finished JAR-file. 
COPY --from=build /app/target/*-jar-with-dependencies.jar app.jar

# Create map for database.
RUN mkdir -p /app/data

# Start application. 
ENTRYPOINT ["java", "-jar", "app.jar"]