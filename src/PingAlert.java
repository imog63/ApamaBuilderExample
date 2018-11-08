/*
 * PingAlert.java
 *
 * Class to abstract an Apama ping alert event. A ping alert event is used 
 * to notify the number of pings received from the pinging source in the 
 * last time interval. A ping alert is only generated if the number of 
 * pings received in the time interval is greater than a given value
 *
 * $Copyright(c) 2002,  2004 - 2006 Progress Software Corporation. All Rights Reserved.$ 
 * $Copyright (c) 2013 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$ 
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 
 *
 * $Revision: 249541 $ 
 */

import com.apama.jmon.Event;


public class PingAlert extends Event {
    /** the source address of the pinging host */
    public String source;
    
    /** the number of pings received */
    public long count;


    /**
     * No argument constructor
     */ 
    public PingAlert() {
	this("", 0);
    }


    /**
     * Construct a ping alert object and set the instance variables
     * 
     * @param source       the source address of the pinging host 
     * @param count        the number of pings received
     */
    public PingAlert(String source, long count) {
	this.source = source;
	this.count = count;
    }
}

