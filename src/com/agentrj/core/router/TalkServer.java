/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router;
import java.net.URL;
import java.rmi.*;
import java.io.*;
import java.rmi.server.*;
import java.util.*;

import com.agentrj.core.agents.*;
import com.agentrj.core.agents.ref.*;
import com.agentrj.core.home.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TalkServer extends UnicastRemoteObject implements ITalkServer
{
	private Vector listHome;	
	public TalkServer()throws RemoteException 
	{
		super();
		listHome = new Vector(0);
	}

	public void registryHome(HomeInfo home)throws RemoteException
	{
		System.out.println("Nova Home: "+home.getURL().toString());
		listHome.add(home);
		updateAgents(home);
	}
	
	private void updateAgents(HomeInfo home)
	{
		try{
			ITalkClient obj = (ITalkClient) Naming.lookup(home.getURL().toString());
			obj.updateAgentsRef(RefManager.getAgentsRef());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public boolean requestWork(HomeInfo home, Agent agent)throws RemoteException
	{
		System.out.println("Requisicao de "+agent.getClass().getName()+"["+agent.getInfo().getId()+"] em "+home.getName());
		for(int i=0;i<listHome.size();i++)
		{
			HomeInfo h = (HomeInfoImpl) listHome.get(i);
			System.out.println(h);
			if(h.getName().equals(home.getName()))
			{
				try{
					System.out.println(home.getURL().toString());
					ITalkClient obj = (ITalkClient) Naming.lookup(home.getURL().toString());
					obj.doJobAgent(agent);
				}catch(Exception e){
					e.printStackTrace();
					System.out.println(e);
				}
				return true;
			}
		}
		return false;
	}
	
	public Object transport(HomeInfo target,Agent agent,AgentProxy agentDispacher)throws RemoteException
	{
		System.out.println("Tranporte");
		for(int i=0;i<listHome.size();i++)
		{
			HomeInfo h = (HomeInfo) listHome.get(i);
			System.out.println(target.getName());
			System.out.println(h.getName());
			if(h.getName().equals(target.getName()))
			{
				try{
					ITalkClient obj = (ITalkClient) Naming.lookup(h.getURL().toString());
					obj.receptAgent(agent,agentDispacher);
				}catch(Exception e){
					System.out.println(e);
					e.printStackTrace();
				}
				return new String("true");
			}
		}
		return new String("false");
	}

	public boolean sendMessage(Message message, AgentAddress target)throws RemoteException 
	{
		for(int i=0;i<listHome.size();i++)
		{
			HomeInfo h = (HomeInfo) listHome.get(i);
			if(h.getName().equals(target.getHome().getName()))
			{
				try{
					ITalkClient obj = (ITalkClient) Naming.lookup(h.getURL().toString());
					return obj.sendMessage(message,target); 
				}catch(Exception e){
					e.printStackTrace();
					System.out.println(e);
				}
			}
		}
		return false;
	}
	public void requestWork(HomeInfo target,URL url,String agentClass,Object init)throws RemoteException 
	{
		System.out.println("-----------------------");
		System.out.println("Requisicao de "+agentClass+" em "+target.getName());
		ITalkClient home = findHome(target);
		if(home!=null);
			//home.doJobAgent(url,agentClass,init);
			//Arrumar aqui
	}
	
	public boolean requestWork(HomeInfo target, AgentRef agentRef, Object init)	throws RemoteException 
	{
		System.out.println("-----------------------");
		System.out.println("Requisicao de "+agentRef.getClassName()+" em "+target.getName());
		ITalkClient home = findHome(target);
		if(home!=null);
			home.doJobAgent(agentRef,init);
		return true;
	}
	
	private ITalkClient findHome(HomeInfo home)
	{
		for(int i=0;i<listHome.size();i++)
		{
			HomeInfo h = (HomeInfoImpl) listHome.get(i);
			if(h.getName().equals(home.getName()))
			{
				try{
					return (ITalkClient) Naming.lookup(home.getURL().toString());
				}catch(Exception e){
					e.printStackTrace();
					System.out.println(e);
				}
			}
		}
		return null;
	}

	public byte[] getAgentStream(AgentRef agentRef) throws RemoteException 
	{
		//System.out.println("Stream requerido");
		AgentRef that = RefManager.getAgentRef(agentRef.getName());
		//if(that.equals(agentRef))
		//{
			File file = new File(System.getProperty("agentrj.home")+"\\lib\\agents\\"+agentRef.getName()+".jar");
			if(file.exists())
			{
				return getAgentFromFile(file);
			}
		//}	
		return null;
	}
	
	private byte[] getAgentFromFile(File file) 
	{
		byte result[];
		try {
			FileInputStream fi = new FileInputStream(file);
			result = new byte[fi.available()];
			fi.read(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveAgentToFile(File file,byte bytes[])throws Exception
	{
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(bytes);
		fo.close();
	}

	public boolean updateAgentRef(AgentRef agentRef,byte bytes[]) throws RemoteException 
	{
		System.out.println("Updating Agent ["+agentRef.getClassName()+"] in system");
		try{
			File file = new File(System.getProperty("agentrj.home")+"\\lib\\agents\\"+agentRef.getName()+".jar");
			saveAgentToFile(file,bytes);
		 	RefManager.addAgentRef(agentRef);
		 	AgentRuntime.getAgentRuntime().getAgentClassLoader().reset();

			for(int i=0;i<listHome.size();i++)
			{
				HomeInfo h = (HomeInfoImpl) listHome.get(i);
				try{
					System.out.println("Updating Agent ["+agentRef.getClassName()+"] in ["+h.getURL().toString()+"]");
					ITalkClient cli = (ITalkClient) Naming.lookup(h.getURL().toString());
					cli.updateAgentsRef(new AgentRef[]{agentRef});
					System.out.println("Update complete!");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			System.out.println("Agent ["+agentRef.getClassName()+"] updated!");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Enumeration getHomes() throws RemoteException 
	{
		return this.listHome.elements();
	}
}