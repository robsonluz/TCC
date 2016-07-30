/*
 * Created on 30/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.router;
import com.agentrj.core.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RouterInfo
{
	private String host;
	private String workdir;
	private int port;
	private String telnet;
	private int portTelnet;
	
	public RouterInfo()
	{
		host = "";
		port = 0;
		telnet = null;
		portTelnet=0;
	}
	
	public void checkFileInfo()throws Exception
	{
		setHost(ConfRouter.getProperty("host"));
		setWorkdir(ConfRouter.getProperty("workdir"));
		setPort(ConfRouter.getProperty("port"));
		setTelnet(ConfRouter.getProperty("telnet"));
	}

	/**
	 * @return
	 * 
	 * @uml.property name="host"
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="host"
	 */
	public void setHost(String string) throws Exception {
		if (string == null)
			throw new Exception("O host deve ser especificado!");
		if (string.length() <= 0)
			throw new Exception("O host não pode ser vazio!");
		host = string;
	}

	
	public String getURL()
	{
		return "//".concat(host).concat("/Router");
	}

	/**
	 * @return
	 * 
	 * @uml.property name="workdir"
	 */
	public String getWorkdir() {
		return workdir;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="workdir"
	 */
	public void setWorkdir(String string) throws Exception {
		if (string == null)
			throw new Exception("O workdir deve ser especificado!");
		if (string.length() <= 0)
			throw new Exception("O workdir não pode ser vazio!");
		workdir = string;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="port"
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param i
	 * 
	 * @uml.property name="port"
	 */
	public void setPort(int i) throws Exception {
		if (i <= 0)
			throw new Exception("A porta deve ser maior que 0");
		port = i;
	}

	
	public void setPort(String p)throws Exception
	{
		try{
			setPort(Integer.parseInt(p.trim()));
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Porta Inválida!");
		}
	}

	/**
	 * @return
	 * 
	 * @uml.property name="portTelnet"
	 */
	public int getPortTelnet() {
		return portTelnet;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="telnet"
	 */
	public String getTelnet() {
		return telnet;
	}


	public void setPortTelnet(String p)throws Exception
	{
		try{
			setPortTelnet(Integer.parseInt(p.trim()));
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Porta Inválida!");
		}
	}

	/**
	 * @param i
	 * 
	 * @uml.property name="portTelnet"
	 */
	public void setPortTelnet(int i) {
		portTelnet = i;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="telnet"
	 */
	public void setTelnet(String string) throws Exception {
		if (string != null && string.equals("yes")) {
			setPortTelnet(ConfRouter.getProperty("telnet.port"));
		}
		telnet = string;
	}

	
	public String toString()
	{
		return "Host: ["+this.host+"] Port: ["+this.port+"]";	
	}
}
