
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


### Installation and configuration of project
2. Open project folder go to dir : \src\main\resources\docker\application.properties
3. update following field as per you DB credentials

   ```bash
    spring.datasource.url=jdbc:postgresql://your_ip:5432/your_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
   ```

### Running the Application
1. Go to root directory and build the project 
     ```
     mvn clean install -Dmaven.test.skip=true -P docker
    ```
## Accessing the API
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
