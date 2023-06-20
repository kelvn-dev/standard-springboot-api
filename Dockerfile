FROM openjdk:11

WORKDIR /opt/springboot-api
COPY target/standard-api-0.0.1-SNAPSHOT.jar /opt/springboot-api/standard-api-0.0.1-SNAPSHOT.jar
RUN chmod +x /opt/springboot-api/standard-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/opt/springboot-api/standard-api-0.0.1-SNAPSHOT.jar"]