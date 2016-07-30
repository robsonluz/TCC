/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.database;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author Adminsitrador
 *
 */
public abstract class DataManager
{
	private Connection systemConnection;
	
	public DataManager()throws SQLException
	{
		systemConnection = initSystemConnection();
	}

	/**
	 * 
	 * @uml.property name="systemConnection"
	 */
	//public abstract Connection getConnection(String user,String pass)throws SQLException;
	public Connection getSystemConnection() {
		return systemConnection;
	}

	
	protected abstract Connection initSystemConnection()throws SQLException;
	
}