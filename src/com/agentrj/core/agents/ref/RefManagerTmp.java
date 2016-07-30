/*
 * Created on 30/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.ref;
import com.agentrj.core.util.*;
import java.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RefManagerTmp
{
	public static AgentRef getAgentRef(String name)
	{
		try{
			return new AgentRef(RefManagerConf.getProperty(name));
		}catch(Exception e){
			return null;
		}
	}
	
	public static AgentRef findAgentRef(String className)
	{
		Enumeration list = getAgentsName();
		String items = "";
		while(list.hasMoreElements())
		{
			AgentRef ref = getAgentRef((String)list.nextElement());
			if(ref.getClassName().equals(className))
				return ref;
		}
		return null;
	}
	
	public static AgentRef[] getAgentsRef()
	{
		Enumeration list = getAgentsName();
		Vector v = new Vector(0);
		while(list.hasMoreElements())
		{
			try{
				v.add(new AgentRef(RefManagerConf.getProperty(list.nextElement().toString().trim())));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return (AgentRef[]) v.toArray(new AgentRef[v.size()]);
	}
	
	public static void removeAgentRef(String nameAgentRef)
	{
		Enumeration list = getAgentsName();
		String items = "";
		while(list.hasMoreElements())
		{
			String item = (String) list.nextElement();
			if(item!=null)
			{
				item = item.trim();
				if(!item.equals(nameAgentRef.trim()))
					items += item+",";
			}
		}
		if(items.endsWith(","))
			items = items.substring(0,items.length()-1);
		RefManagerConf.setProperty("agents",items);
		RefManagerConf.deleteProperty(nameAgentRef);
	}
	
	public static void removeAgentRef(AgentRef agentRef)
	{
		removeAgentRef(agentRef.getName());
	}
	
	public static void addAgentRef(AgentRef agentRef)
	{
		addRef(agentRef.getName());
		RefManagerConf.setProperty(agentRef.getName(),agentRef.toString());
	}
	
	private static void addRef(String name)
	{
		boolean add = true;
		Enumeration list = getAgentsName();
		String items = "";
		while(list.hasMoreElements())
		{
			String item = (String) list.nextElement();
			if(item!=null)
			{
				item = item.trim();
				if(item.equals(name.trim()))
					add = false;
				items += item+",";
			}
		}
		items += name;
		if(add)
		{
			RefManagerConf.setProperty("agents",items);
		}
		
	}
	
	public static Enumeration getAgentsName()
	{
		String agents = RefManagerConf.getProperty("agents");
		if(agents == null)
			agents = "";
		StringTokenizer st = new StringTokenizer(agents,",");
		Vector ret = new Vector(0);
		while(st.hasMoreTokens())
		{
			ret.add(st.nextToken());
		}
		return ret.elements();
	}
}

class RefManagerConf extends Conf
{
	private static final String FILENAME = "/data/repository.properties";

	/**
	 * 
	 * @uml.property name="conf"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static final Conf conf = new Conf(FILENAME);

	
	public static String getProperty(String name) {
		return getProperty(name,conf);
	}
	
	public static void setProperty(String name,String value)
	{
		setProperty(name,value,conf);
	}
	
	public static void deleteProperty(String name)
	{
		deleteProperty(name,conf);
	}
}