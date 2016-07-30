/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.net.URL;
import java.io.*;
import com.agentrj.core.agents.ref.*;
import com.agentrj.core.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface AgentContext
{
	public Agent createAgent(AgentRef agentRef,Object init)throws IOException,ClassNotFoundException,InstantiationException;
	public Agent createAgent(String classname,Object init)throws IOException,ClassNotFoundException,InstantiationException;
																		   
	public AgentProxy getAgentProxy(String id);
	public IHome getHome();
	public AgentClassLoader getAgentClassLoader();
}
