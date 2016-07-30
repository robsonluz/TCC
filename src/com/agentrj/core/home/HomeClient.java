/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;

import com.agentrj.core.router.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.protocol.*;

import java.io.IOException;
import java.net.*;
import com.agentrj.impl.home.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class HomeClient
{

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	ITalkServer router;

	public HomeClient() {
		super();
	}

	public HomeClient(String args[])
	{
		String fileConf = args[0];
		try{
			AgentRuntime runtime = AgentRuntimeHome.createAgentRuntime();
			
			String hostRouter = ConfHome.getProperty("router.url");
			String protocolClass = ConfHome.getProperty("protocol.class");
			HomeInfo homeInfo = new HomeInfoImpl(new URL(ConfHome.getProperty("home.url")));
			 
			System.out.println("Iniciando Objeto RObject..");
			System.out.println("Procurando Roteador...");
			router = (ITalkServer) Naming.lookup(hostRouter);
			
			IHome homeServer = new HomeImpl(router);
			homeServer.setHomeInfo(homeInfo);
			
			
			ITalkClient iClient = new TalkClient(homeServer);
			Protocol protocol = Protocol.createProtocol(protocolClass,iClient,homeInfo.getURL());
			System.out.println("Registrando Home no Roteador...");
			router.registryHome(homeInfo);
			iClient.setRouter(router);
			System.out.println("Home "+homeInfo+" iniciado com sucesso!");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		Console console = new Console("Home - "+args[0]);
		new HomeClient(args);
	}
}