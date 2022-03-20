FROM openjdk:17
COPY application.yml /opt/dropwizard/
COPY target/task-manager-1.0-SNAPSHOT.jar /opt/dropwizard/
ENV DATABASE_USERNAME #DATABASE_USERNAME
ENV DATABASE_PASSWORD #DATABASE_PASSWORD
EXPOSE 8080
WORKDIR /opt/dropwizard
CMD ["java", "-jar", "-Done-jar.silent=true", "task-manager-1.0-SNAPSHOT.jar", "server", "application.yml"]