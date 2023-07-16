FROM openjdk:11

WORKDIR /opt/springboot-api

COPY target/standard-api-0.0.1-SNAPSHOT.jar standard-api-0.0.1-SNAPSHOT.jar

RUN chmod +x standard-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "standard-api-0.0.1-SNAPSHOT.jar"]