#
# Build stage
#
FROM maven:3.6.2-jdk-11-slim AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -q
#
# Package stage
#
FROM openjdk:11.0.12-jre-slim-buster
ARG path=/usr/app
WORKDIR ${path}
COPY --from=build ${path}/target/*.jar ${path}/app.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx280m -jar app.jar"]