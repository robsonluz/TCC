/*
 * Created on 24/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.agents.system.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class RemoteAgentStub extends AgentStub
{

	/**
	 * 
	 * @uml.property name="proxy"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentProxy proxy;

	public RemoteAgentStub(IHome home,AgentProxy proxy) 
	{
		super(home);
		this.proxy = proxy;
	}

	public AgentProxy dispatch(HomeInfo target, Agent agent)throws Exception 
	{
		proxy.sendMessage(new MessageSystem(MessageSystem.DISPATCH));
		return null;
	}
	
	public AgentProxy dispatch(HomeInfo target,Agent agent,AgentProxy agentDispatcher)throws Exception 
	{
		return null;
	}

	public void dispose() throws Exception
	{
		proxy.sendMessage(new MessageSystem(MessageSystem.DISPOSE));
	}

	public AgentContext getAgentContext() 
	{
		return home.getAgentContext();
	}
}
