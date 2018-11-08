SAMPLE

   Using the Apama Builder image.


DESCRIPTION

   Using the Apama Builder image to build an EPL plugin written in Java, test the plugin and then 
   build a docker image that runs the application in the deployed container. 


FILES

   README.txt                This file
   build.xml                 ANT build file for all plugins
   
   ComplexPlugin.java        Source for the complex example, using the Java API
   ComplexPlugin.mon         MonitorScript code for the Java complex example
   ComplexPlugin.xml         XML deployment descriptor for the Complex plugin jar
   ComplexPluginSample.txt   Reference output for the complex plugin


BUILDING THE SAMPLE

   clone this repository 

      git clone 

   build the image with a tag for the final image

      cd ApamaBuilderExample.git/trunk
      docker build -t builder-test:latest .

   run the image 

      docker run -it --rm builder-test:latest 
   
RUNNING THE SAMPLES

   
   1. Navigate to the folder that contains this README.txt 
      followed by the corresponding monitorScript code:
   
      > engine_inject -v ComplexPlugin.mon -p docker_port
      
      
SAMPLE OUTPUT

   ComplexPlugin.mon should produce output similar to the file ComplexPluginSample.txt

