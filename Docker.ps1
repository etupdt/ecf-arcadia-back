
docker stop ecf-arcadia-back-preprod
docker rm ecf-arcadia-back-preprod
docker image rm ecf-arcadia-back-preprod

docker build -t ecf-arcadia-back .

# docker run -d --name ecf-arcadia-back -p 8080:8080 ecf-arcadia-back

docker compose -f .\docker-compose-preprod.yml up -d
