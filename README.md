# REST API - Luizalabs.

This API was being hosted on Google Cloud using 1 cluster with 3 replicas (3 pods) of the API and 1 (1 pod) of postgres. In order to use LoadBalancer to distribute API requests in these 3 replicas, and of course, facilitate the entire automation process (CI / CD) and implement new features in the cluster itself, to improve the application's performance even more.

![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)
[![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](http://perso.crans.org/besson/LICENSE.html)
![Badge](https://img.shields.io/static/v1?label=status-work&message=stopped&color=red)
![Badge](https://travis-ci.org/EnsleyEC/luizalabs_api.svg?branch=master)

# Topics

<!--ts-->
   * [Demo](#Demo)
   * [Prerequisites](#Prerequisites)
   * [Running](#Executando)
   * [Tests](#Tests)
   * [Technologies/Tools](#Technologies/Tools)
<!--te-->

# Demo

<a href="">Demo (Stopped)</a></p>

<p>To consume the API, it is necessary to authenticate beforehand and send the token in requests.

If you don't have an account, the account creation endpoint (/api/v1/account/save) is free to access.</p>

# Prerequisites

To test/run the code in a development environment, you will need (some are optional):

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://spring.io/projects/spring-boot"> Spring Boot</a>,
<a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html"> Java 8</a>,
<a href="https://www.postgresql.org/">PostgreSQL</a>,
<a href="https://www.postman.com/">Postman</a>

If it is just to run the application:

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://docs.docker.com/compose/"> Docker Compose </a>,
<a href="https://www.postman.com/">Postman</a>

# Running


### - Clone the project using (HTTPS):

    git clone https://github.com/EnsleyEC/luizalabs_api.git

### - Access the project folder: luizalabs-server-rest

    cd luizalabs_api/luizalabs-server-rest/

### - Generate the project image (API + PostgreSQL) with Docker Compose, in the same directory as the docker-compose.yml file

    docker-compose up

### - Local access URL:

    Base URL: http://localhost:3000/

    DOC: http://localhost:3000/swagger-ui.html

### - API Roles

    To use, you must have an account or create an account first. And then, log in, taking the token and sending the other endpoints to be able to use them.

    Free endpoints to use, without having to send a token:

    - Create account: /api/v1/account/save
    - Auth: /api/v1/signIn

    Mandatory header example:

    {key: Authorization, value: Bearer flkjkds.......}

# Tests

- Postman:
    - Result: /docs/postman/LuizaLabs.postman_test_run.json
    - Description: in the postman it is possible to import this "run" and see the test results for each endpoint.
- Spring boot:
    - When building the project with the command "./mvnw clean package" inside the folder luizalabs-server-rest, the unit tests of the API are performed (today Travis-CI does this part and indicates the status of the commit on GitHub). Currently, the API has 27 unit tests focused on controller tests (endpoints).

# Technologies/Tools

The following tools were use in the construction of the project:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [PostgreSQL](https://www.postgresql.org//)
- [Docker](https://www.docker.com/)
- [Kubernetes](https://kubernetes.io/)
- [Git](https://git-scm.com/)
- [Travis-CI](https://travis-ci.org/)
- [Google Cloud](https://cloud.google.com/)
- [Postman](https://www.postman.com/)
- [IntelliJ Community](https://www.jetbrains.com/idea/)
- [Pg Admin](https://www.pgadmin.org/)

# Author

<sub><b>Ensley Fortunato Moreira Ribeiro </b></sub></a>

[![Linkedin Badge](https://img.shields.io/badge/-Ensley-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/tgmarinho/)](https://www.linkedin.com/in/ensley-ribeiro-37b293126/) 
