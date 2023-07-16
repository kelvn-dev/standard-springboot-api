FROM openjdk:11

WORKDIR /opt/springboot-api

COPY target/standard-api-1.0.1.jar standard-api-1.0.1.jar

RUN chmod +x standard-api-1.0.1.jar

ENTRYPOINT ["java", "-jar", "standard-api-1.0.1.jar"]