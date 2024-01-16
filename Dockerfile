FROM maven:3.9.2-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY ./src ./src

RUN mvn clean package -Dmaven.test.skip.exec

## BUILD JAR

FROM eclipse-temurin:17-alpine

COPY --from=build ./app/target/*.jar ./ms-management.jar

CMD ["java", "-jar", "ms-management.jar"]