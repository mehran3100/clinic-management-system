FROM eclipse-temurin:17-jdk-alpine

RUN apk add --no-cache curl bash netcat-openbsd

WORKDIR /app

ARG JAR_FILE=api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Reuse shared script from docker-utils
COPY docker-utils/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "app.jar"]
