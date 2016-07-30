/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.database.DataManager;
import com.agentrj.core.home.security.HomeSecurityManager;

import java.net.*;
import java.io.*;
import java.security.*;
import com.agentrj.core.util.*;
import com.agentrj.core.home.database.*;

/**
 * @author Adminsitrador
 */

public class AgentRuntimeHome extends AgentRuntime 
{

	/**
	 * 
	 * @uml.property name="dataManager"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static DataManager dataManager;

	protected AgentRuntimeHome()throws Exception
	{
		init();
	}
	
	public static AgentRuntime createAgentRuntime()throws Exception
	{
		if(agentRuntime!=null)
			throw new Exception("AgentRuntime has been initialized!");
		agentRuntime = new AgentRuntimeHome();
		return getAgentRuntime();
	}
	
	
	private void init()throws Exception
	{
		URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
			public URLStreamHandler createURLStreamHandler(String protocol) {
				return new URLStreamHandler() {
					protected URLConnection openConnection(URL u)throws IOException 
					{
						return new AgentURLConnection(u);
					};
				};
			}
		});
		
		initProperties();
		initSecurityManager();
		
		//Init ClassLoader for System Agents
		agentClassLoader = new AgentClassLoaderImpl();
		System.out.println("Iniciando DataManager");
		dataManager = new DataMangerImpl();
	}
	
	private void initProperties()
	{
		System.setProperty("java.rmi.server.RMIClassLoaderSpi","com.agentrj.impl.protocol.rmi.AgentRMIClassLoader");
	}
	
	private void initSecurityManager()throws Exception
	{
		/*Policy.setPolicy(new Policy() {
			public PermissionCollection getPermissions(CodeSource codesource) {
				System.out.println("getPermissions: "+codesource);
				return null;
			}

			public void refresh() {
				System.out.println("refresh");

			}
		});*/
		System.setSecurityManager(new HomeSecurityManager());
	}

	/**
	 * 
	 * @uml.property name="dataManager"
	 */
	public DataManager getDataManager() {
		return dataManager;
	}

}
