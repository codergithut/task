#!/bin/bash
git pull

mvn clean

mvn package

mvn dockerfile:build

docker stop task

docker rm task