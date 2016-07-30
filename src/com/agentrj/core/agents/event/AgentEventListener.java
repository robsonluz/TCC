/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.event;
import java.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AgentEventListener implements MobilityListener 
{
	private Vector vector = new Vector();
	
	public AgentEventListener(MobilityListener l1, MobilityListener l2) 
	{
	   vector.addElement(l1);
	   vector.addElement(l2);
	}
	
	public void addMobilityListener(MobilityListener listener) {
		if (vector.contains(listener)) {
			return;
		} 
		vector.addElement(listener);
	}
		
	public void onArrival(MobilityEvent event) 
	{
		Enumeration e = vector.elements();

		while (e.hasMoreElements()) {
			((MobilityListener)e.nextElement()).onArrival(event);
		} 
	}

	public void onDispatching(MobilityEvent event) 
	{
		Enumeration e = vector.elements();

		while (e.hasMoreElements()) {
			((MobilityListener)e.nextElement()).onDispatching(event);
		} 
	}

	public void onReverting(MobilityEvent event) 
	{
		Enumeration e = vector.elements();

		while (e.hasMoreElements()) {
			((MobilityListener)e.nextElement()).onReverting(event);
		} 
	}
}
