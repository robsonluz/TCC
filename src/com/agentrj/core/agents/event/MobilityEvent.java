/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.event;
import com.agentrj.core.agents.*;

/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MobilityEvent extends AgentEvent {

	public MobilityEvent(Object source, int id) 
	{
		super(source, id);
	}
	
	/**
	 * Marks the first integer id for the range of mobility event ids.
	 */
	public static final int AGLET_MOBILITY_FIRST = 1200;

	/**
	 * Marks the last integer id for the range of mobility event ids.
	 */
	public static final int AGLET_MOBILITY_LAST = 1202;

	/**
	 * The DISPATCHING event type is delivered just after the dispatch methods
	 * is called.
	 */
	public static final int DISPATCHING = AGLET_MOBILITY_FIRST;

	/**
	 * The REVERTING event type is delivered when the retaction is requested
	 * from the remote site.
	 */
	public static final int REVERTING = AGLET_MOBILITY_FIRST + 1;

	/**
	 * The ARRIVAL event type is delivered when the aglet arrived at the
	 * destination.
	 */
	public static final int ARRIVAL = AGLET_MOBILITY_FIRST + 2;

	private static String name[] = {
		"DISPATCHING", "REVERTING", "ARRIVAL", 
	};



}
