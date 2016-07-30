/*
 * Created on 03/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.impl.router;
import java.net.*;
import java.io.*;
import java.util.*;
import com.agentrj.core.util.*;
import com.agentrj.core.agents.*;
import com.agentrj.core.router.*;
import com.agentrj.impl.router.comunication.DataBase;

/**
 * @author Adminsitrador
 */

public class RouterTelnetComunication extends Thread
{
	private Socket sock;
	private PrintStream out;
	private BufferedReader in;
	private boolean isRunning = true;

	/**
	 * 
	 * @uml.property name="writer"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private Writter writer;

	/**
	 * 
	 * @uml.property name="router"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ITalkServer router;

	/**
	 * 
	 * @uml.property name="info"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private RouterInfo info;

	/**
	 * 
	 * @uml.property name="telnet"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private RouterTelnet telnet;

	
	public RouterTelnetComunication(RouterTelnet telnet,ITalkServer router,RouterInfo info,Writter writer,Socket sock)
	{
		this.telnet =telnet;
		this.info = info;
		this.router = router;
		this.writer = writer;
		this.sock = sock;
	}
	
	public void run()
	{
		try {
			this.out = new PrintStream(sock.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()) );

			// tell 'em they are connected
			write("Connected from: "+sock);
			out.println("Roteador "+info.toString());
			out.flush();

			while(this.isRunning) {
				out.print("> ");
				out.flush();
				communicate();
			}
			telnet.removeCliente(this);
		} catch(IOException ioe) {
			System.err.println(ioe.toString());
			telnet.removeCliente(this);
			return;
		}
	}
	
	public void communicate() throws IOException 
	{
		// read the body
		String data;

			if((data = in.readLine())==null) {
			return;
		}

		if("".equals(data)) {
			return;
		}
		process(data);

	}
	
	
	private void process(String msg)throws IOException
	{
		if(msg!=null)
		{
			msg = msg.toUpperCase();
			if(msg.equals("HELP") || msg.equals("?"))
			{
				help();		
			}else if(msg.equals("INFO")){
				printOut("Roteador "+info.toString());
			}else if(msg.equals("LIST")){
				listHome();
			}else if(msg.equals("QUIT")){
				quit();
			}else if(msg.equals("DATABASE")){
				new DataBase(this.out,this.in);
			}else{
				printOut("Funcao invalida");
			}
		}
	}
	
	private void quit()
	{
		try{
			printOut("Bye.");
			write("Connection close from: "+sock.getRemoteSocketAddress());
			this.sock.close();
			setRunning(false);
		}catch(Exception e){
		}
	}
	
	private void listHome()
	{
		try{
			Enumeration enum = router.getHomes();
			while(enum.hasMoreElements())
			{
				HomeInfo h = (HomeInfo)enum.nextElement();
				printOut(h.toString());
			}
		}catch(Exception e){
		}
		
	}
	
	private void printOut(String t)
	{
		out.println(t);
		out.flush();
	}
	
	private void help()
	{
		printOut("help - Ajuda");
		printOut("info - Informaçoes do roteador");
		printOut("list - Lista todas as Home cadastradas no roteador");
		printOut("database - Conecta com o repositório de Agents do roteador");
		printOut("quit - Sair");
	}

	private void write(String s)
	{
		writer.println(s);
	}

	/**
	 * @return
	 * 
	 * @uml.property name="isRunning"
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param b
	 * 
	 * @uml.property name="isRunning"
	 */
	public void setRunning(boolean b) {
		isRunning = b;
	}

}
