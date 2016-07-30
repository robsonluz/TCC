/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.agents.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ThreadWork1 extends Thread
{

	/**
	 * 
	 * @uml.property name="agent"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Agent agent;

	public ThreadWork1(Agent agent)
	{
		this.agent = agent;
	}
	
	public void run()
	{
		agent.doJob();
		try{
			//agent.getState().setState(StateAgent.SLEEP);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * @return
	 * 
	 * @uml.property name="agent"
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 * 
	 * @uml.property name="agent"
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}