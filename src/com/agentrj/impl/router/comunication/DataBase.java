/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.router.comunication;
import java.io.*;
import java.sql.*;
import com.agentrj.core.agents.*;
/**
 * @author Adminsitrador
 *
 */
public class DataBase
{
	private PrintStream out;
	private BufferedReader in;
	private boolean isRunning = true;
	private Connection con;
	
	public DataBase(PrintStream out,BufferedReader in)throws IOException
	{
		this.out = out;
		this.in = in;
		this.con = AgentRuntime.getAgentRuntime().getDataManager().getSystemConnection();
		init();
	}
	
	private void init()throws IOException
	{
		while(this.isRunning) {
			out.print("#database> ");
			out.flush();
			communicate();
		}
	}
	
	public void communicate() throws IOException 
	{
		// read the body
		String data;

			if((data = in.readLine())==null) {
			return;
		}

		if("".equals(data)) {
			return;
		}
		process(data);

	}
	
	private void process(String msg)throws IOException
	{
		if(msg!=null)
		{
			msg = msg.toUpperCase();
			if(msg.equals("HELP") || msg.equals("?"))
			{
				help();		
			}else if(msg.equals("INFO")){
				info();
			}else if(msg.equals("QUIT")){
				this.isRunning = false;
			}else{
				execSQL(msg);
			}
		}
	}
	
	private void info()
	{
		try{
			printOut("Database: "+con.getMetaData().getDatabaseProductName());
			printOut("Driver: "+con.getMetaData().getDriverName());
		}catch(Exception e){
		}
	}
	
	private void execSQL(String sql)
	{
		try{
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			printResultSet(rs);
		}catch(SQLException e){
			printOut("ERROR["+e.getErrorCode()+"] "+e.getMessage());
		}
	}
	
	private void printResultSet(ResultSet rs)throws SQLException
	{
		ResultSetMetaData me = rs.getMetaData();
		int cont = me.getColumnCount();
		for(int i=0;i<cont;i++)
			out.print(me.getColumnName(i+1)+"\t");
		out.print("\r\n");
		out.flush();
		while(rs.next())
		{
			for(int i=0;i<cont;i++)
			{
				out.print(rs.getString(i+1)+"\t");
			}
			out.print("\r\n");
			out.flush();
		}
		out.print("\r\n");
		out.flush();
	}
	
	private void help()
	{
		printOut("help - Ajuda");
		printOut("info - Informaçoes do Banco de Dados");
		printOut("quit - Sair");
	}
	
	private void printOut(String t)
	{
		out.println(t);
		out.flush();
	}

}
