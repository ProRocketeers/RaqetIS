# Use an official OpenJDK image as the base image
FROM eclipse-temurin:21-jdk-alpine as build

WORKDIR /app

COPY gradlew gradlew.bat settings.gradle.kts build.gradle.kts ./
COPY gradle ./gradle

COPY src ./src

RUN chmod +x gradlew

# Build the application
RUN ./gradlew bootJar --no-daemon

# ------------------
# Use a smaller base image for the runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar raqetis.jar

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "raqetis.jar"]