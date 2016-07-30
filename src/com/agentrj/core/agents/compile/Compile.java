/*
 * Created on 04/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.compile;
import java.net.*;
import java.rmi.*;
import java.io.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.home.*;
import com.agentrj.core.router.*;
import com.agentrj.core.agents.ref.*;

/**
 * @author Adminsitrador
 *
 */
public class Compile
{
	public Compile(String args[])
	{
		try{
			init(args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init(String args[])throws Exception
	{
		String urlRouter = args[0]; //"rmi//saturno:9000/Router"
		String agentRef = args[1]; //"AgentSample1;com.agentrj.samples.sample1.AgentSample1;1.0"
		String agentJar = args[2];
				
		ITalkServer router = (ITalkServer) Naming.lookup(urlRouter);
		File file = new File(agentJar);
		if(file.exists())
		{
			FileInputStream fi = new FileInputStream(file);
			byte result[] = new byte[fi.available()];
			fi.read(result);
			fi.close();
			AgentRef ref = new AgentRef(agentRef+";"+file.getName());
			if(router.updateAgentRef(ref,result))
			{
				System.out.println("Agent atualizado com sucesso!");
			}else{
				System.out.println("Ocorreu um erro no roteador ao atualizar o Agent!");
			}
		}else{
			System.out.println(agentJar+" nao existe");
		}
	}
	
	public static void main(String args[])
	{
		new Compile(args);
	}
}
