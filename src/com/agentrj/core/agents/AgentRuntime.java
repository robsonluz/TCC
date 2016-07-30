/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.agents.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.database.*;

/**
 * @author Adminsitrador
 */

public abstract class AgentRuntime
{

	/**
	 * 
	 * @uml.property name="agentRuntime"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	protected static AgentRuntime agentRuntime = null;

	/**
	 * 
	 * @uml.property name="agentClassLoader"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	protected AgentClassLoader agentClassLoader = null;

/**
 * 
 * @uml.property name="agentRuntime"
 */
/*	public static AgentRuntime createAgentRuntime()throws Exception
 {
 if(agentRuntime!=null)
 throw new Exception("AgentRuntime has been initialized!");
 agentRuntime = new AgentRuntime();
 return getAgentRuntime();
 }*/

public final static AgentRuntime getAgentRuntime() {
	return agentRuntime;
}

	/**
	 * 
	 * @uml.property name="agentClassLoader"
	 */
	public final AgentClassLoader getAgentClassLoader() {
		return agentClassLoader;
	}

	
	public abstract DataManager getDataManager();
}
