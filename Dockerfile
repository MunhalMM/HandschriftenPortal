FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y postgresql-client && \
    rm -rf /var/lib/apt/lists/*

VOLUME /tmp

COPY target/handschriftenportal-1.0.1.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
