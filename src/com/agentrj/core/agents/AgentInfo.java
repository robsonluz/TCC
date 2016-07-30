/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;

import java.io.Serializable;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class AgentInfo implements Serializable 
{
	private String id;
	private String className;

	/**
	 * 
	 * @uml.property name="homeInfo"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private transient HomeInfo homeInfo;

	
	public AgentInfo()
	{
		this.id = "";
		this.className = "";
	}
	
	public AgentInfo(String id,HomeInfo homeInfo)
	{
		this.id = id;
		this.className = "";
		this.homeInfo = homeInfo;
	}

	/**
	 * 
	 * @uml.property name="id"
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @uml.property name="id"
	 */
	public void setId(String string) {
		id = string;
	}

	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ClassName:[");
		sb.append(className);
		sb.append("]\n");
		sb.append("ID:[");
		sb.append(id);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return
	 * 
	 * @uml.property name="className"
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="className"
	 */
	public void setClassName(String string) {
		className = string;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="homeInfo"
	 */
	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	/**
	 * @param info
	 * 
	 * @uml.property name="homeInfo"
	 */
	public void setHomeInfo(HomeInfo info) {
		homeInfo = info;
	}

}
