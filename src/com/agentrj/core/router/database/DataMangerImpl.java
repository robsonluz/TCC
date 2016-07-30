/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router.database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import com.agentrj.core.agents.database.*;
/**
 * @author Adminsitrador
 *
 */
public class DataMangerImpl extends DataManager
{
	
	public DataMangerImpl() throws SQLException 
	{
		super();
	}

	protected Connection initSystemConnection() throws SQLException 
	{
		try{
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:"+System.getProperty("agentrj.home")+"\\data\\systemdb","sa","explorer");
			return con;
			
		 	/*Class.forName("org.gjt.mm.mysql.Driver");
		  	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/tccrouter","root","");
		  	return con;*/
			
		}catch(ClassNotFoundException e){
			throw new SQLException(e.getMessage());      
		}
	}
}
