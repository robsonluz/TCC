/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.event;
import com.agentrj.core.agents.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RemoteMobilityListener implements MobilityListener
{
	private Object obj;
	public RemoteMobilityListener(Object obj)
	{
		this.obj = obj;
	}
	
	public void onArrival(MobilityEvent event) 
	{
		//agent.onCreation(event.getSource());
	}

	public void onDispatching(MobilityEvent event) 
	{
	}

	public void onReverting(MobilityEvent event) 
	{
	}
}
