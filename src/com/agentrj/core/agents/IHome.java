/*
 * Created on 22/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.ref.*;
import com.agentrj.core.router.*;
import java.util.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public interface IHome
{
	public AgentProxy transport(HomeInfo target,Agent agent,AgentProxy agentDispacher)throws Exception;
	public AgentContext getAgentContext();
	public MessageManager getMessageManager();
	public Vector getAgents();
	public void disposeAgent(Agent agent);
	public ITalkServer getRouter();

	/**
	 * 
	 * @uml.property name="homeInfo"
	 */
	public void setHomeInfo(HomeInfo homeInfo);

	/**
	 * 
	 * @uml.property name="homeInfo"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	public HomeInfo getHomeInfo();

	public void doJobAgent(AgentRef agentRef,Object init)throws Exception;
	public boolean updateAgentRef(AgentRef agentRef);
	//public void doJobAgent(java.net.URL url,String agentClass,Object init)throws Exception;
}
