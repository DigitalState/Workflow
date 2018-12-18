FROM openjdk:8-jdk-alpine

ENV DATASOURCE_URL postgresql://localhost:5432/camunda?createDatabaseIfNotExist=true
ENV DATASOURCE_USER camunda
ENV DATASOURCE_PASSWORD !ChangeMe!

ENV ADMIN_USER admin
ENV ADMIN_PASSWORD !ChangeMe!

COPY app.jar /app.jar

COPY config.yml /config.yml

VOLUME /tmp

EXPOSE 80

ENTRYPOINT ["sh", "-c"]

CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.config.location=file:./config.yml"]
