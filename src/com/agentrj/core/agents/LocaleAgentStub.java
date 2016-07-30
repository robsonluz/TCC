/*
 * Created on 24/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.home.*;
import com.agentrj.core.agents.event.*;
import com.agentrj.core.agents.system.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LocaleAgentStub extends AgentStub
{
	public LocaleAgentStub(IHome home) 
	{
		super(home);
	}

	public final AgentProxy dispatch(HomeInfo target,Agent agen) throws Exception 
	{
		AgentProxy proxy = new AgentProxyImpl(home,new AgentAddress(home.getHomeInfo(),agen.getInfo().getId()));
		return dispatch(target,agen,proxy);
	}

	public final AgentProxy dispatch(HomeInfo target,Agent agen,AgentProxy agentDispatcher)throws Exception 
	{
		AgentProxy proxy = new AgentProxyImpl(getAgentContext().getHome(), new AgentAddress(target,agen.getInfo().getId()));
		MobilityEvent event = new MobilityEvent(proxy,MobilityEvent.DISPATCHING);
		super.agent.dispatchEvent(event);
		return home.transport(target,agen,agentDispatcher);
	}

	public final void dispose()
	{
		try{
			home.getMessageManager().sendMessage(new MessageSystem(MessageSystem.DISPOSE),new AgentAddress(home.getHomeInfo(),agent.getInfo().getId()));
		}catch(Exception e){
		}
		//agent.onDisposing();
		//home.disposeAgent(agent);
	}
	
	public final AgentContext getAgentContext()
	{
		return home.getAgentContext();
	}

}