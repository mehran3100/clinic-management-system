FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/patient-service-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
