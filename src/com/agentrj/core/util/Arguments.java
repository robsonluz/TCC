/*
 * Created on 23/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.util;
import java.util.Hashtable;
/**
 * @author Adminsitrador
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
final public class Arguments extends Hashtable {

	static final long serialVersionUID = -2495749088367637553L;

	/**
	 * In order to store null value in the Hashtable,
	 * NULL is used as a magic object to specify the null value.
	 */
	private final static String NULL = "null";

	/**
	 * Constructs a empty arguments object.
	 */
	public Arguments() {}
	public Object clone() {
		return super.clone();
	}
	/**
	 * Get the value associated with the name.
	 * @return the value associated with the given name
	 */
	public Object getArg(String name) {
		Object o = get(name);

		return o == NULL ? null : o;
	}
	/**
	 * Set a byte value with an associated name.
	 * @param name a name of this argument.
	 * @param value a byte value of this argument.
	 */
	public Object setArg(String name, byte value) {
		return super.put(name, new Byte(value));
	}
	/**
	 * Sets a character value with an associated name.
	 * @param name a name of this argument.
	 * @param value a character value of this argument.
	 */
	public Object setArg(String name, char value) {
		return super.put(name, new Character(value));
	}
	/**
	 * Set a double value with an associated name.
	 * @param name a name of this argument.
	 * @param d a double value of this argument.
	 */
	public Object setArg(String name, double value) {
		return super.put(name, new Double(value));
	}
	/**
	 * Set a float value with an associated name.
	 * @param name a name of this argument.
	 * @param value a float value of this argument.
	 */
	public Object setArg(String name, float value) {
		return super.put(name, new Float(value));
	}
	/**
	 * Set a int value with an associated name.
	 * @param name a name of this argument.
	 * @param value an integer value of this argument.
	 */
	public Object setArg(String name, int value) {
		return super.put(name, new Integer(value));
	}
	/**
	 * Sets a long value with an associated name.
	 * @param name a name of this argument.
	 * @param value a long value of this argument.
	 */
	public Object setArg(String name, long value) {
		return super.put(name, new Long(value));
	}
	/**
	 * Set a value with an associated name.
	 * @param name a name of this argument.
	 * @param value a value of this argument.
	 */
	public Object setArg(String name, Object value) {
		return value == null ? super.put(name, NULL) : super.put(name, value);
	}
	/**
	 * Set a short value with an associated name.
	 * @param name a name of this argument.
	 * @param value a short value of this argument.
	 */
	public Object setArg(String name, short value) {
		return super.put(name, new Short(value));
	}
	/**
	 * Set a boolean value with an associated name.
	 * @param name a name of this argument.
	 * @param value a boolean value of this argument.
	 */
	public Object setArg(String name, boolean value) {
		return super.put(name, new Boolean(value));
	}
}
