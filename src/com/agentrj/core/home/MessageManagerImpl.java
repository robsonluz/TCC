/*
 * Created on 26/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.agents.*;
import com.agentrj.core.agents.system.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class MessageManagerImpl implements MessageManager
{

	/**
	 * 
	 * @uml.property name="home"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private IHome home;

	public MessageManagerImpl(IHome home)
	{
		this.home = home;
	}
	
	public boolean handleMessage(Message message, AgentAddress target) 
	{
		for(int i=0;i<this.home.getAgents().size();i++)
		{
			AgentThread tw = (AgentThread)home.getAgents().get(i);
			
			if(tw.getAgent().getInfo().getId().equals(target.getIdAgent()))
			{
				return handleMessage(tw,message);
			}
		}
		return false;
	}
	
	private boolean handleMessage(AgentThread tw,Message message)
	{
		
		/*if(message.sameKind(MessageEvent.CREATE))
		{
			tw.getAgent().onCreation(message.getArg());
			return true;
		}

		return tw.getAgent().handleMessage(message);
		*/
		if(message.sameKind(MessageSystem.DISPOSE))
		{
			tw.handleMessage(message);
			home.disposeAgent(tw.getAgent());
			return true;
		}
		tw.handleMessage(message);
		return false;
	}

	public boolean sendMessage(Message msg, AgentAddress address) throws Exception
	{
		if(home.equals(address.getHome()))
			return handleMessage(msg,address);
		return home.getRouter().sendMessage(msg,address);
	}
}