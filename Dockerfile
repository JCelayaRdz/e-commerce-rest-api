FROM maven:3.9.1-amazoncorretto-17 AS builder
WORKDIR /api
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B package --no-transfer-progress -DskipTests

FROM amazoncorretto:17-alpine3.18
WORKDIR /api
COPY --from=builder /api/target/e-commerce-rest-api.jar ./e-commerce-rest-api.jar
EXPOSE 8080
CMD ["java", "-jar", "e-commerce-rest-api.jar"]