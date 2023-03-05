# Keycloak-Server

## Deploying a New Version to Prod

### Step-By-Step

On your local machine:
* Start Docker Desktop
* Build the App:
  * Make sure you're running the correct version of Java
  * Either run the 'Run Configuration' named: `Build App`.
  * Or run: `mvn clean install -Dactive.profile=prod -f pom.xml`
* Build the Docker image
  * `docker build -f Dockerfile --tag=keycloak-server:latest .`
* Save image to .tar to move to AWS
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
