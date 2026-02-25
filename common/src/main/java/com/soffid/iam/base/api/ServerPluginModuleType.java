//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.base.api;
/**
 * Enumeration ServerPluginModuleType
 */
public class ServerPluginModuleType
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final ServerPluginModuleType MODULE_WEB= new ServerPluginModuleType( new java.lang.String("W"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_CORE= new ServerPluginModuleType( new java.lang.String("C"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_SYNCSERVER= new ServerPluginModuleType( new java.lang.String("S"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_AGENT= new ServerPluginModuleType( new java.lang.String("A"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_COMMON= new ServerPluginModuleType( new java.lang.String("V"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_WEBSERVICE= new ServerPluginModuleType( new java.lang.String("X"));

	/**
	 */
	public static final ServerPluginModuleType MODULE_SELFSERVICE= new ServerPluginModuleType( new java.lang.String("E"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private ServerPluginModuleType(java.lang.String value)
	{
		this.value=value;
	}

	protected ServerPluginModuleType()
	{
	}

	/**
	 *  @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return java.lang.String.valueOf(value);
	}
	/**
	 * Creates an instance of ServerPluginModuleType from <code>value</code>.
	 *
	 * @param value the value to create the ServerPluginModuleType from.
	 */
	public static ServerPluginModuleType fromString(java.lang.String value)
	{
		final ServerPluginModuleType typeValue = (ServerPluginModuleType) values.get(value);
		if (typeValue == null)
			throw new IllegalArgumentException("invalue value '" + value + "', possible vaues are: " + literals); 
		return typeValue;
	}

	/**
	 * Gets the underlying value of this type safe enumeration.
	 *
	 * @return the underlying value.
	 */
	public java.lang.String getValue()
	{
		return this.value;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object that)
	{
		return (this == that) ? 0 : this.getValue().compareTo(((ServerPluginModuleType)that).getValue());
	}

	/**
	 * Returns an unmodifiable list containing the literals that are known by this enumeration.
	 *
	 * @return A List containing the actual literals defined by this enumeration, this list
	 *         can not be modified.
	 */
	public static java.util.List literals()
	{
		return literals;
	}

	/**
	 * Returns an unmodifiable list containing the names of the literals that are known
	 * by this enumeration.
	 *
	 * @return A List containing the actual names of the literals defined by this
	 *         enumeration, this list can not be modified.
	 */
	public static java.util.List names()
	{
		return names;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object)
	{
		return (this == object)
			|| (object instanceof ServerPluginModuleType
			    && ((ServerPluginModuleType)object).getValue().equals(this.getValue()));
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return this.getValue().hashCode();
	}

	/**
	 * This method allows the deserialization of an instance of this enumeration type to return the actual instance
	 * that will be the singleton for the JVM in which the current thread is running.
	 * Doing this will allow users to safely use the equality operator <code>==</code> for enumerations because
	 * a regular deserialized object is always a newly constructed instance and will therefore never be
	 * an existing reference; it is this <code>readResolve()</code> method which will intercept the deserialization
	 * process in order to return the proper singleton reference.
	 * This method is documented here:
	 * <a href="http://java.sun.com/j2se/1.3/docs/guide/serialization/spec/input.doc6.html">Java
	 * Object Serialization Specification</a>
	 */
	private java.lang.Object readResolve() throws java.io.ObjectStreamException
	{
		return ServerPluginModuleType.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(7, 1);
	private static java.util.List literals = new java.util.ArrayList(7);
	private static java.util.List names = new java.util.ArrayList(7);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(MODULE_WEB.value, MODULE_WEB);
		literals.add(MODULE_WEB.value);
		names.add("MODULE_WEB");
		values.put(MODULE_CORE.value, MODULE_CORE);
		literals.add(MODULE_CORE.value);
		names.add("MODULE_CORE");
		values.put(MODULE_SYNCSERVER.value, MODULE_SYNCSERVER);
		literals.add(MODULE_SYNCSERVER.value);
		names.add("MODULE_SYNCSERVER");
		values.put(MODULE_AGENT.value, MODULE_AGENT);
		literals.add(MODULE_AGENT.value);
		names.add("MODULE_AGENT");
		values.put(MODULE_COMMON.value, MODULE_COMMON);
		literals.add(MODULE_COMMON.value);
		names.add("MODULE_COMMON");
		values.put(MODULE_WEBSERVICE.value, MODULE_WEBSERVICE);
		literals.add(MODULE_WEBSERVICE.value);
		names.add("MODULE_WEBSERVICE");
		values.put(MODULE_SELFSERVICE.value, MODULE_SELFSERVICE);
		literals.add(MODULE_SELFSERVICE.value);
		names.add("MODULE_SELFSERVICE");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
