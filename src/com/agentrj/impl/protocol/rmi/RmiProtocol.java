/*
 * Created on 29/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.protocol.rmi;

import java.net.URL;

import com.agentrj.core.home.ITalkClient;
import com.agentrj.core.protocol.*;
import java.rmi.*;
import java.rmi.registry.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public final class RmiProtocol extends Protocol
{
	private static final String MODEL_URL = "rmi://[host]:[port]/[Service Name]";
	private static final String MODEL_PROTOCOL = "rmi";

	/**
	 * 
	 * @uml.property name="talkClient"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkClient talkClient;

	private URL url;

	protected final void initProtocol(ITalkClient talkClient, URL url)throws Exception 
	{
		if(talkClient == null)
			throw new Exception("ITalkClient cannot be null!");
		if(url == null)
			throw new Exception("Url cannot be null!");
			
		this.talkClient = talkClient;
		this.url = url;
		checkURL();
		init();		
	}
	
	private void init()throws Exception
	{
		Registry registry = initRmiRegistry(url.getPort());
		Naming.rebind(url.toString(),talkClient);
	}
	
	private Registry initRmiRegistry(int port)throws Exception
	{
		System.out.println(port);
		if(port<1)
			return LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		return LocateRegistry.createRegistry(port);
	}
	
	private void checkURL()throws Exception
	{
		if(!url.getProtocol().toUpperCase().equals(MODEL_PROTOCOL.toUpperCase()))
			throw new Exception("Protocol "+url.getProtocol()+" Invalid!");
	}

	public final String getModelURL() 
	{
		return MODEL_URL;
	}

	/**
	 * 
	 * @uml.property name="talkClient"
	 */
	public final ITalkClient getTalkClient() {
		return talkClient;
	}

	public final URL getURL() 
	{
		return url;
	}
}

