FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE=target/task.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]