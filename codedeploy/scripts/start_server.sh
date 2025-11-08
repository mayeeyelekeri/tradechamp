#!/bin/bash
pwd 
id 
ls -lrt /tmp 
ls -lrt ~
echo $HOME
cd /tmp; docker build -t tradechamp:latest .
docker run --name tradechamp -p 80:80 -d tradechamp:latest 
