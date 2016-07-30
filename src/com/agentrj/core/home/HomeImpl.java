/*
 * Created on 24/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.ref.AgentRef;
import com.agentrj.core.router.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.system.*;
import java.net.URL;
import java.util.*;
import java.io.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public final class HomeImpl implements IHome
{

	/**
	 * 
	 * @uml.property name="agentContext"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentContext agentContext;

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkServer router;

	private Vector listAgents;

	/**
	 * 
	 * @uml.property name="messageManager"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private MessageManager messageManager;

	/**
	 * 
	 * @uml.property name="homeInfo"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private HomeInfo homeInfo;

	
	public HomeImpl(ITalkServer router)
	{
		this.router = router;
		agentContext = new AgentContextImpl(this);
		listAgents = new Vector(0);
		messageManager = new MessageManagerImpl(this);
	}

	/**
	 * 
	 * @uml.property name="router"
	 */
	public ITalkServer getRouter() {
		return router;
	}

	
	public synchronized AgentProxy transport(HomeInfo target, Agent agent,AgentProxy agentDispacher)throws Exception 
	{
		HomeInfo home = new HomeInfoImpl(target.getName());
		AgentProxy proxy = new AgentProxyImpl(this, new AgentAddress(target,agent.getInfo().getId()));
		router.transport(target,agent,agentDispacher);
		return proxy;
	}

	/**
	 * 
	 * @uml.property name="agentContext"
	 */
	public AgentContext getAgentContext() {
		return this.agentContext;
	}


	public synchronized Vector getAgents() 
	{
		return this.listAgents;
	}
	
	public synchronized void disposeAgent(Agent agent)
	{
		for(int i=0;i<listAgents.size();i++)
		{
			AgentThread tw = (AgentThread) listAgents.get(i);
			if(tw.getAgent().equals(agent))
			{
				listAgents.remove(i);
				return;
			}
		}
		listAgents.remove(agent);
	}

	/**
	 * 
	 * @uml.property name="messageManager"
	 */
	public MessageManager getMessageManager() {
		return this.messageManager;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="homeInfo"
	 */
	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	/**
	 * @param home
	 * 
	 * @uml.property name="homeInfo"
	 */
	public void setHomeInfo(HomeInfo home) {
		homeInfo = home;
	}

	public void doJobAgent(AgentRef agentRef, Object init) throws Exception 
	{
		try{
			Agent agent = getAgentContext().createAgent(agentRef,init);
			AgentThread tw = new AgentThread(agent);
			listAgents.add(tw);
			tw.start();
			tw.handleMessage(new MessageSystem(MessageSystem.RUN));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean updateAgentRef(AgentRef agentRef) 
	{
		
		try{
			byte in[] = router.getAgentStream(agentRef);
			if(in == null || in.length == 0)
				return false;
			File file = new File(System.getProperty("agentrj.home")+"\\lib\\agents\\"+agentRef.getName()+".jar");
			FileOutputStream out = new FileOutputStream(file);
			out.write(in);
			out.close();
			System.out.println("Agent ["+agentRef.getClassName()+"] updated!");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}