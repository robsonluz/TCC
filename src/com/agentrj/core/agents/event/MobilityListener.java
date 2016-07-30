/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.event;

/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface MobilityListener extends java.util.EventListener, 
										  java.io.Serializable {

	/**
	 * Invoked just after the aglet arrived at the destination
	 */
	public void onArrival(MobilityEvent event);
	/**
	 * Invoked when the aglet is attempted to dispatch.
	 */
	public void onDispatching(MobilityEvent event);
	/**
	 * Invoked when the aglet is retracted.
	 */
	public void onReverting(MobilityEvent event);
}

