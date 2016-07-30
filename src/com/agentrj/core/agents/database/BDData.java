/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.database;
import java.sql.*;
import java.util.*;
/**
 * @author Adminsitrador
 *
 */
public abstract class BDData
{	
	protected Connection con;
	
	public BDData(Connection con)
	{
		this.con = con;
	}
	
	public Collection executeQuery(String query)throws SQLException,Exception
	{
		LinkedList lista = new LinkedList();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			IGenerico igg = (IGenerico) getIClass().newInstance();
			igg.setBD(rs);
			addObjectToCollection(lista,igg);
		}
		rs.close();
		return lista;
	}
	
	public Collection executeQuery(PreparedStatement statement)throws SQLException,Exception
	{
		LinkedList lista = new LinkedList();
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			IGenerico igg = (IGenerico) getIClass().newInstance();
			igg.setBD(rs);
			addObjectToCollection(lista,igg);
		}
		rs.close();
		return lista;
	}
	
	public int countQuery(String query)throws SQLException
	{
		int ret = 0;
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		if(rs.next())
		{
			ret = rs.getInt("Count(*)"); 
		}
		rs.close();
		return ret;
	}
	
	public int countQuery(PreparedStatement stm)throws SQLException
	{
		int ret = 0;
		ResultSet rs = stm.executeQuery();
		if(rs.next())
		{
			ret = rs.getInt("Count(*)"); 
		}
		rs.close();
		return ret;
	}	
	
	public void inserir(IGenerico iGen)throws SQLException
	{
		String sql = LoadSQL.load(iGen.getNameTable()+".insert");
		PreparedStatement pStm = con.prepareStatement(sql);
		iGen.setAttributes(pStm);
		pStm.executeUpdate();
	}
	
	public void alterar(IGenerico iGen)throws SQLException
	{
		String sql = LoadSQL.load(iGen.getNameTable()+".update");
		PreparedStatement pStm = con.prepareStatement(sql);
		iGen.setAttributes(pStm);
		pStm.executeUpdate();
	}
	
	protected void excluir(String table,int idValue)throws SQLException
	{
		String sql = LoadSQL.load(table+".delete");
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1,idValue);
		stm.executeUpdate();
	}
	
	public ResultSet executeQueryR(String sql)throws SQLException,Exception
	{
		Statement stm = con.createStatement();
		return stm.executeQuery(sql);
	}
	
	protected abstract void addObjectToCollection(LinkedList lista,IGenerico ig);
	protected abstract Class getIClass();
}
