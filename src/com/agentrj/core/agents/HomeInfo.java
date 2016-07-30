/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.net.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public interface HomeInfo 
{

	/**
	 * 
	 * @uml.property name="uRL"
	 */
	public void setURL(URL url);

	/**
	 * 
	 * @uml.property name="name"
	 */
	public void setName(String name);

	/**
	 * 
	 * @uml.property name="host"
	 */
	public void setHost(String host);

	/**
	 * 
	 * @uml.property name="port"
	 */
	public void setPort(int port);

	/**
	 * 
	 * @uml.property name="port"
	 */
	public int getPort();

	/**
	 * 
	 * @uml.property name="name"
	 */
	public String getName();

	/**
	 * 
	 * @uml.property name="host"
	 */
	public String getHost();

	/**
	 * 
	 * @uml.property name="uRL"
	 */
	public URL getURL();

}
