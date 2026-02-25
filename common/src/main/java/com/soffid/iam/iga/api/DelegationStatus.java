//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.iga.api;
/**
 * Enumeration DelegationStatus
 */
public class DelegationStatus
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final DelegationStatus DELEGATION_PENDING= new DelegationStatus( new java.lang.String("P"));

	/**
	 */
	public static final DelegationStatus DELEGATION_ACTIVE= new DelegationStatus( new java.lang.String("A"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private DelegationStatus(java.lang.String value)
	{
		this.value=value;
	}

	protected DelegationStatus()
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
	 * Creates an instance of DelegationStatus from <code>value</code>.
	 *
	 * @param value the value to create the DelegationStatus from.
	 */
	public static DelegationStatus fromString(java.lang.String value)
	{
		final DelegationStatus typeValue = (DelegationStatus) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((DelegationStatus)that).getValue());
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
			|| (object instanceof DelegationStatus
			    && ((DelegationStatus)object).getValue().equals(this.getValue()));
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
		return DelegationStatus.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(2, 1);
	private static java.util.List literals = new java.util.ArrayList(2);
	private static java.util.List names = new java.util.ArrayList(2);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(DELEGATION_PENDING.value, DELEGATION_PENDING);
		literals.add(DELEGATION_PENDING.value);
		names.add("DELEGATION_PENDING");
		values.put(DELEGATION_ACTIVE.value, DELEGATION_ACTIVE);
		literals.add(DELEGATION_ACTIVE.value);
		names.add("DELEGATION_ACTIVE");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
