/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.agents.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface AgentProxy
{
	public boolean sendMessage(Message message)throws Exception;
	public AgentProxy dispatch(HomeInfo target, Agent agent)throws Exception;
	public AgentProxy dispatch(HomeInfo target, Agent agent,AgentProxy agentDispatcher)throws Exception;
	public void dispose()throws Exception; 
	public void setLocaleHome(IHome home);
	public AgentAddress getAgentLocale();
}
