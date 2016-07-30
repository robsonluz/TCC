/*
 * Created on 09/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.util.*;
import com.agentrj.core.agents.system.*;

/**
 * @author Adminsitrador
 */

public final class AgentThread extends Thread
{

	/**
	 * 
	 * @uml.property name="agent"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Agent agent;

	private boolean work=true;
	private boolean sleep=false;
	private Vector vMessage;
	
	public AgentThread(Agent agent)
	{
		vMessage = new Vector(0);
		this.agent = agent;
	}
	
	public synchronized Message handleMessage(Message message)
	{
		synchronized(this)
		{
			synchronized(vMessage)
			{
				vMessage.add(message);
			}
			sleep = false;
			notify();
			return null;
		}
	}
	
	public void run() 
	{
		while(work)
		{
			Message message = getLastMessage();
			if(message!=null)
			{
				hMessage(message);
			}
		
			synchronized(this) 
			{	
				while(sleep)
				{
					try{
						wait();
					}catch(InterruptedException e){
					}
				}
			}
		}
	}
	
	private void hMessage(Message message)
	{
		if(message.sameKind(MessageSystem.RUN))
		{
			agent.doJob();
			return;
		}
		if(message.sameKind(MessageSystem.CREATE))
		{
			agent.onCreation(message.getArg());
			return;
		}
		if(message.sameKind(MessageSystem.DISPOSE))
		{
			agent.onDisposing();
			synchronized(this)
			{
				work = false;
			}
			return;
		}
		agent.handleMessage(message);
	}
	
	private Message getLastMessage()
	{
		synchronized(this)
		{
			synchronized(vMessage)
			{
				if(vMessage.size()>0)
				{
					Message m = (Message)vMessage.get(0);
					vMessage.remove(0);
					return m;
				}
			}
			sleep = true;
			return null;
		}
	}

	/**
	 * 
	 * @uml.property name="sleep"
	 */
	private void setSleep(boolean b) {
		synchronized (this) {
			sleep = b;
		}
	}

	/**
	 * 
	 * @uml.property name="agent"
	 */
	public Agent getAgent() {
		return agent;
	}

}