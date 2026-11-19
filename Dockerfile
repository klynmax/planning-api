FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests && cp target/*.jar app.jar

FROM eclipse-temurin:25-jre-alpine AS startar

WORKDIR /app

COPY --from=build /app/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiçes.active=production"]