/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;

import java.io.Serializable;

/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StateAgent implements Serializable
{
	public static final int SLEEP = 1;
	public static final int WORKING = 2;
	private int currentState=1;
	
	public int getState()
	{
		return this.currentState;
	}
	
	public void setState(int state)
	{
		this.currentState = state;
	}
	
}
