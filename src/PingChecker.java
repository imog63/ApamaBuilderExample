/*
 * PingChecker.java
 *
 * Simple java class to monitor pings from a source more closely. Class instantiations
 * keep a record of the number of pings received from the source in each time interval. 
 * On expiry of the time interval, a notification is sent out if the number of pings
 * exceeds a defined amount (set at 10). If there were no pings during the time interval, 
 * monitoring of the source is ceased, the source is removed from the pingers hashmap in 
 * the factory, and the object killed.
 *
 * $Copyright(c) 2002 - 2006 Progress Software Corporation. All Rights Reserved.$ 
 * $Copyright (c) 2013 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.$ 
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 
 *
 * $Revision: 249541 $
 */

import com.apama.jmon.*;


public class PingChecker {
    // the prepared event expression templates for detecting pings and producing the internal clock
    private static PreparedEventExpressionTemplate timeTemplate = new PreparedEventExpressionTemplate("all wait(1.0)");
    private static PreparedEventExpressionTemplate pingTemplate = new PreparedEventExpressionTemplate("all com.apama.samples.dos.Ping(?)");

    // the maximum number of allowed pings before a notification is made 
    private static int maxPings = 10;

    // the ping event defining which source to watch 
    private Ping ping;    

    // the number of pings received from the source in the current time interval
    private int pingCount = 0;   
 
    // reference to the factory monitor
    private Dos factory;
    
    // the event expressions and match listeners used in the class instantiation
    private PreparedEventExpression timeExpr = null;
    private PreparedEventExpression pingExpr = null;
    private TimeMatchListener timer; 
    private PingMatchListener pinger;



    /**
     * Inner class to respond to expiry of the time interval. The method checks the number
     * of ping events received in the time interval. If the number exceeds the maximum
     * allowed, a notification is made. If the number is zero, the source is assumed
     * to no longer pose a threat and the object is killed after removing the source
     * from the pingers hashmap in the factory
     */
    class TimeMatchListener implements MatchListener {
        public void match(MatchEvent event) {
	    if (pingCount > maxPings){
		PingAlert alert = new PingAlert(ping.source, pingCount);
		alert.emit();
	    }
	    else if (pingCount == 0){
		factory.removePinger(ping.source);
		die();
	    }
	    pingCount = 0;
        }
    }



    /**
     * Inner class to respond to ping events from designated source. The match method
     * increments the ping count. The ping count is zeroed when each time interval expires
     */
    class PingMatchListener implements MatchListener {
        public void match(MatchEvent event) {
	    pingCount++;
        }
    }



    /** 
     * Construct an instantiation of the class to monitor pings from a source more 
     * closely. The constructor sets up the prepared event expressions by getting 
     * an instance of the relevant template, and where appropriate setting the index
     * fields. It also creates the match listeners and attaches them to the event
     * expressions.
     *  
     * @param ping     The ping event used to start the close monitoring
     */
    public PingChecker(Ping ping, Dos factory) {
	this.ping = ping;
	this.factory = factory;
	
	// get an instance of the timing event expression to produce a periodic clock 
	// tick with interval 1.0 secs. Add the timing match listener to the expression
        timer = new TimeMatchListener();
        timeExpr = timeTemplate.getInstance();
        timeExpr.addMatchListener(timer); 

	// get an instance and set the zero index field of the ping expression to capture
	// all pings from this source. Add the ping match listener to the expression
        pinger = new PingMatchListener();
        pingExpr = pingTemplate.getInstance();
	pingExpr.setString(0, ping.source);
        pingExpr.addMatchListener(pinger);
    }



    // private method used to remove all match listeners attached to event 
    // expressions created in this object, thus making the object available 
    // for garbage collection
    private void die() {
	if (timeExpr != null) timeExpr.removeMatchListener(timer);
	if (pingExpr != null) pingExpr.removeMatchListener(pinger);
    }

}





