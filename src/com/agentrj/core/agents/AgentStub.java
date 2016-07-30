/*
 * Created on 24/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.io.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public abstract class AgentStub 
{

	/**
	 * 
	 * @uml.property name="agent"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	protected Agent agent;

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	protected IHome home;

	
	public AgentStub(IHome home)
	{
		this.home = home;
	}

	/**
	 * 
	 * @uml.property name="agent"
	 */
	public final void setAgent(Agent agent) {
		this.agent = agent;
	}

	
	
	abstract public AgentProxy dispatch(HomeInfo target,Agent agent)throws Exception;
	abstract public AgentProxy dispatch(HomeInfo target,Agent agent,AgentProxy agentDispatcher)throws Exception;
	
	abstract public void dispose()throws Exception;
	
	abstract public AgentContext getAgentContext();
}
