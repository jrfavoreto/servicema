----------
Como rodar:

mvn compile
mvn package
java -jar .\target\servicema-0.0.1-SNAPSHOT.jar

Banco H2: http://localhost:8080/h2
user:sa
passwd:password
-------------------------------
Utilização do docker em WSL

1. Iniciar serviço docker no wsl:
> sudo service docker start

2. Iniciar os containers (rabbitmq, ...) com docker compose:
> docker compose up

------------------------
VSCode Extensions utilizados:

* Thunder Client
* XML (Red Hat)
* Debugger for Java (Microsoft)
* Dependency Analytics (Red Hat)
* Extension Pack for Java (Microsoft)
* IntelliCode (Microsoft)
* IntelliCode API Usage Examples (Microsoft)
* Language Support for JAva (Red Hat)
* Maven for Java (Microsoft)
* Project Manager for Java (Microsoft)
* Spring Boot [Dashboard, Extension Pack, Tools, Initializr] (Microsoft)
* Test Runner for Java (Microsoft)
* WSL (Microsoft)
* Docker (Microsoft)