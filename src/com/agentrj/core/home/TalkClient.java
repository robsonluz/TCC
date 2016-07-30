/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.event.MobilityEvent;
import com.agentrj.core.agents.ref.AgentRef;
import com.agentrj.core.agents.ref.RefManager;
import com.agentrj.core.router.ITalkServer;
import com.agentrj.core.agents.system.*;
import java.net.URL;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class TalkClient extends UnicastRemoteObject implements ITalkClient
{

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkServer router;

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private IHome home;

	
	public TalkClient(IHome home)throws RemoteException
	{
		super();
		this.home = home;
	}
	
	public void doJobAgent(Agent agent)throws RemoteException
	{
		agent.setStub(new LocaleAgentStub(home));
		addAgent(agent);
	}
	
	public void receptAgent(Agent agent,AgentProxy agentDispacher) throws RemoteException 
	{
		agent.setStub(new LocaleAgentStub(home));
		AgentProxy proxy = agentDispacher;
		proxy.setLocaleHome(home);
		agent.dispatchEvent(new MobilityEvent(proxy,MobilityEvent.ARRIVAL));
		addAgent(agent);
	}
	
	public void doJobAgent(AgentRef agentRef, Object init)throws RemoteException 
	{
		try{
			home.doJobAgent(agentRef,init);
		}catch(Exception e){
			throw new RemoteException(e.toString(),e.fillInStackTrace());
		}
	}
	
	private synchronized void addAgent(Agent agent)
	{
		AgentThread tw = new AgentThread(agent);
		home.getAgents().add(tw);
		tw.start();
		tw.handleMessage(new MessageSystem(MessageSystem.RUN));
	}

	/**
	 * 
	 * @uml.property name="router"
	 */
	public ITalkServer getRouter() {
		return router;
	}

	/**
	 * 
	 * @uml.property name="router"
	 */
	public void setRouter(ITalkServer server) throws RemoteException {
		router = server;
	}

	public boolean sendMessage(Message message, AgentAddress target)throws RemoteException 
	{
		return home.getMessageManager().handleMessage(message,target);
	}

	public void updateAgentsRef(AgentRef[] agentRef) throws RemoteException 
	{
		for(int i=0;i<agentRef.length;i++){
			home.updateAgentRef(agentRef[i]);
			RefManager.addAgentRef(agentRef[i]);			
		}
		home.getAgentContext().getAgentClassLoader().reset();
	}
}