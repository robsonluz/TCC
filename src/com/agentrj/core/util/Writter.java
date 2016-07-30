/*
 * Created on 30/12/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.util;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Writter
{
	private PrintStream out;
	private PrintStream err;
	private String language;
	private String country;
	private String dateFormat;
	private String logFormat;
	
	public Writter(PrintStream out,PrintStream err,String language,String country,String dateFormat,String logFormat)
	{
		this.out = out;
		this.err = err;
		this.language = language;
		this.country = country;
		this.dateFormat = dateFormat;
		this.logFormat = logFormat;
	}
	
	public Writter()
	{
		out = System.out;
		err = System.err;
		language = "pt";
		country = "BR";
		dateFormat = "dd'/'MM'/'yyyy' 'HH':'mm':'ss";
		logFormat = "<date> = <log>";
	}
	
	private String formatLog(Object msg)
	{
		if(msg!=null){
			String ret = logFormat;
			ret = ret.replaceAll("<date>",getDate());
			ret = ret.replaceAll("<log>",msg.toString());
			return ret;
		}
		return "";
	}
	
	public void print(Object obj)
	{
		out.print(formatLog(obj));
	}
	
	public void println(Object obj)
	{
		out.println(formatLog(obj));
	}
	
	public void printErr(Object obj)
	{
		err.print(formatLog(obj));
	}
	
	public void printlnErr(Object obj)
	{
		err.println(formatLog(obj));
	}
	
	private String getDate()
	{
		Date date = new Date();
		Locale locale = new Locale (language,country);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat,locale);
		return formatter.format(date);
	}

	/**
	 * @return
	 * 
	 * @uml.property name="err"
	 */
	public PrintStream getErr() {
		return err;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="out"
	 */
	public PrintStream getOut() {
		return out;
	}

	/**
	 * @param stream
	 * 
	 * @uml.property name="err"
	 */
	public void setErr(PrintStream stream) {
		err = stream;
	}

	/**
	 * @param stream
	 * 
	 * @uml.property name="out"
	 */
	public void setOut(PrintStream stream) {
		out = stream;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="country"
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="language"
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="country"
	 */
	public void setCountry(String string) {
		country = string;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="language"
	 */
	public void setLanguage(String string) {
		language = string;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="dateFormat"
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="dateFormat"
	 */
	public void setDateFormat(String string) {
		dateFormat = string;
	}

	/**
	 * @return
	 * 
	 * @uml.property name="logFormat"
	 */
	public String getLogFormat() {
		return logFormat;
	}

	/**
	 * @param string
	 * 
	 * @uml.property name="logFormat"
	 */
	public void setLogFormat(String string) {
		logFormat = string;
	}

}