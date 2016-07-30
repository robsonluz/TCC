/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.ref;
import java.sql.Connection;
import java.sql.*;
import java.util.LinkedList;
import java.util.*;
import com.agentrj.core.agents.database.*;
/**
 * @author Adminsitrador
 *
 */
public class BDAgentRef extends BDData
{
	
	public BDAgentRef(Connection con) 
	{
		super(con);
	}

	protected void addObjectToCollection(LinkedList lista, IGenerico ig) 
	{
		lista.add((AgentRef) ig);
	}
	
	public Collection busca()throws SQLException,Exception
	{
		String sql = LoadSQL.load("system_agents.busca()");
		return executeQuery(sql);
	}
	
	public Collection buscaClass(String className)throws SQLException,Exception
	{
		String sql = LoadSQL.load("system_agents.busca(className)");
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1,className);
		return executeQuery(stm);
	}
	
	public Collection busca(String name)throws SQLException,Exception
	{
		String sql = LoadSQL.load("system_agents.busca(name)");
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1,name);
		return executeQuery(stm);
	}
	
	public void excluir(int id)throws SQLException
	{
		super.excluir("system_agents",id);
	}
	
	public AgentRef[] addRowsToVector(Collection list)
	{
		return (AgentRef[]) list.toArray(new AgentRef[list.size()]);
	}

	protected Class getIClass() 
	{
		return AgentRef.class;
	}
}	
