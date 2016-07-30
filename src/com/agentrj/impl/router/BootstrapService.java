/*
 * Created on 03/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.router;
import com.agentrj.core.router.*;

/**
 * @author Adminsitrador * Esta classe server para iniciar o roteador de agentes como serviço no windows.
 */

public class BootstrapService
{

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static RouterServer router;

	public BootstrapService()
	{
		router = new RouterServer();
	}
	
	private void start()
	{
		Console console = new Console("Router");
		router.init();
	}
	
	private void stop()
	{
		router.end();
	}
	
	public static void main(String args[])
	{
		BootstrapService service = new BootstrapService();
		String command = args[0];
		if(command.equals("start"))
			service.start();
		else if(command.equals("stop"))
			service.stop();
	}
}