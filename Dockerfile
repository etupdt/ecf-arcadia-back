
FROM maven:3.9.7-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY . .

# RUN mvn clean package -DskipTests

ARG ENV=prod
ARG SECRET_KEY
ARG SECRET_P7B
ARG TLS_PASSWORD

RUN if [ "${ENV}" == "prod" ] ; then echo ${SECRET_KEY} > ./tls/server.key ; fi
RUN if [ "${ENV}" == "prod" ] ; then echo ${SECRET_P7B} > ./tls/server.p7b ; fi
RUN if [ "${ENV}" == "prod" ] ; then openssl pkcs7 -print_certs -in ./tls/server.p7b -out ./tls/server.pem ; fi
RUN if [ "${ENV}" == "prod" ] ; then openssl pkcs12 -export -inkey ./tls/server.key -in ./tls/server.pem -name etupdt -out ./tls/server.p12 -passout pass:${TLS_PASSWORD} ; fi
# RUN keytool -importkeystore -srckeystore ./server.p12 -srcstoretype pkcs12 -srcalias etupdt -srcstorepass ${TLS_PASSWORD} -destkeystore ./keystore.jks -deststoretype jks -deststorepass ${TLS_PASSWORD} -destalias etupdt

FROM tomcat:jre21-temurin-jammy

ARG ENV=prod

RUN mkdir -p /usr/local/images

WORKDIR /usr/local/tomcat

# COPY --chmod=755 --from=build /tls/keystore.jks ./conf
RUN if [ "${ENV}" == "prod" ] ; then COPY --chmod=755 --from=build /app/tls/server.p12 ./conf ; fi
COPY --chmod=755 --from=build /app/tls/server.${ENV}.xml ./conf/server.xml

COPY --chmod=755 --from=build /app/src/main/resources/application.properties ./lib
COPY --chmod=755 --from=build /app/src/main/resources/server/images.xml ./conf/Catalina/localhost/
# COPY --chmod=755 --from=build /app/target/ecf-arcadia-back.war ./webapps/
