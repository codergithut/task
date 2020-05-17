#!/bin/bash

git pull

mvn clean

mvn package

mvn dockerfile:build

docker stop task

docker rm task

docker run -d -it -p 8080:8080 --name task tianjian3209/task:develop