/*
 * Created on 06/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.home.security;
import java.security.Permission;
import java.security.*;
import java.io.FileDescriptor;
import java.net.InetAddress;
/**
 * @author Adminsitrador
 *
 */
public class HomeSecurityManager extends SecurityManager
{
	public HomeSecurityManager()
	{
		super();
	}
	
	
	/**
	 * Loaded classes are allowed to create class loaders.
	 */
	public synchronized void checkCreateClassLoader()
	{
	// Provide null override
	//System.out.println( "checkCreateClassLoader()");
	}
    

	/**
	 * Connections to other machines are allowed
	 */
	public synchronized void checkConnect( String host, int port )
	{
		//Policy.getPolicy().refresh();
		//super.checkConnect(host,port);
		//System.out.println("checkConnect: "+host+":"+port);
	}

	/**
	 * Loaded classes are allowed to manipulate threads.
	 */
	public synchronized void checkAccess(Thread t)
	{
	// Provide null override
	//System.out.println( "checkAccess()");
	}

	/**
	 * Loaded classes are allowed to manipulate thread groups.
	 */
	public synchronized void checkAccess(ThreadGroup g)
	{
	// Provide null override
	//System.out.println( "checkAccess()");
	}


	/**
	 * Loaded classes are allowed to access the system properties list.
	 */
	public synchronized void checkPropertiesAccess()
	{
	// Provide null override
	//System.out.println( "checkPropertiesAccess()");
	}

	public void checkAccept(String host, int port) 
	{
		//System.out.println("checkAccept: "+host+":"+port);
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkConnect(java.lang.String, int, java.lang.Object)
	 */
	public void checkConnect(String host, int port, Object context) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkDelete(java.lang.String)
	 */
	public void checkDelete(String file) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkExec(java.lang.String)
	 */
	public void checkExec(String cmd) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkExit(int)
	 */
	public void checkExit(int status) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkLink(java.lang.String)
	 */
	public void checkLink(String lib) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkListen(int)
	 */
	public void checkListen(int port) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkMemberAccess(java.lang.Class, int)
	 */
	public void checkMemberAccess(Class clazz, int which) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkMulticast(java.net.InetAddress, byte)
	 */
	public void checkMulticast(InetAddress maddr, byte ttl) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkMulticast(java.net.InetAddress)
	 */
	public void checkMulticast(InetAddress maddr) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPackageAccess(java.lang.String)
	 */
	public void checkPackageAccess(String pkg) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPackageDefinition(java.lang.String)
	 */
	public void checkPackageDefinition(String pkg) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPermission(java.security.Permission, java.lang.Object)
	 */
	public void checkPermission(Permission perm, Object context)
	{
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPermission(java.security.Permission)
	 */
	public void checkPermission(Permission perm) {
		//super.checkPermission(perm);
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPrintJobAccess()
	 */
	public void checkPrintJobAccess() {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkPropertyAccess(java.lang.String)
	 */
	public void checkPropertyAccess(String key) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkRead(java.io.FileDescriptor)
	 */
	public void checkRead(FileDescriptor fd) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkRead(java.lang.String, java.lang.Object)
	 */
	public void checkRead(String file, Object context) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkRead(java.lang.String)
	 */
	public void checkRead(String file) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkSecurityAccess(java.lang.String)
	 */
	public void checkSecurityAccess(String target) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkSetFactory()
	 */
	public void checkSetFactory() {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkSystemClipboardAccess()
	 */
	public void checkSystemClipboardAccess() {
	}


	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkWrite(java.io.FileDescriptor)
	 */
	public void checkWrite(FileDescriptor fd) {
	}

	/* (non-Javadoc)
	 * @see java.lang.SecurityManager#checkWrite(java.lang.String)
	 */
	public void checkWrite(String file) {
	}

}

