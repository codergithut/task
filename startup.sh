#!/bin/bash
mvn clean

mvn package

mvn dockerfile:build