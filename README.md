
# User Details Application – Project Overview

## Overview
This project provides a collection of REST API endpoints to create, update, delete, and list users.

## Table of Contents
- [Project Setup](#project-setup)
  - [Prerequisites](#prerequisites)
  - [Installation and configuration of project](#installation-and-configuration-of-project)
  - [Running the Application](#running-the-application)
  - [Accessing the API](#accessing-the-api)
  - 


## Project Setup

### Prerequisites
Make sure you have the following installed:
- jdk ( for this program we have used java version 1.8)
- PostgreSQL
- tomcat

# Run Tomcat in docker container
### Tomcat should have credentials 

### Installation and configuration of project
2. Open project folder go to dir : \src\main\resources\docker\application.properties
3. update following field as per you DB credentials

   ```bash
    spring.datasource.url=jdbc:postgresql://your_ip:5432/your_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
   ```

# CI/CD Pipeline and Branch Strategy
## Project Branches
### The repository contains 5 main projects, each in its own folder and managed through its own Git branch:
- deployOne
- deployTwo
- deployThree
- deployFour
- deployFive

## Deployment Strategy
### Each project is built and deployed independently.

### Jenkins monitors each branch (deployOne, deployTwo, etc.) for changes.

### When changes are pushed to a branch:

### Jenkins checks out the corresponding project folder.

### Builds the project using Maven.
   ```
     mvn clean install -Dmaven.test.skip=true -P docker
   ```
### Deploys the generated WAR file to the Tomcat webapps directory.

## Once you deploy the project in the webapps folder you can see the tomcat ui all 5 projects are running 
![image](https://github.com/user-attachments/assets/4204d7ea-c429-4bec-92f2-8db325027e35)

# CI/CD Automation Goals
### Isolate build and deployment per project using branch-based workflows.
### Ensure automated and consistent deployment to Tomcat via Jenkins jobs or pipelines.
### Support rollback and environment-specific configurations by managing properties per project folder.



# Accessing the API
## user endpoints
#### create user

```http
  POST /users
```

#### update user

```http
  PUT /users
```
#### Get user list

```http
  GET /users
```

## delete user

```http
  DELETE /users/{id}
```

## find user

```http
  GET /users/{id}
```

## actuator url
```
http://domain:port/user_project-0.0.1-SNAPSHOT/actuator
```
## Health monitoring url
```
http://domain:port/user_project-0.0.1-SNAPSHOT/actuator/health
```
