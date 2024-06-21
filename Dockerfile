
FROM maven:3.9.7-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM tomcat:jre21-temurin-jammy

RUN mkdir -p /usr/local/images

WORKDIR /usr/local/tomcat

COPY --chmod=755 --from=build /app/target/ecf-arcadia-back.war ./webapps/
COPY --chmod=755 --from=build /app/src/main/resources/server/application.properties ./lib
COPY --chmod=755 --from=build /app/src/main/resources/server/images.xml ./conf/Catalina/localhost/
