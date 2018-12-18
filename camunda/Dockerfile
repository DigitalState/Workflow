FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY app.jar /app.jar

COPY config.yml /config.yml

ENTRYPOINT ['java', '-Djava.security.egd=file:/dev/./urandom', '-jar', '/app.jar', '--spring.config.location=file:./config.yml']
