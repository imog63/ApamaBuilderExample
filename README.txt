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

   
RUNNING THE SAMPLES

   Running of the samples requires access to the Correlator and Apama command 
   line tools. 

   To ensure that the environment is configured correctly for Apama, all the 
   commands below should be executed from an Apama Command Prompt, or from a 
   shell or command prompt where the bin\apama_env script has been run (or on 
   Unix, sourced). 
   
   1. Start the Apama Correlator in the Apama Command Prompt by
      running:

      > correlator -j

      The Apama Command Prompt can be started using the Windows Start Menu 
      shortcut.

   2. Navigate to the folder that contains this README.txt, and Inject the simple_plugin test Jar, 
      followed by the corresponding monitorScript code:
   
      > engine_inject -j -v simple_plugin.jar
      > engine_inject -v SimplePlugin.mon
      

      if you built the sample in the Apama install directory - otherwise, 
      replace the path with the location you copied the sample to.

   3. Repeat the previous step with the other plugins.

      
SAMPLE OUTPUT

   ComplexPlugin.mon should produce output similar to the file ComplexPluginSample.txt

