/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import com.agentrj.core.agents.ref.*;
import java.net.*;
/**
 * @author Adminsitrador
 *
 */
public abstract class AgentClassLoader extends ClassLoader
{
	public abstract Class loadAgent(AgentRef agentRef)throws ClassNotFoundException;
	public abstract void reset();
	public abstract Class loadClass(String className, URL url)throws ClassNotFoundException;
	//public abstract Class loadClass(String name)throws ClassNotFoundException;
	//protected abstract Class loadClass(String name, boolean resolve);
}
