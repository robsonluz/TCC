/*
 * Created on 29/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home;
import com.agentrj.core.util.*;

/**
 * @author Adminsitrador *  * To change the template for this generated type comment go to * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


public class ConfHome extends Conf
{

	/**
	 * 
	 * @uml.property name="conf"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private static Conf conf = new Conf("/home.ini");

	public static String getProperty(String name) {
		return getProperty(name,conf);
	}
}

