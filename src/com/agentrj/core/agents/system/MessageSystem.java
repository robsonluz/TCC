/*
 * Created on 09/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.system;
import com.agentrj.core.agents.*;
/**
 * @author Adminsitrador
 *
 */
public class MessageSystem extends Message
{
	static final public String RUN = "_run";
	static final public String DISPOSE = "_dispose";
	static final public String CREATE = "_create";
	
	public MessageSystem(String kind) 
	{
		super(kind);
	}
}
