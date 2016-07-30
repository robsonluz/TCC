/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import java.io.IOException;
import java.net.URL;
import java.beans.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.event.*;
import com.agentrj.core.agents.ref.*;
import java.util.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public final class AgentContextImpl implements AgentContext
{

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private IHome home;

	/**
	 * 
	 * @uml.property name="loader"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentClassLoader loader;

	
	public AgentContextImpl(IHome home)
	{
		this.home = home;
		this.loader = AgentRuntime.getAgentRuntime().getAgentClassLoader();
	}
	
	public final Agent createAgent(AgentRef agentRef, Object init)throws IOException, ClassNotFoundException, InstantiationException 
	{
		if(!checkAgentRef(agentRef))
			throw new ClassNotFoundException("Agent nao encontrado");
		try{	
			Agent agent = (Agent) loader.loadAgent(agentRef).newInstance();
			agent.setStub(new LocaleAgentStub(home));
			agent.getInfo().setId(generateKey());
			agent.getInfo().setClassName(agent.getClass().getName());
			agent.getInfo().setHomeInfo(home.getHomeInfo());
			agent.onCreation(init);

			return agent;
		}catch(ClassCastException e){
			e.printStackTrace();
			throw e;
		}catch(IllegalAccessException e){
			e.printStackTrace();
			throw new ClassCastException(e.toString());
		}
	}
	
	public final Agent createAgent(String classname, Object init)throws IOException, ClassNotFoundException, InstantiationException 
	{
		AgentRef ref = RefManager.findAgentRef(classname);
		if(ref!=null)
			return createAgent(ref,init);
		return null;
	}
	
	private boolean checkAgentRef(AgentRef agentRef)
	{
		AgentRef that = RefManager.getAgentRef(agentRef.getName());
		if(that != null && that.equals(agentRef))
		{
			return true;
		}
		boolean result = home.updateAgentRef(agentRef);
		if(result)
			RefManager.addAgentRef(agentRef); 
		return result;
	}

	/**
	 * 
	 * @uml.property name="home"
	 */
	public IHome getHome() {
		return this.home;
	}

	
	private String generateKey()
	{
		try{
			return String.valueOf(Math.random() * 10000);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "undefined";
	}

	public AgentProxy getAgentProxy(String id)
	{
		return new AgentProxyImpl(home, new AgentAddress(home.getHomeInfo(),id));	
	}
	
	public AgentClassLoader getAgentClassLoader()
	{
		return this.loader;
	}

}