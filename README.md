
# Welcome to Client Management API Project!

This project was implemented in order to demonstrate how to build rest APIs using Spring Boot.  The project structure was generated using [Spring Initilizr](https://start.spring.io/)

For this demonstration purpose, we are creating a collection of APIs to manage client data. 

The project is using the memory database H2 in order to facilitate the demonstration. When the application starts the database is created, and some clients are inserted into the database.

# Requirements
All project dependencies are managed by Maven. Besides maven dependencies, you will need the following:

 - JDK 11
 - [Docker](https://docs.docker.com/engine/install/ubuntu/)
 - [Google Cloud SDK](https://cloud.google.com/sdk/docs/install)

# Available APIs
All APIs use JSON format as the communication pattern to send and receive information

 - `GET /clients` - Returns all clients
 - `GET /clients/find` - Returns a list of clients according to the parameter q
 - `GET /clients/{id}` - Returns the details of a specific client
 - `POST /clients` - Adds a new client
 - `PUT /clients` - Updates client attributes 
 - `PATH /clients/{id}` - Updates some client attributes 
 - `DELETE /clients/{id}`- Deletes a specific client
 
# Running the application

## No docker container
As we are testing a Maven project, you can run the application by using:

    $ ./mvnw spring-boot:run

## With a Docker Container
As we are testing a Maven project, you can run the application with a docker container by using:

    $ sudo docker build -t springio/client-management-api .
    $ sudo docker run -p 8080:8080 springio/client-management-api

# Deploying the application

## Google Cloud - App Engine
If you want to deploy this application to Google App Engine, just run the following command from the project root directory:

     $ gcloud app deploy
     $ gcloud app browse -s client-management-api

You can check my own deployment to App Engine. The service is available from the link:
[client-management-api](https://client-management-api-dot-aaperei.rj.r.appspot.com/clients)

## Google Cloud - Kubernetes
If you want to deploy this application to Google kubernetes, just follow this tutorial:
[Deploy a Spring Boot Java app to Kubernetes on Google Kubernetes Engine](https://developers.google.com/codelabs/cloud-springboot-kubernetes?continue=https%3A%2F%2Fdevelopers.google.com%2Flearn%2Fpathways%2Fjava-cloud-fundamentals%23codelab-https%3A%2F%2Fdevelopers.google.com%2Fcodelabs%2Fcloud-springboot-kubernetes#5)

# HTTP Requests - Curl
You can explore the client-management-api service using the curl commands:

     $ #Get all clients
     $ curl --location --request GET 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients'
     $   
     $ #Find a specific client by 'id'
     $ curl --location --request GET 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients/1'
     $        
     $ #Find client by query     
     $ curl --location --request GET 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients/find?q=maRia'
     $        
     $ #Delete a specific cliend by 'id'     
     $ curl --location --request DELETE 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients/1'
     $        
     $ #Create a new Client
     $ curl --location --request POST 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients' \
     $ --header 'Content-Type: application/json' \
     $ --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
     $ --data-raw '{
     $ "cpf": 79191439043,
     $ "name": "RITA ANDRADE",
     $ "address": {
     $ "street": "Rua dos Amores xxxxxxxxxxxx",
     $ "streetNumber": 1000,
     $ "city": "Paraíso",
     $ "neighborhood": "Bosque",
     $ "zipCode": 4641616,
     $ "country": "BRASIL"
     $ },
     $ "email": "test@test.com.br",
     $ "birthDate": "1991-02-03"
     $ }'
     $        
     $ #Update an existing client by id   
     $  curl --location --request PUT 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients/8' \
     $  --header 'Content-Type: application/json' \
     $  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
     $  --data-raw '{
     $  "id": 8,
     $  "cpf": 79191439043,
     $  "name": "RITA ANDRADE",
     $  "address": {
     $  "id": 8,
     $  "street": "Rua dos Amores Mudou de Nome",
     $  "streetNumber": 1000,
     $  "city": "Paraíso",
     $  "neighborhood": "Bosque",
     $  "zipCode": 4641616,
     $  "country": "BRASIL"
     $  },
     $  "birthDate": "1991-02-03",
     $  "email": "test@test.com.br"
     $  }'    
     $  
     $ #Update some attributes from a specific cliend by 'id'     
     $  curl --location --request PATCH 'https://client-management-api-dot-aaperei.rj.r.appspot.com/clients/1' \
     $  --header 'Content-Type: application/json' \
     $  --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
     $  --data-raw '  {
     $  "id": 1,
     $  "cpf": 16559815900,
     $  "name": "JOSE GERALDO NETO NOVO NOME",
     $  "birthDate": "1990-02-03",
     $  "email": "test@test.com.br"
     $  }'