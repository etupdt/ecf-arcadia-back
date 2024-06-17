
./mvnw.cmd clean -f .\pom.xml
# ./mvnw.cmd -Ppreprod compile -f .\pom.xml
./mvnw.cmd -DENV=preprod install -DskipTests -f .\pom.xml

docker stop ecf-arcadia-back-preprod
docker rm ecf-arcadia-back-preprod
docker stop ecf-arcadia-back-test
docker rm ecf-arcadia-back-test
docker image rm ecf-arcadia-back

docker build --no-cache -t ecf-arcadia-back --build-arg CATALINA_HOME=/usr/local/tomcat .

docker run -d --name ecf-arcadia-back-preprod `
    -p 8082:8080 `
    -v ecf-arcadia-volume-preprod:/usr/local/images `
    --env-file src/main/resources/preprod/env.properties `
    ecf-arcadia-back

docker run -d --name ecf-arcadia-back-test `
    -p 8083:8080 `
    -v ecf-arcadia-volume-test:/usr/local/images `
    --env-file src/main/resources/test/env.properties `
    ecf-arcadia-back

#docker compose -f .\docker-compose.yml up -d
