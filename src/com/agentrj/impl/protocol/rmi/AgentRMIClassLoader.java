 /*
 * Created on 05/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.protocol.rmi;
import java.net.MalformedURLException;
import java.rmi.server.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.*;

/**
 * @author Adminsitrador
 */

public class AgentRMIClassLoader extends RMIClassLoaderSpi
{

	/**
	 * 
	 * @uml.property name="loader"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentClassLoader loader;

	private RMIClassLoaderSpi provider;
	
	public AgentRMIClassLoader()
	{
		loader = AgentRuntime.getAgentRuntime().getAgentClassLoader();
		provider = RMIClassLoader.getDefaultProviderInstance();
	}
		
	public String getClassAnnotation(Class cl) 
	{
		return provider.getClassAnnotation(cl);
	}

	public ClassLoader getClassLoader(String codebase)throws MalformedURLException 
	{
		return loader;
	}

	public Class loadClass(String codebase,String name,ClassLoader defaultLoader)throws MalformedURLException, ClassNotFoundException 
	{
		return loader.loadClass(name);
	}

	public Class loadProxyClass(String codebase,String[] interfaces,ClassLoader defaultLoader)throws MalformedURLException, ClassNotFoundException 
	{
		return provider.loadProxyClass(codebase,interfaces,defaultLoader);
	}
}