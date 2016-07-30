/*
 * Created on 30/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.ref;
import com.agentrj.core.agents.database.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AgentRef implements Serializable,IGenerico
{
	private String name;
	private String className;
	private String version;
	private long timestamp;
	private String nameJar;
	private int idAgent;
		
	public AgentRef()
	{
		this.name = "";
		this.className = "";
		this.version = "";
		this.timestamp = 0;
		this.idAgent = 0;
	}
	
	/**
	 * @param ref deve ser o parâmetro completo para o AgentRef
	 * @throws Exception Gera uma Exception, se estiver incorreto os parâmetros
	 */
	public AgentRef(String ref)throws Exception
	{
		int cont = 0;
		StringTokenizer st = new StringTokenizer(ref,";");
		if(st.countTokens() != 4)
			throw new Exception("Parametros incorretos!");
		while(st.hasMoreTokens())
		{
			switch(cont)
			{
				case 0: this.name = st.nextToken();break;
				case 1: this.className = st.nextToken();break;
				case 2: this.version = st.nextToken();break;
				case 3: this.nameJar = st.nextToken();break;
				default: throw new Exception("Parametros incorretos!");
			}
			cont++;
		}
		this.idAgent = 0;
		this.timestamp = new Date().getTime();
	}

	/**
	 * 
	 * @uml.property name="className"
	 */
	public String getClassName() {
		return className;
	}


	public Date getDate() 
	{
		return new java.sql.Date(this.timestamp);
	}

	/**
	 * 
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @uml.property name="version"
	 */
	public String getVersion() {
		return version;
	}

	
	public String toString()
	{
		return name+";"+className+";"+version;
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof AgentRef))
			return false;
		AgentRef that = (AgentRef) obj;
		if( that.getClassName().equals(this.getClassName()) &&
			that.getName().equals(this.getName()) &&
			that.getVersion().equals(this.getVersion()))
			return true;
		return false;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="className"
	 */
	public void setClassName(String string) throws Exception {
		if (string == null || string.equals(""))
			throw new Exception("ClassName cannot be null or emptry!");
		if (string.indexOf(";") != -1)
			throw new Exception("ClassName cannot have charactere ';'");
		className = string;
	}


	/**
	 * @param date
	 */
	public void setDate(java.sql.Date date) 
	{
		if(date!=null)
			this.timestamp = date.getTime();
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="name"
	 */
	public void setName(String string) throws Exception {
		if (string == null || string.equals(""))
			throw new Exception("Name cannot be null or emptry!");
		if (string.indexOf(";") != -1)
			throw new Exception("Name cannot have charactere ';'");
		name = string;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="version"
	 */
	public void setVersion(String string) throws Exception {
		if (string == null || string.equals(""))
			throw new Exception("Version cannot be null or emptry!");
		if (string.indexOf(";") != -1)
			throw new Exception("Version cannot have charactere ';'");
		version = string;
	}

	/**
	 * 
	 * @uml.property name="nameJar"
	 */
	public String getNameJar() {
		return this.nameJar;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="timestamp"
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param l
	 * 
	 * @uml.property name="timestamp"
	 */
	public void setTimestamp(long l) {
		timestamp = l;
	}


	public String getNameTable() 
	{
		return "system_agents";
	}

	public void setAttributes(PreparedStatement stm) throws SQLException 
	{
		stm.setString(1,this.name);
		stm.setString(2,this.version);
		stm.setString(3,this.className);
		stm.setLong(4,this.timestamp);
		stm.setString(5,this.nameJar);
		if(idAgent!=0)
			stm.setInt(6,this.idAgent);
		else
			stm.setString(6,null);
	}

	public void setBD(ResultSet rs) throws SQLException 
	{
		name = rs.getString("name");
		version = rs.getString("version");
		className = rs.getString("className");
		timestamp = rs.getLong("data");
		nameJar = rs.getString("nameJar");
		idAgent = rs.getInt("idAgent");
	}

	/**
	 * @return
	 * 
	 * @uml.property name="idAgent"
	 */
	public int getIdAgent() {
		return idAgent;
	}

	/**
	 * @param i
	 * 
	 * @uml.property name="idAgent"
	 */
	public void setIdAgent(int i) {
		idAgent = i;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="nameJar"
	 */
	public void setNameJar(String string) {
		nameJar = string;
	}

}

