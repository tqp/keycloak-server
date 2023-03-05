# Keycloak-Server

## Deploying a New Version to Prod

### TO Run 
* `mvn clean install`
* `mvn spring-boot:run -Dspring-boot.run.profiles=prod`

### Step-By-Step

On your local machine:
* Start Docker Desktop
* Run docker-build-image.sh.
  * `./docker-build-image.sh`
* Save image to .tar to move to VM (make sure the 'images' directory exists).
  * `docker image save keycloak-server:latest -o deploy-config/images/keycloak-server.tar`
* Copy the image file to the root directory on the AWS EC2 instance:
  * `scp -i ./deploy-config/secrets/tims-analytics.pem ./deploy-config/images/keycloak-server.tar ec2-user@ec2-54-146-74-179.compute-1.amazonaws.com:~/temp/.`

Connect to the AWS EC2 Instance:
* Move the image file to the keycloak-server directory on the server.
  * `mv ~/temp/keycloak-server.tar ~/docker/keycloak-server/`
* Navigate to the keycloak-server directory
  * `cd docker/keycloak-server`
* Stop the running Docker container.
  * `docker-compose down`
* Remove the previous image.
  * `docker image ls`
  * `docker image rm <image_id>`
* Load the image into Docker
  * `docker image load -i keycloak-server.tar`
* Update the docker-compose.yml file to reference the proper image version.
* Start the container using docker-compose:
  * `docker-compose up -d`

To "tail" the log:
* `docker logs keycloak-server --follow`
