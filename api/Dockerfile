FROM openjdk:8-jdk-alpine

ENV DATABASE_URL postgresql://localhost:5432/workflow?createDatabaseIfNotExist=true
ENV DATABASE_USER workflow
ENV DATABASE_PASSWORD !ChangeMe!

ENV ADMIN_USER admin
ENV ADMIN_PASSWORD !ChangeMe!

ENV TIMEZONE UTC

COPY api.jar /api.jar

COPY config.yml /config.yml

VOLUME /tmp

EXPOSE 80

ENTRYPOINT ["sh", "-c"]

CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar /api.jar --spring.config.location=file:./config.yml"]
