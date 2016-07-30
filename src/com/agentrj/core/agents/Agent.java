/*
 * Created on 29/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents;
import java.io.*;
import java.security.*;
import com.agentrj.core.agents.event.*;
import com.agentrj.core.home.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public abstract class Agent implements Serializable,Cloneable 
{

	/**
	 * 
	 * @uml.property name="state"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private StateAgent state;

	private long id;

	/**
	 * 
	 * @uml.property name="stub"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private transient AgentStub stub;

	/**
	 * 
	 * @uml.property name="mobilityListener"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private MobilityListener mobilityListener = null;

	/**
	 * 
	 * @uml.property name="info"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private AgentInfo info;

	
	public Agent()
	{
		info = new AgentInfo();
		state = new StateAgent();
	}

	
	synchronized final public void addMobilityListener(MobilityListener listener) 
	{
		if (mobilityListener == null) {
			mobilityListener = listener;
		} else if (mobilityListener == listener) {
			return;
		} else if (mobilityListener instanceof AgentEventListener) {
			((AgentEventListener)mobilityListener)
				.addMobilityListener(listener);
		} else if (mobilityListener instanceof MobilityListener) {
			mobilityListener = new AgentEventListener(mobilityListener, 
													  listener);
		} 
	}
	
	protected void processMobilityEvent(MobilityEvent ev) {
		if (mobilityListener != null) {
			switch (ev.getID()) {
			case MobilityEvent.DISPATCHING:
				mobilityListener.onDispatching(ev);
				break;
			case MobilityEvent.REVERTING:
				mobilityListener.onReverting(ev);
				break;
			case MobilityEvent.ARRIVAL:
				mobilityListener.onArrival(ev);
				break;
			}
		} 
	}
	
	final public void dispatchEvent(AgentEvent ev) {
		switch (ev.getID()) {
		case MobilityEvent.DISPATCHING:
		case MobilityEvent.REVERTING:
		case MobilityEvent.ARRIVAL:
			processMobilityEvent((MobilityEvent)ev);
			break;
		}
	}

	/**
	 * 
	 * @uml.property name="stub"
	 */
	public synchronized final void setStub(AgentStub stub) {
		stub.setAgent(this);
		this.stub = stub;
	}

	
	public final AgentProxy getProxy() {
		return stub.getAgentContext().getAgentProxy(getInfo().getId());
	}
	
	public final AgentProxy dispatch(HomeInfo target)throws Exception
	{
		AgentProxy proxy = new AgentProxyImpl(stub.getAgentContext().getHome(), new AgentAddress(target,getInfo().getId()));
		return stub.dispatch(target,(Agent)clone());
	}
	
	public final AgentProxy dispatch(HomeInfo target,AgentProxy agentDispatcher)throws Exception
	{
		return stub.dispatch(target,(Agent)clone(),agentDispatcher);
	}
	
	public final AgentContext getAgentContext()
	{
		return stub.getAgentContext();
	}

	/**
	 * 
	 * @uml.property name="state"
	 */
	public final StateAgent getState() {
		return state;
	}

	
	protected final void setProtections(PermissionCollection protections)
	{
		
	}
	
	public final void dispose()throws Exception
	{
		stub.dispose();	
	}
	
	public boolean handleMessage(Message msg) 
	{
		return false;
	}
	
	public void onCreation(Object init){}

	public void onDisposing(){}
	
	
	public void doJob(){}
	
	public boolean equals(Object obj)
	{
		if(obj == null || !(obj instanceof Agent))
			return false;
		Agent agent = (Agent) obj;
		if(agent.getInfo().getId() == this.getInfo().getId())
			return true;
		return false;
	}

	/**
	 * 
	 * @uml.property name="info"
	 */
	public final AgentInfo getInfo() {
		return info;
	}

	
	public boolean isMessageAlive()
	{
		return false;
	}
}