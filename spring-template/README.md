# Overview
This template provides a very basic Spring Boot application. This is intended to provide a bare minimum set of files that is executable, and can be compiled into a functional docker image.

There are 2 APIs for 2 tasks in [SuperVisorController.java](spring-template/src/main/java/springtemplate/SupervisorController.java):
- First is POST API, send user details with validation and supervisor's name to notify.
- Second is GET API returns list of supervisors(Manager) from external API calling using RestTemplate , filter its result by sorting it last name and then first name, check if supervisor jurisdiction field is not numeric.

# Running locally with gradle
gradle build
gradle bootRun

# Running with docker
To help ensure consistently correct startup across multiple platforms, you may choose to use Docker to containerize your application.  Installation steps for docker can be found on their main page.
https://docs.docker.com/engine/install/

With Docker installed, you can build your a new image. This build needs to be run after any changes are made to the source code.
```
docker build --tag=spring-template:latest .
```

After the image builds successfully, run a container from that image.
```
docker run -d --name spring-template -p8080:8080 spring-template:latest
```

When you are done testing, stop the server and remove the container.
```
docker rm -f spring-template
```

# Running with docker-compose
docker-compose up
