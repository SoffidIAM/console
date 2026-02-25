//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.am.api;
/**
 * Enumeration SessionType
 */
public class SessionType
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final SessionType WSSO= new SessionType( new java.lang.String("W"));

	/**
	 */
	public static final SessionType ESSO= new SessionType( new java.lang.String("E"));

	/**
	 */
	public static final SessionType PAM= new SessionType( new java.lang.String("P"));

	/**
	 */
	public static final SessionType PAMSSH= new SessionType( new java.lang.String("S"));

	/**
	 */
	public static final SessionType PAMRDP= new SessionType( new java.lang.String("R"));

	/**
	 */
	public static final SessionType CONSOLE= new SessionType( new java.lang.String("C"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private SessionType(java.lang.String value)
	{
		this.value=value;
	}

	protected SessionType()
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
	 * Creates an instance of SessionType from <code>value</code>.
	 *
	 * @param value the value to create the SessionType from.
	 */
	public static SessionType fromString(java.lang.String value)
	{
		final SessionType typeValue = (SessionType) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((SessionType)that).getValue());
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
			|| (object instanceof SessionType
			    && ((SessionType)object).getValue().equals(this.getValue()));
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
		return SessionType.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(6, 1);
	private static java.util.List literals = new java.util.ArrayList(6);
	private static java.util.List names = new java.util.ArrayList(6);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(WSSO.value, WSSO);
		literals.add(WSSO.value);
		names.add("WSSO");
		values.put(ESSO.value, ESSO);
		literals.add(ESSO.value);
		names.add("ESSO");
		values.put(PAM.value, PAM);
		literals.add(PAM.value);
		names.add("PAM");
		values.put(PAMSSH.value, PAMSSH);
		literals.add(PAMSSH.value);
		names.add("PAMSSH");
		values.put(PAMRDP.value, PAMRDP);
		literals.add(PAMRDP.value);
		names.add("PAMRDP");
		values.put(CONSOLE.value, CONSOLE);
		literals.add(CONSOLE.value);
		names.add("CONSOLE");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
