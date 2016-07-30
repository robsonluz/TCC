/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import java.net.URL;
import java.rmi.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.router.*;
import com.agentrj.core.agents.ref.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ITalkClient extends Remote
{
	public void updateAgentsRef(AgentRef agentRef[])throws RemoteException;
	public void doJobAgent(Agent agent)throws RemoteException;
	public void receptAgent(Agent agent,AgentProxy agentDispatcher)throws RemoteException;
	public void setRouter(ITalkServer router)throws RemoteException;
	public boolean sendMessage(Message message,AgentAddress target)throws RemoteException;
	public void doJobAgent(AgentRef agentRef,Object init)throws RemoteException;
}