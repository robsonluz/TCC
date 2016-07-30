/*
 * Created on 03/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.router;
import java.net.*;
import java.util.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.router.*;

/**
 * @author Adminsitrador
 */

public class RouterTelnet extends Thread
{
	private int port;
	private ServerSocket serverSocket;
	private boolean running=true;
	private Vector vCon;

	/**
	 * 
	 * @uml.property name="writer"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Writter writer;

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkServer router;

	/**
	 * 
	 * @uml.property name="info"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private RouterInfo info;

	
	public RouterTelnet(ITalkServer router,RouterInfo info,Writter writer,int port)
	{
		this.info = info;
		this.router = router;
		this.writer = writer;
		this.port = port;
		vCon = new Vector(0);
	}
	
	public void run()
	{
		try{
			writer.println("Iniciando servidor telnet na porta "+this.port);
			this.serverSocket = new ServerSocket(port);
			writer.println("Servidor telnet iniciado com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			writer.printlnErr("Nao foi possivel iniciar o telnet");
			return;
		}
		try{
			while(running){
				RouterTelnetComunication thread = new RouterTelnetComunication(this,router,info,writer,serverSocket.accept());
				synchronized(vCon){
					thread.start();
					vCon.add(thread);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void removeCliente(RouterTelnetComunication cli)
	{
		synchronized(vCon){
			vCon.remove(cli);
		}
	}

	/**
	 * @return
	 * 
	 * @uml.property name="running"
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param b
	 * 
	 * @uml.property name="running"
	 */
	public void setRunning(boolean b) {
		running = b;
		for (int i = 0; i < vCon.size(); i++) {
			((RouterTelnetComunication) vCon.get(i)).setRunning(b);
		}
	}

}
