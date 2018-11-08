/*
 * Dos.java
 *
 * This sample java monitor is used to monitor all ping events received. Pings
 * from new sources cause a worker monitor, the PingChecker, to be instantiated
 * to watch pings from that source more closely. When pings from the source fall 
 * to zero in a given time interval (set to 1.0 secs), the PingChecker for that 
 * source is removed and the source is deemed to no longer be a potential threat.
 *
 * $Copyright(c) 2002, 2008 Progress Software Corporation (PSC). All rights reserved.$
 * $Copyright (c) 2013 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG
 *
 * $Revision: 249541 $
 */

import com.apama.jmon.*;
import java.util.HashMap;


public class Dos implements Monitor, MatchListener {    
    /** A hashmap of sources to monitor for DOS attacks */
    private HashMap<String,Integer> pingers = new HashMap<String,Integer>();
    
    
    /**
     * No argument constructor used by the jmon framework on 
     * application loading
     */
    public Dos() {}
    

    /**
     * Implementation of the monitor interface onLoad method. Sets up 
     * a single event expression looking for all ping events. When a 
     * ping event from a new source is seen a ping checker is created 
     * to monitor pings from that source more closely. The method updates
     * the pingers hashmap to keep a track of sources being monitored more
     * closely
     */
    public void onLoad() {   
	EventExpression pingExpr = new EventExpression("all com.apama.samples.dos.Ping():ping");
	pingExpr.addMatchListener(this);
    } 

     
    /**
     * Implementation of the MatchListener interface match method. Extracts the
     * ping event that caused the event expression to trigger, and adds the source
     * into the pingers hashmap if it does not allready exist
     * 
     * @param event       the match against the event expression
     */
    public void match(MatchEvent event) {
	Ping ping = (Ping)event.getMatchingEvents().get("ping");
	if (!pingers.containsKey(ping.source)){
	    pingers.put(ping.source, new Integer(1));
	    new PingChecker(ping, this);
	}
    }


    /**
     * Method to remove a pinger from the pingers hashmap if it exists in the map.
     * Used by ping checkers when the number of pings in the time interval falls to 
     * zero
     * 
     * @param source       the source IP address of the ping event
     */
    public void removePinger(String source) {
	if (pingers.containsKey(source))
	    pingers.remove(source);
    }

}



