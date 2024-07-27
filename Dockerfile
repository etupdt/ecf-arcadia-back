
FROM maven:3.9.7-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

WORKDIR /tls

RUN openssl pkcs7 -print_certs -in server.p7b -out server.pem
RUN openssl pkcs12 -export -inkey server.key -in server.pem -name etupdt -out server.p12 -passout pass:${TLS_PASSWORD}
RUN sudo keytool -importkeystore -srckeystore server.p12 -srcstoretype pkcs12 -srcalias etupdt -destkeystore keystore.jks -deststoretype jks -deststorepass ${TLS_PASSWORD} -srcstorepass ${TLS_PASSWORD} -destalias etupdt

FROM tomcat:jre21-temurin-jammy

ARG ENV=prod

RUN mkdir -p /usr/local/images

WORKDIR /usr/local/tomcat

COPY --chmod=755 --from=build /tls/keystore.jks ./conf
COPY --chmod=755 --from=build /tls/server.${ENV}.xml ./conf

COPY --chmod=755 --from=build /app/target/ecf-arcadia-back.war ./webapps/
COPY --chmod=755 --from=build /app/src/main/resources/application.properties ./lib
COPY --chmod=755 --from=build /app/src/main/resources/server/images.xml ./conf/Catalina/localhost/
