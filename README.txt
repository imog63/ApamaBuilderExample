SAMPLE

   Denial Of Service sample java application


DESCRIPTION

   The Event Correlator is used to detect Denial of Service attacks from one 
   or more hosts through detection of high densities of 'Ping' events. 
   Periodic alerts are emitted with the address of the originating host and 
   the number of Ping events detected. This java monitor demonstrates the use
   of prepared event expression templates, allowing for performance 
   optimisation in applications which carry out a significant amount of event 
   expression creation.
   
   
COPYRIGHT NOTICE

   $Copyright(c) 2002-2006, 2008, 2009, 2013 Progress Software Corporation. All Rights Reserved.$ 
   $Copyright (c) 2013 - 2017 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$ 
   Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 

 
FILES

   README.txt                   This file
   dos-feed.evt                 Sample event data
   build.xml                    XML-based build file
   src/Ping.java                Abstraction of a ping event
   src/PingAlert.java           Abstraction of a ping alert event
   src/PingChecker.java         Worker monitor used to detect DOS attacks
   src/Dos.java                 Factory monitor to start PingCheckers
   src/META-INF/jmon-jar.xml    Deployment descriptor for the sample


BUILDING THE SAMPLE

   It is recommended that you copy this sample folder to an area of your 
   APAMA_WORK directory rather than running it directly from the installation 
   directory. For Windows users with UAC enabled this step is required to 
   avoid access denied errors when writing to the sample directory.

   A Java compiler and optionally Apache Ant are required in order to build 
   the sample. Check the documentation for the recommended versions.


   ** To build the sample on UNIX (Linux) **
   
   Source the apama_env file located in the installation bin directory to set 
   the environment variables required for building the sample. To build either 
   run ant, or compile and archive directly in the src directory i.e.

    >ant
   
   or
   
    >cd src
    >javac -classpath "$APAMA_HOME/lib/ap-correlator-extension-api.jar" *.java
    >jar -cf ../dos-jmon.jar META-INF/jmon-jar.xml *.class


   ** To build the sample on Microsoft Windows **
   
   Either start a new "Apama Command Prompt" from the Start Menu, or from an 
   existing command prompt run the apama_env script located in the installation 
   bin directory to set the environment variables required for building the 
   sample. To build, either run ant, or compile and archive directly in the 
   src directory i.e.

    >ant
   
   or

    >cd src
    >javac -classpath "%APAMA_HOME%\lib\ap-correlator-extension-api.jar" *.java
    >jar -cf ..\dos-jmon.jar META-INF\jmon-jar.xml *.class


RUNNING THE SAMPLE

   To ensure that the environment is configured correctly for Apama, all the 
   commands below should be executed from an Apama Command Prompt, or from a 
   shell or command prompt where the bin\apama_env script has been run (or on 
   Unix, sourced). 

   1. Start the Apama Correlator in the Apama Command Prompt by
      running:

      > correlator --java
      
     The Apama Command Prompt can be started using the Windows Start Menu 
     shortcut.

   2. (in a separate command prompt/terminal) inject the application jar:
   
     >engine_inject -j dos-jmon.jar
     
   3. (in a separate command prompt/terminal) register the event receiver and
     write events to stdout:
   
     >engine_receive
   
   4. (in a separate command prompt/terminal) send the events:
   
     >engine_send dos-feed.evt

     
SAMPLE OUTPUT

   The output of the engine_receive window should match the sample_output.txt
   file (ignoring BATCH tags).
    
   (Note that since this sample is time-dependent, the exact output may vary 
   slightly if the machine is loaded)
   
