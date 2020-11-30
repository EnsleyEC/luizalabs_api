# API Rest - Luizalabs: feita para o desafio.

Essa API está sendo hosteada na Google Cloud utilizando 1 cluster com 3 replicas (3 pods) da API e 1 (1 pod) do postgres. Com o intuito de usar o LoadBalancer para distribuir as requisições da API nessas 3 replicas, e claro, facilitar todo processo de automização da mesma (CI/CD) e implementação de novas features no próprio cluster, para aprimorar cada vez mais o desempenho da aplicação.

[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](http://34.95.239.212:3000/swagger-ui.html)
[![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](http://perso.crans.org/besson/LICENSE.html)
![Badge](https://img.shields.io/static/v1?label=status&message=working&color=4CC61E)
![Badge](https://travis-ci.org/EnsleyEC/luizalabs_api.svg?branch=master)

# Tópicos

<!--ts-->
   * [Demo](#Demo)
   * [Pré-requisitos](#Pré-requisitos)
   * [Executando](#Executando)
   * [Tests](#Tests)
   * [Tecnologias/Ferramentas](#Tecnologias/Ferramentas)
<!--te-->

# Demo

<p>URL base da API: http://34.95.239.212:3000</p>
<a href="http://34.95.239.212:3000/swagger-ui.html">Link de acesso</a></p>

<p>Para consumir a API, é nececessário autenticar-se antes e enviar o token nas requests.

Caso não tenha conta, o endpoint de criação de conta (/api/v1/account/save) é livre para acesso.</p>

# Pré-requisitos

Para testar/executar o código em um ambiente de desenvolvimento, você irá precisar(alguns são opcionais):

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://spring.io/projects/spring-boot"> Spring Boot</a>,
<a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html"> Java 8</a>,
<a href="https://www.postgresql.org/">PostgreSQL</a>,
<a href="https://www.postman.com/">Postman</a>

Caso for apenas para executar a aplicação:

<a href="https://git-scm.com/">Git</a>,
<a href="https://www.docker.com/"> Docker</a>,
<a href="https://docs.docker.com/compose/"> Docker Compose </a>,
<a href="https://www.postman.com/">Postman</a>

# Executando


### - Clonar o projeto usando (HTTPS):

    git clone https://github.com/EnsleyEC/luizalabs_api.git

### - Acesse a pasta do projeto: luizalabs-server-rest

    cd luizalabs_api/luizalabs-server-rest/

### - Gere a imagem do projeto (API + PostgreSQL) com o Docker Compose, no mesmo diretório do arquivo docker-compose.yml

    docker-compose up

### - URL local de acesso:

    URL base: http://localhost:3000/

    DOC: http://localhost:3000/swagger-ui.html

### - Regras da API

    Para usar, é necessário ter uma conta ou criar uma conta antes. E em seguida, antenticar-se, pegando o token e mandando nos outros endpoints para conseguir utiliza-los.

    Endpoints livres para uso, sem precisar mandar token:

    - Criação de conta: /api/v1/account/save
    - Autenticação: /api/v1/signIn

    Exemplo de header obrigatório:

    {key: Authorization, value: Bearer flkjkds.......}

# Tests

    - Postman:
        - Resultado: /docs/postman/LuizaLabs.postman_test_run.json
        - Descrição: no postman é possível importar esse "run" e ver os resultados dos testes de cada endpoint.

# Tecnologias/Ferramentas

As seguintes ferramentas foram usadas na construção do projeto:

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

# Autor

<sub><b>Ensley Fortunato Moreira Ribeiro </b></sub></a>

[![Linkedin Badge](https://img.shields.io/badge/-Ensley-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/tgmarinho/)](https://www.linkedin.com/in/ensley-ribeiro-37b293126/) 
[![Gmail Badge](https://img.shields.io/badge/-ensleyfmr@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ensleyfmr@gmail.com)](mailto:ensleyfmr@gmail.com)