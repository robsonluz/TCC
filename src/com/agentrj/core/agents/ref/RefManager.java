/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.ref;
import com.agentrj.core.agents.*;


import java.sql.*;
import java.util.*;

/**
 * @author Adminsitrador
 */

public class RefManager
{

	/**
	 * 
	 * @uml.property name="bdAgentRef"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static BDAgentRef bdAgentRef;

	
	static{
		bdAgentRef = new BDAgentRef(AgentRuntime.getAgentRuntime().getDataManager().getSystemConnection());
		/*try{
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:"+System.getProperty("agentrj.home")+"\\data\\systemdb","sa","explorer");
			bdAgentRef = new BDAgentRef(con);
		}catch(Exception e){
			e.printStackTrace();
		}*/

	}
	
	public static AgentRef findAgentRef(String className)
	{
		try{
			AgentRef ag[] = bdAgentRef.addRowsToVector(bdAgentRef.buscaClass(className));
			if(ag.length==1)
				return ag[0];
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void removeAgentRef(String nameAgent)
	{
		AgentRef agent = getAgentRef(nameAgent);
		if(agent!=null)
		{
			try{	
				bdAgentRef.excluir(agent.getIdAgent());
			}catch(Exception e){
			}
		}
	}
	
	public static void removeAgentRef(AgentRef agentRef)
	{
		removeAgentRef(agentRef.getName());
	}
	
	public static void addAgentRef(AgentRef agentRef)
	{
		try{
			AgentRef ref = getAgentRef(agentRef.getName());
			if(ref==null)
			{
				bdAgentRef.inserir(agentRef);
			}else{
				agentRef.setIdAgent(ref.getIdAgent());
				bdAgentRef.alterar(agentRef);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static AgentRef[] getAgentsRef()
	{
		try{
			return bdAgentRef.addRowsToVector(bdAgentRef.busca());
		}catch(Exception e){
			return new AgentRef[0];
		}
	}
	
	public static AgentRef getAgentRef(String name)
	{
		try{
			AgentRef ag[] = bdAgentRef.addRowsToVector(bdAgentRef.busca(name));
			if(ag.length==1)
				return ag[0];
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
