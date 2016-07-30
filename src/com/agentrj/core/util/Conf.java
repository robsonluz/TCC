/*
 * Created on 30/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.util;
import java.io.*;
import java.util.Properties;
import java.util.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class Conf
{

	/**
	 * 
	 * @uml.property name="manager"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	protected static Conf manager = null;

	private static Object managerLock = new Object();
	protected static String propsName = "/conf.properties";
	
	public Conf()
	{
	}

	public static String getProperty(String name) {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		return manager.getProp(name);
	}
	
//	public static String getProperty(String name,Conf conf) {
//		if (manager == null) {
//			synchronized(managerLock) {
//				if (manager == null) {
//					manager = conf;
//				}
//			}
//		}
//		return manager.getProp(name);
//	}
	
	public static String getProperty(String name,Conf conf) {
		if(conf!=null)
			return conf.getProp(name);
		return "";
	}
	
//	public static void setProperty(String name, String value,Conf conf) {
//		if (manager == null) {
//			synchronized(managerLock) {
//				if (manager == null) {
//					manager = conf;
//				}
//			}
//		}
//		manager.setProp(name, value);
//	}
	
	public static void setProperty(String name, String value,Conf conf) {
		if(conf!= null)
			conf.setProp(name,value);
		//manager.setProp(name, value);
	}
	
//	public static void deleteProperty(String name,Conf conf) {
//		if (manager == null) {
//			synchronized(managerLock) {
//				if (manager == null) {
//					manager = conf;
//				}
//			}
//		}
//		manager.deleteProp(name);
//	}
	
	
	public static void deleteProperty(String name,Conf conf) {
		if(conf!=null)
			conf.deleteProp(name);
	}

	/**
	 * 
	 * @uml.property name="propsName"
	 */
	public static void setPropsName(String name) {
		propsName = name;
	}

	/**
	 * 
	 * @uml.property name="propsName"
	 */
	public static String getPropsName() {
		return propsName;
	}

	/**
	 * Sets a Jive property. If the property doesn't already exists, a new
	 * one will be created.
	 *
	 * @param name the name of the property being set.
	 * @param value the value of the property being set.
	 */
	public static void setProperty(String name, String value) {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		manager.setProp(name, value);
	}

	/**
	 * Deletes a Jive property. If the property doesn't exist, the method
	 * does nothing.
	 *
	 * @param name the name of the property to delete.
	 */
	public static void deleteProperty(String name) {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		manager.deleteProp(name);
	}

	/**
	 * Returns the names of the Jive properties.
	 *
	 * @return an Enumeration of the Jive property names.
	 */
	public static Enumeration propertyNames() {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		return manager.propNames();
	}

	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public static boolean propertyFileIsReadable() {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		return manager.propFileIsReadable();
	}

	/**
	 * Returns true if the properties are writable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public static boolean propertyFileIsWritable() {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		return manager.propFileIsWritable();
	}

	/**
	 * Returns true if the jive.properties file exists where the path property
	 * purports that it does.
	 */
	public static boolean propertyFileExists() {
		if (manager == null) {
			synchronized(managerLock) {
				if (manager == null) {
					manager = new Conf(propsName);
				}
			}
		}
		return manager.propFileExists();
	}


	private Properties properties = null;
	private Object propertiesLock = new Object();
	private String resourceURI;

	/**
	 * Creates a new Conf. Singleton access only.
	 */
	public Conf(String resource) {
		File file = new File(System.getProperty("agentrj.home")+resource);
		this.resourceURI = file.getAbsolutePath();
	}

	/**
	 * Gets a Jive property. Jive properties are stored in jive.properties.
	 * The properties file should be accesible from the classpath. Additionally,
	 * it should have a path field that gives the full path to where the
	 * file is located. Getting properties is a fast operation.
	 *
	 * @param name the name of the property to get.
	 * @return the property specified by name.
	 */
	protected String getProp(String name) {
		//If properties aren't loaded yet. We also need to make this thread
		//safe, so synchronize...
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

	/**
	 * Sets a Jive property. Because the properties must be saved to disk
	 * every time a property is set, property setting is relatively slow.
	 */
	protected void setProp(String name, String value) {
		//Only one thread should be writing to the file system at once.
		synchronized (propertiesLock) {
			//Create the properties object if necessary.
			if (properties == null) {
				loadProps();
			}
			properties.setProperty(name, value);
			saveProps();
		}
	}

	protected void deleteProp(String name) {
		//Only one thread should be writing to the file system at once.
		synchronized (propertiesLock) {
			//Create the properties object if necessary.
			if (properties == null) {
				loadProps();
			}
			properties.remove(name);
			saveProps();
		}
	}

	protected Enumeration propNames() {
		//If properties aren't loaded yet. We also need to make this thread
		//safe, so synchronize...
		if (properties == null) {
			synchronized(propertiesLock) {
				//Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		return properties.propertyNames();
	}

	/**
	 * Loads Jive properties from the disk.
	 */
	private void loadProps() {
		
		properties = new Properties();
		
		InputStream in = null;
		try {
			in = new FileInputStream(new File(this.resourceURI));
			
			//in = getClass().getResourceAsStream(resourceURI);
			//System.out.println(in);
			//System.out.println(resourceURI);
			properties.load(in);
		}
		catch (Exception e) {
			System.err.println("Error reading Jive properties in Conf.loadProps() " + e);
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (Exception e) { }
		}
	}

	/**
	 * Saves Jive properties to disk.
	 */
	private void saveProps() {
		//System.out.println(properties);
		//Now, save the properties to disk. In order for this to work, the user
		//needs to have set the path field in the properties file. Trim
		//the String to make sure there are no extra spaces.
		String path = properties.getProperty("path");//.trim();
		//if(path == null || path.equals(""))
		if(resourceURI!= null)
		{
			path = resourceURI;
			if(path.startsWith("/") || path.startsWith("\\"))
				path = path.substring(1);
			//System.out.println(path);
			File file = new File(path);
			
			if(file.exists())
			{
				OutputStream out = null;
				try {
					out = new FileOutputStream(file.getAbsoluteFile());
					properties.store(out, "conf.properties -- " + (new java.util.Date()));
				}
				catch (Exception ioe) {
					System.err.println(ioe);
					ioe.printStackTrace();
				}
				finally {
					try {
					   out.close();
					} catch (Exception e) { }
				}
				return;
			}
		}
		path = path.trim();	
		OutputStream out = null;
		try {
			out = new FileOutputStream(path);
			properties.store(out, "conf.properties -- " + (new java.util.Date()));
		}
		catch (Exception ioe) {
			System.err.println(ioe);
			ioe.printStackTrace();
		}
		finally {
			try {
			   out.close();
			} catch (Exception e) { }
		}
	}

	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public boolean propFileIsReadable() {
		try {
			InputStream in = getClass().getResourceAsStream(resourceURI);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns true if the jive.properties file exists where the path property
	 * purports that it does.
	 */
	public boolean propFileExists() {
		String path = getProp("path");
		if( path == null ) {
			return false;
		}
		File file = new File(path);
		if (file.isFile()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns true if the properties are writable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public boolean propFileIsWritable() {
		String path = getProp("path");
		File file = new File(path);
		if (file.isFile()) {
			//See if we can write to the file
			if (file.canWrite()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
