#!/bin/bash
docker ps 
docker ps -aqf "name=tradechamp"  |xargs docker rm -f  | echo "removed any old running containers(if any)"
docker ps 
