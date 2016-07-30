/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.agents.*;
import java.io.*;
import java.net.URL;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HomeInfoImpl implements HomeInfo,Serializable
{
	private String name;
	private String host;
	private URL url;
	
	public HomeInfoImpl()
	{
		this.name = "";
		this.host = "";
	}
	
	public HomeInfoImpl(String name)
	{
		this.host = "";
		this.name = name;
	}
	
/*	public HomeInfoImpl(String name,String host)
	{
		this.name = name;
		this.host = host;
	}*/
	
	public HomeInfoImpl(URL url)
	{
		this.url = url;
		//System.out.println(url.getPath());
		checkName();
	}
	
	private void checkName()
	{
		String path = url.getPath();
		if(path!= null && !path.equals(""))
		{
			if(path.startsWith("/"))
				path = path.substring(1);
			this.name = path;
		}else{
			name = "";
		}
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
	 * @uml.property name="name"
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * 
	 * @uml.property name="host"
	 */
	public String getHost() {
		return url.getHost();
	}

	/**
	 * 
	 * @uml.property name="host"
	 */
	public void setHost(String string) {
		host = string;
	}

	
	
	public boolean equals(HomeInfo home)
	{
		if(home.getName().equals(this.name))
			return true;
		return false;
	}
	
	public String toString()
	{
		return getURL().toString();
	}

	public int getPort() {
		return url.getPort();
	}

	public URL getURL() 
	{
		return url;
	}

	public void setPort(int port) {
	}

	public void setURL(URL url) {
		this.url = url;

	}

}
