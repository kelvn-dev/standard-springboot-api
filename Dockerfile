FROM openjdk:11

WORKDIR /opt/standard-api
COPY target/standard-api-0.0.1-SNAPSHOT.jar /opt/standard-api/standard-api-0.0.1-SNAPSHOT.jar
RUN chmod +x /opt/standard-api/standard-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/opt/standard-api/standard-api-0.0.1-SNAPSHOT.jar"]