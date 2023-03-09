@echo off
set pathDeploy="\\wsl.localhost\Ubuntu\home\junior\rabbit-service"

if not exist %pathDeploy% ( 
    mkdir %pathDeploy%
    echo Folder created.
 )

copy ..\rabbitmq\docker-compose.yml %pathDeploy%