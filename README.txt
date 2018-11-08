SAMPLE

   Sample correlator plugins using the Java Plugin Development Kit (PDK)


DESCRIPTION

   Library source and associated MonitorScript programs to demonstrate the
   development of EPL plugins written in Java. 
   

COPYRIGHT NOTICE

   $Copyright(c) 2013 Progress Software Corporation. All Rights Reserved.$ 
   $Copyright (c) 2013-2017 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$ 
   Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 


FILES

   README.txt                This file
   build.xml                 ANT build file for all plugins
   
   SimplePlugin.java         Source for the simple example, using the Java API
   SimplePlugin.mon          MonitorScript code for the Java simple example 
   SimplePlugin.xml          XML deployment descriptor for the Simple plugin jar
   SimplePluginSample.txt    Reference output for the simple plugin
   
   ComplexPlugin.java        Source for the complex example, using the Java API
   ComplexPlugin.mon         MonitorScript code for the Java complex example
   ComplexPlugin.xml         XML deployment descriptor for the Complex plugin jar
   ComplexPluginSample.txt   Reference output for the complex plugin
    
   SendPlugin.java           Source for the send example, using the Java API
   SendPlugin.mon            MonitorScript code for the Java send example
   SendPlugin.xml            XML deployment descriptor for the Send plugin jar
   SendPluginSample.txt      Reference output for the send plugin

   SubscribePlugin.java      Source for the subscribe example, using the Java API
   SubscribePlugin.mon       MonitorScript code for the Java subscribe example
   SubscribePlugin.xml       XML deployment descriptor for the Subscribe plugin jar
   SubscribePluginSample.txt Reference output for the subscribe plugin


BUILDING THE SAMPLES

   It is recommended (especially for Windows users) that you copy this sample 
   folder to an area of your APAMA_WORK directory rather than running it 
   directly from the installation directory. For Windows users with UAC 
   enabled this step is required to avoid access denied errors when writing 
   to the sample directory.

   The Java samples use the Java EPL plugin API. 
   
   Note also that Apache ANT is also required to build the plugins using the
   supplied build.xml.

   ** To build the samples **
   
   Run ant in the current directory:

   $ ant

   A successful build will produce four output files:

      simple_plugin.jar
      complex_plugin.jar
      send_plugin.jar
      subscribe_plugin.jar
   
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

   SimplePlugin.mon should produce output similar to the file SimplePluginSample.txt

   ComplexPlugin.mon should produce output similar to the file ComplexPluginSample.txt

   SendPlugin.mon should produce output similar to the file SendPluginSample.txt

   SubscribePlugin.mon should produce output similar to the file SubscribePluginSample.txt

