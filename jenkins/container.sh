#!/bin/bash

sshpass -p c6b94gmg ssh cyborg@192.168.0.8 rm -rf /home/cyborg/Dockers/tasks
sshpass -p c6b94gmg ssh cyborg@192.168.0.8 mkdir -p /home/cyborg/Dockers/tasks

sshpass -p c6b94gmg scp -oStrictHostKeyChecking=no Dockerfile cyborg@192.168.0.8:/home/cyborg/Dockers/tasks/
sshpass -p c6b94gmg scp -oStrictHostKeyChecking=no ./tasks-backend/target/tasks-backend.war cyborg@192.168.0.8:/home/cyborg/Dockers/tasks/
sshpass -p c6b94gmg scp -oStrictHostKeyChecking=no ./tasks-frontend/target/tasks.war cyborg@192.168.0.8:/home/cyborg/Dockers/tasks/

sshpass -p c6b94gmg ssh cyborg@192.168.0.8 docker rm -f $(docker ps -a | grep tasks | awk '{ print $1 }')
sshpass -p c6b94gmg ssh cyborg@192.168.0.8 docker rmi -f $(docker images | grep tasks | awk '{ print $3 }')

sshpass -p c6b94gmg ssh cyborg@192.168.0.8 docker build -f /home/cyborg/Dockers/tasks/Dockerfile /home/cyborg/Dockers/tasks/ -t cyborgmg/tasks:latest
sshpass -p c6b94gmg ssh cyborg@192.168.0.8 docker run -d --name tasks --hostname tasks -p 8080:8080 cyborgmg/tasks:latest
