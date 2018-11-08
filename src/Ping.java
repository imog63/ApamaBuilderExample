/*
 * Ping.java
 *
 * Class to abstract an Apama ping event. A ping event contains only the
 * source IP address of the pinging host.
 *
 * $Copyright(c) 2002,  2004 - 2006 Progress Software Corporation. All Rights Reserved.$ 
 * $Copyright (c) 2013 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$ 
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 
 *
 * $Revision: 249541 $ 
 */

import com.apama.jmon.Event;

public class Ping extends Event {
    /** the source address of the pinging host */
    public String source;


    /**
     * No argument constructor
     */ 
    public Ping() {
	this("");
    }


    /**
     * Construct a ping object and set the instance variables
     * 
     * @param source       the source address of the pinging host 
     */
    public Ping(String source) {
	this.source = source;
    }
    
}

