/*
 * Created on 29/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.protocol;
import com.agentrj.core.home.*;
import java.beans.*;
import java.net.URL;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class Protocol
{
	public static Protocol createProtocol(String classname,ITalkClient talkClient,URL url)throws Exception
	{
		Object obj = Beans.instantiate(ClassLoader.getSystemClassLoader(), classname);
		Protocol pro = (Protocol) obj;
		pro.initProtocol(talkClient,url);
		return (Protocol) obj;
	}
	
	protected abstract void initProtocol(ITalkClient talkClient,URL url)throws Exception;
	public abstract URL getURL();
	public abstract ITalkClient getTalkClient();
	public abstract String getModelURL();
}