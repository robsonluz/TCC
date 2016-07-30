/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router;

import com.agentrj.core.agents.*;
import com.agentrj.core.agents.ref.AgentRef;
import com.agentrj.core.agents.ref.RefManager;

import java.util.*;
import java.io.*;
import java.net.*;
/**
 * @author Adminsitrador
 *
 */
public class AgentClassLoaderImpl extends AgentClassLoader
{
	private static Hashtable classes = new Hashtable();
	private String pathAgent;
	private final static String EXT_AGENT = ".jar";

	public AgentClassLoaderImpl(String pathAgent) 
	{
		this.pathAgent = pathAgent;
	}
	
	public AgentClassLoaderImpl()
	{
		this.pathAgent = "\\lib\\agents\\";
	}
	
	public void reset()
	{
		synchronized(classes)
		{
			classes = null;
			classes = new Hashtable();
		}
	}
	
	private Class loadAgentFromFile(AgentRef agentRef)
	{
		Class result = null;
		try {
			File file = new File(System.getProperty("agentrj.home")+pathAgent+agentRef.getName()+EXT_AGENT);
			if(file.exists())
			{
				String tmp = "file:///"+file.getAbsolutePath();
				//URL url = file.toURL();
				URL url = new URL(tmp);
				System.out.println(url.toString());
				
				ClassLoader loaderURL = new URLClassLoader(new URL[]{url});
				result = loaderURL.loadClass(agentRef.getClassName());
				
				//result = RMIClassLoader.loadClass(url,agentRef.getClassName());
				
				if (result != null) {
					System.out.println("Agent "+agentRef.getClassName()+" read from "+file.getAbsolutePath()+"!");
					classes.put(agentRef.getClassName(),result);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public synchronized Class loadAgent(AgentRef agentRef)throws ClassNotFoundException
	{
		
		Class result=null;
		synchronized(classes)
		{
			result = (Class)classes.get(agentRef.getClassName());
		}
		if (result != null) {
			System.out.println("Class "+agentRef.getClassName()+" read from memory!");
			return result;
		}

		result = loadAgentFromFile(agentRef);
		if(result!=null)
			return result;

		/* Check with the primordial class loader */
		try {
			System.out.println(agentRef.getClassName());
			result = super.findSystemClass(agentRef.getClassName());
			return result;
		} catch (ClassNotFoundException e) {
			throw e;
		}

	}
	
	
	private synchronized Class findAgent(String className)
	{
		AgentRef ref = RefManager.findAgentRef(className);
		System.out.println("REF: "+ref);
		if(ref!=null)
		{
			try{
				Class c =loadAgentFromFile(ref);
				System.out.println("Class: "+c); 
				return c;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public synchronized Class loadClass(String name) throws ClassNotFoundException 
	{
		Class result;
		synchronized(classes)
		{
			result = (Class)classes.get(name);
			if (result != null) {
				System.out.println("Class "+name+" read from memory!");
				return result;
			}
		}
		
		try{
			return super.loadClass(name);
		}catch(Exception e){
		}
		
		result = findAgent(name);
		if(result!=null)
			return result;
		else
			throw new ClassNotFoundException(name);

		
	}
	
	protected synchronized Class loadClass(String name, boolean resolve)throws ClassNotFoundException 
	{
		Class result;
		synchronized(classes)
		{
			result = (Class)classes.get(name);
			if (result != null) {
				System.out.println("Class "+name+" read from memory!");
				return result;
			}
		}
		try{
			return super.loadClass(name,resolve);
		}catch(Exception e){
		}
		
		result = findAgent(name);
		if(result!=null)
			return result;
		else
			throw new ClassNotFoundException(name);
	}
	
	
	
	
	
	
	
	
	public synchronized Class loadClass(String className, URL url)throws ClassNotFoundException 
	{
		System.out.println("Agent class load "+className);
		Class result;
		byte  classData[];
		
		synchronized(classes)
		{
			result = (Class)classes.get(className);
		}
		
		if (result != null) {
			System.out.println("Class "+className+" read from memory!");
			return result;
		}
		
		try {
			ClassLoader loaderURL = new URLClassLoader(new URL[]{url});
			result = loaderURL.loadClass(className);
		}catch(Exception e){
		}
		
		if (result != null) {
			System.out.println("Class "+className+" read from "+url.toString());
			classes.put(className,result);
			return result;
		}
		

		/* Check with the primordial class loader */
		try {
			result = super.findSystemClass(className);
			return result;
		} catch (ClassNotFoundException e) {
		}

		throw new ClassNotFoundException();
	}


}
