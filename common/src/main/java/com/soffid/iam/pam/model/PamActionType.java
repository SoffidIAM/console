//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.pam.model;
/**
 * Enumeration PamActionType
 */
public class PamActionType
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final PamActionType CLOSE_SESSION= new PamActionType( new java.lang.String("C"));

	/**
	 */
	public static final PamActionType LOCK_ACCOUNT= new PamActionType( new java.lang.String("L"));

	/**
	 */
	public static final PamActionType NOTIFY= new PamActionType( new java.lang.String("N"));

	/**
	 */
	public static final PamActionType ISSUE= new PamActionType( new java.lang.String("I"));

	/**
	 */
	public static final PamActionType NONE= new PamActionType( new java.lang.String("-"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private PamActionType(java.lang.String value)
	{
		this.value=value;
	}

	protected PamActionType()
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
	 * Creates an instance of PamActionType from <code>value</code>.
	 *
	 * @param value the value to create the PamActionType from.
	 */
	public static PamActionType fromString(java.lang.String value)
	{
		final PamActionType typeValue = (PamActionType) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((PamActionType)that).getValue());
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
			|| (object instanceof PamActionType
			    && ((PamActionType)object).getValue().equals(this.getValue()));
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
		return PamActionType.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(5, 1);
	private static java.util.List literals = new java.util.ArrayList(5);
	private static java.util.List names = new java.util.ArrayList(5);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(CLOSE_SESSION.value, CLOSE_SESSION);
		literals.add(CLOSE_SESSION.value);
		names.add("CLOSE_SESSION");
		values.put(LOCK_ACCOUNT.value, LOCK_ACCOUNT);
		literals.add(LOCK_ACCOUNT.value);
		names.add("LOCK_ACCOUNT");
		values.put(NOTIFY.value, NOTIFY);
		literals.add(NOTIFY.value);
		names.add("NOTIFY");
		values.put(ISSUE.value, ISSUE);
		literals.add(ISSUE.value);
		names.add("ISSUE");
		values.put(NONE.value, NONE);
		literals.add(NONE.value);
		names.add("NONE");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
