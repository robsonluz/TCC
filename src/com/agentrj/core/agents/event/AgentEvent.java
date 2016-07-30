/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.event;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class AgentEvent extends java.util.EventObject {
	/**
	 * Event id
	 */
	protected int id;

	/**
	 * Constructs an AgentEvent with source and id.
	 */
	public AgentEvent(Object source, int id) 
	{
		super(source);
		this.id = id;
	}
	
	/**
	 * Gets the id of this event
	 */
	public int getID() 
	{
		return id;
	}
}