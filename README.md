# Task Manager

![img.png](resources/taskManager.png)

Task Manager is an application for managing tasks or todos developed with Dropwizard (Java framework) for the backend
and React for the frontend.

## Running

`$ docker-compose up` will deploy following services:
- MySQL database with docker image ->https://hub.docker.com/repository/docker/atascg01/task-manager-database/general
- Dropwizard backend application with docker image -> https://hub.docker.com/repository/docker/atascg01/task-manager
- React frontend application with docker image -> https://hub.docker.com/repository/docker/atascg01/task-manager-frontend

## Testing report

Task manager uses Jacoco for generating a test report by executing jacoco maven goal: report

## Packaging

`mvn package` generates a `task-manager-1.0-SNAPSH.jar` that can be executed with
command `$ java -jar /path/to/jar server application.yml`.

There are some environment variables that the application needs, such as `DATABASE_USERNAME` and `DATABASE_PASSWORD`.

docker run -d -it --network app_network -e MYSQL_ROOT_PASSWORD=taskManager -e MYSQL_DATABASE=taskManager --name mysql_name mysql
docker run -d -it -e DATABASE_USERNAME=taskManager -e DATABASE_PASSWORD=taskManager -p 8080:8080 --network app_network task_manager:dev
