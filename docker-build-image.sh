mvn clean package
docker build -f Dockerfile --tag=keycloak-server:latest .
