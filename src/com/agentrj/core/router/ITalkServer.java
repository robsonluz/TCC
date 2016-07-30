/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router;
import java.rmi.*;

import com.agentrj.core.agents.*;
import com.agentrj.core.agents.ref.*;
import com.agentrj.core.home.*;
import java.io.*;
import java.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ITalkServer extends Remote
{
	public void registryHome(HomeInfo home)throws RemoteException;
	public boolean requestWork(HomeInfo home,Agent agent)throws RemoteException;
	public boolean requestWork(HomeInfo target,AgentRef agentRef,Object init)throws RemoteException;
	public void requestWork(HomeInfo target,java.net.URL url,String agentClass,Object init)throws RemoteException;
	public Object transport(HomeInfo target,Agent agent,AgentProxy agentDispatcher)throws RemoteException;
	public boolean sendMessage(Message message,AgentAddress target)throws RemoteException;
	public byte[] getAgentStream(AgentRef agentRef)throws RemoteException;
	public boolean updateAgentRef(AgentRef agentRef,byte bytes[])throws RemoteException;
	public Enumeration getHomes()throws RemoteException;
}