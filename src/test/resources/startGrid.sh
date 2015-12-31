#!/bin/bash
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker run -d --name=joomla -p 80:80 ajodochus/joomla_installed:version6
# considers .yml file in the same location as the bash script
# starting hub, register ff and chrome nodes
docker-compose up -d
