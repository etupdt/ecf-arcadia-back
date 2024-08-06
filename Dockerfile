
FROM maven:3.9.7-eclipse-temurin-21-jammy AS build

WORKDIR /app

ENV TLS_PASSWORD TLS_PASSWORD

COPY . .

RUN mvn clean package -DskipTests

WORKDIR /tls

ENV TLS_PASSWORD TLS_PASSWORD

COPY ./tls ./

RUN openssl pkcs7 -print_certs -in ./server.p7b -out ./server.pem
RUN openssl pkcs12 -export -inkey ./server.key -in ./server.pem -name etupdt -out ./server.p12 -passout pass:${TLS_PASSWORD}
# RUN keytool -importkeystore -srckeystore ./server.p12 -srcstoretype pkcs12 -srcalias etupdt -srcstorepass ${TLS_PASSWORD} -destkeystore ./keystore.jks -deststoretype jks -deststorepass ${TLS_PASSWORD} -destalias etupdt

FROM tomcat:jre21-temurin-jammy

ARG ENV=prod

RUN mkdir -p /usr/local/images

WORKDIR /usr/local/tomcat

# COPY --chmod=755 --from=build /tls/keystore.jks ./conf
COPY --chmod=755 --from=build /tls/server.p12 ./conf
COPY --chmod=755 --from=build /tls/server.${ENV}.xml ./conf/server.xml

COPY --chmod=755 --from=build /app/src/main/resources/application.properties ./lib
COPY --chmod=755 --from=build /app/src/main/resources/server/images.xml ./conf/Catalina/localhost/
COPY --chmod=755 --from=build /app/target/ecf-arcadia-back.war ./webapps/
