/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import java.io.Serializable;

import com.agentrj.core.agents.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public final class AgentProxyImpl implements AgentProxy,Serializable
{

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private transient IHome home;

	/**
	 * 
	 * @uml.property name="agentLocale"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentAddress agentLocale;

	/**
	 * 
	 * @uml.property name="stub"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private transient AgentStub stub;

	
	public AgentProxyImpl(IHome home,AgentAddress agentLocale)
	{
		this.home = home;		
		this.agentLocale = agentLocale;
		this.stub = new RemoteAgentStub(home,this);	
	}

	public final boolean sendMessage(Message message) throws Exception
	{
		return home.getMessageManager().sendMessage(message,agentLocale);
	}

	public AgentProxy dispatch(HomeInfo target, Agent agent)throws Exception 
	{
		return stub.dispatch(target,agent);
	}

	public AgentProxy dispatch(HomeInfo target,Agent agent,AgentProxy agentDispatcher)throws Exception 
	{
		return stub.dispatch(target,agent,agentDispatcher);
	}	

	public void setLocaleHome(IHome home)
	{
		this.home = home;
	}

	/**
	 * 
	 * @uml.property name="agentLocale"
	 */
	public AgentAddress getAgentLocale() {
		return this.agentLocale;
	}

	public void dispose() throws Exception
	{
		stub.dispose();
	}
}