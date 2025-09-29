FROM maven:3.8 AS builder
WORKDIR /
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /
VOLUME /tmp
EXPOSE 8080
#COPY wait-for-maven.sh /wait-for-maven.sh
COPY  --from=builder ../target/*.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
