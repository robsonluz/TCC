/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.io.Serializable;

import com.agentrj.core.home.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class AgentAddress implements Serializable
{

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private HomeInfo home;

	private String idAgent;
	
	public AgentAddress(HomeInfo home,String idAgent)
	{
		this.home = home;
		this.idAgent = idAgent;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="home"
	 */
	public HomeInfo getHome() {
		return home;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="idAgent"
	 */
	public String getIdAgent() {
		return idAgent;
	}

	/**
	 * @param home
	 * 
	 * @uml.property name="home"
	 */
	public void setHome(HomeInfo home) {
		this.home = home;
	}

	/**
	 * @param i
	 * 
	 * @uml.property name="idAgent"
	 */
	public void setIdAgent(String i) {
		idAgent = i;
	}

	
	public String toString()
	{
		return home+"["+idAgent+"]";
	}

}
