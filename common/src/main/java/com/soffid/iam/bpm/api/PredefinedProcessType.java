//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.bpm.api;
/**
 * Enumeration PredefinedProcessType
 */
public class PredefinedProcessType
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final PredefinedProcessType ROLE_GRANT_APPROVAL= new PredefinedProcessType( new java.lang.String("RoleApproval"));

	/**
	 */
	public static final PredefinedProcessType ROLE_DEFINITION_APPROVAL= new PredefinedProcessType( new java.lang.String("RoleDefApproval"));

	/**
	 */
	public static final PredefinedProcessType AUTHORITATIVE_CHANGE= new PredefinedProcessType( new java.lang.String("AuthoritativeChange"));

	/**
	 */
	public static final PredefinedProcessType PRIVILEGED_ACCOUNT= new PredefinedProcessType( new java.lang.String("PrivilegedAccount"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private PredefinedProcessType(java.lang.String value)
	{
		this.value=value;
	}

	protected PredefinedProcessType()
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
	 * Creates an instance of PredefinedProcessType from <code>value</code>.
	 *
	 * @param value the value to create the PredefinedProcessType from.
	 */
	public static PredefinedProcessType fromString(java.lang.String value)
	{
		final PredefinedProcessType typeValue = (PredefinedProcessType) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((PredefinedProcessType)that).getValue());
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
			|| (object instanceof PredefinedProcessType
			    && ((PredefinedProcessType)object).getValue().equals(this.getValue()));
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
		return PredefinedProcessType.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(4, 1);
	private static java.util.List literals = new java.util.ArrayList(4);
	private static java.util.List names = new java.util.ArrayList(4);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(ROLE_GRANT_APPROVAL.value, ROLE_GRANT_APPROVAL);
		literals.add(ROLE_GRANT_APPROVAL.value);
		names.add("ROLE_GRANT_APPROVAL");
		values.put(ROLE_DEFINITION_APPROVAL.value, ROLE_DEFINITION_APPROVAL);
		literals.add(ROLE_DEFINITION_APPROVAL.value);
		names.add("ROLE_DEFINITION_APPROVAL");
		values.put(AUTHORITATIVE_CHANGE.value, AUTHORITATIVE_CHANGE);
		literals.add(AUTHORITATIVE_CHANGE.value);
		names.add("AUTHORITATIVE_CHANGE");
		values.put(PRIVILEGED_ACCOUNT.value, PRIVILEGED_ACCOUNT);
		literals.add(PRIVILEGED_ACCOUNT.value);
		names.add("PRIVILEGED_ACCOUNT");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
