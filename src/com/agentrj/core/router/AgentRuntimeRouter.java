/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router;
import com.agentrj.core.agents.AgentRuntime;
import com.agentrj.core.agents.database.DataManager;
import com.agentrj.core.router.database.DataMangerImpl;

/**
 * @author Adminsitrador
 */

public class AgentRuntimeRouter extends AgentRuntime 
{

	/**
	 * 
	 * @uml.property name="dataManager"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static DataManager dataManager;

	protected AgentRuntimeRouter()throws Exception
	{
		agentClassLoader = new AgentClassLoaderImpl();
		System.out.println("Iniciando o DataManager");
		dataManager = new DataMangerImpl();
	}
	
	public static AgentRuntime createAgentRuntime()throws Exception
	{
		if(agentRuntime!=null)
			throw new Exception("AgentRuntime has been initialized!");
		agentRuntime = new AgentRuntimeRouter();
		return getAgentRuntime();
	}

	/**
	 * 
	 * @uml.property name="dataManager"
	 */
	public DataManager getDataManager() {
		return dataManager;
	}

}