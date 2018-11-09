[![Build Status](https://travis-ci.com/CaribouJohn/ApamaBuilderExample.svg?branch=master)](https://travis-ci.com/CaribouJohn/ApamaBuilderExample)

# Using the Apama Builder image

## DESCRIPTION

Using the Apama Builder image to build an EPL plugin written in Java, test the plugin and then 

build a docker image that runs the application in the deployed container. 

## FILES

| filename               | Description |
|------------------------|-------------|
|README.txt              |This file|
|build.xml               |ANT build file for all plugins|
|ComplexPlugin.java      |Source for the complex example using the Java API|
|ComplexPlugin.mon       |MonitorScript code for the Java complex example|
|ComplexPlugin.xml       |XML deployment descriptor for the Complex plugin jar|
|ComplexPluginSample.txt |Reference output for the complex plugin|

## BUILDING THE SAMPLE

clone this repository 

`git clone <URL copied from the "clone this repository" at the top of the page>`
      
or
      
`svn co <URL copied from the "clone this repository" at the top of the page>`

build the image with a tag for the final image, which also runs an automated test

`cd ApamaBuilderExample.git/trunk`

`docker build -t builder-test:latest .`

run the image 

`docker run -it --rm -p 40000:15903 builder-test:latest`

## RUNNING THE SAMPLES

start another connection to the machine running the docker image, and confirm that the expected docker container is running (and discover its container id)

`docker ps`

Make sure you source the apama_env file in the \<SAG installation\>/Apama/bin directory

`. <SAG installation>/Apama/bin/apama_env`

navigate to the folder that contains this README.txt and the monitorScript code. 

`engine_inject -v ComplexPlugin.mon -p 40000`

## SAMPLE OUTPUT

this can be viewed on the screen or read using

`docker logs <container id>`

ComplexPlugin.mon should produce output similar to the file ComplexPluginSample.txt

## TIDY UP

stop the correlator in the container, which will also stop the container 

`engine_management -p 40000 -s bye!`
