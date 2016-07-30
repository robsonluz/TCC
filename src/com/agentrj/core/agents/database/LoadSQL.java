/*
 * Created on 11/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.database;

import java.util.*;
import java.io.*;

/**
 * @author Administrator *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class LoadSQL
{

	/**
	 * 
	 * @uml.property name="manager"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static LoadSQL manager = null;

	private static Object managerLock = new Object();
	private Properties properties = null;
	private Object propertiesLock = new Object();
	private String resourceURI;
	private static String propsName = System.getProperty("agentrj.home")+"\\data\\sql.properties";
	
	private LoadSQL(String resourceURI)
	{
		this.resourceURI = resourceURI;
	}
	
	public static String load(String name) {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new LoadSQL(propsName);
				}
			}
		}
		return manager.getProp(name);
	}
	
	protected String getProp(String name) {
		if (properties == null) {
			synchronized(propertiesLock) {
				//Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		}
		else {
			return property.trim();
		}
	}
	
	private void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			File file = new File(propsName);
			in = new FileInputStream(file);
			//in = getClass().getResourceAsStream(resourceURI);
			properties.load(in);
		}
		catch (Exception e) {
			System.err.println("Error reading SQL properties in LoadSQL.loadProps() " + e);
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (Exception e) { }
		}
	}
}