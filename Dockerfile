FROM amazoncorretto:17-alpine3.18

WORKDIR /api

COPY target/e-commerce-rest-api.jar e-commerce-rest-api.jar

EXPOSE 8080

CMD ["java", "-jar", "e-commerce-rest-api.jar"]