/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.router;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteServer;

import sun.rmi.registry.RegistryImpl;

import com.agentrj.core.agents.AgentRuntime;
import com.agentrj.core.router.*;
import com.agentrj.core.util.*;
import java.net.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class RouterServer
{

	/**
	 * 
	 * @uml.property name="info"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private RouterInfo info;

	/**
	 * 
	 * @uml.property name="write"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Writter write;

	/**
	 * 
	 * @uml.property name="telnet"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private RouterTelnet telnet = null;

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkServer router;

	
	public RouterServer()
	{
		write = new Writter();
	}
	
	public void init()
	{
		try{
			URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
				public URLStreamHandler createURLStreamHandler(String protocol) {
					return new URLStreamHandler() {
						protected URLConnection openConnection(URL u)
							throws IOException {
								return new AgentURLConnection(u);
						}
					};
				}
			});
			AgentRuntime run = AgentRuntimeRouter.createAgentRuntime();
			write.println("Iniciando o roteador de agentes...");
			write.println("Carregando as configuracoes...");
			info = new RouterInfo();
			info.checkFileInfo();
			initRmiRegistry(info.getPort());
			write.println("Iniciando tarefas do roteador...");
			write.println("- Iniciando TalkServer...");
			initTalkServer();
			write.println("- TalkServer iniciando com sucesso!");
			checkTelnet();
			write.println("Roteador iniciado com sucesso!");
		}catch(Exception e){
			
			write.printlnErr(e);
		}
	}
	
	private void checkTelnet()
	{
		if(info.getTelnet()!= null && info.getTelnet().equals("yes"))
		{
			telnet = new RouterTelnet(router,info,write,info.getPortTelnet());
			telnet.start();
		}
	}
	
	private void initRmiRegistry(int port)throws Exception
	{
		write.println("Iniciando rmiregistry na porta "+port+" ...");
		Registry regs = LocateRegistry.createRegistry(port);
	}
	
	private void initTalkServer()throws Exception
	{
		router = new TalkServer();
		Naming.rebind(info.getURL(),router);
	}
	
	public void end()
	{
		write.println("Finalizando o routeador...");
		write.println("Finalizado com sucesso!");
	}
	
	public static void main(String args[])
	{
		Console console = new Console("Router");

		//RouterServer server = new RouterServer();
		//server.init();
	}

	/**
	 * 
	 * @uml.property name="info"
	 */
	public RouterInfo getInfo() {
		return info;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="write"
	 */
	public Writter getWrite() {
		return write;
	}

	/**
	 * @param writter
	 * 
	 * @uml.property name="write"
	 */
	public void setWrite(Writter writter) {
		write = writter;
	}

}