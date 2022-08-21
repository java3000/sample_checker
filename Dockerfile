FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package 

FROM openjdk:11

RUN mkdir /app
WORKDIR /app

COPY --from=build /home/app/target/minchecker-0.0.1-SNAPSHOT.jar /app/checker.jar
COPY src /app/src

EXPOSE 9081
ENV rs.endpoint=http://192.168.1.4:9080

ENTRYPOINT ["java", "-jar", "/app/checker.jar"]