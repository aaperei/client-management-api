
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

# API Documentation
You can try out the APIs through Swagger docs: [Api Documentation](https://client-management-api-dot-aaperei.rj.r.appspot.com/swagger-ui.html#/client-controller/getAllClientsUsingGET)