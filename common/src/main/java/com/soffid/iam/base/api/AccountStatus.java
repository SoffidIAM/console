//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.base.api;
/**
 * Enumeration AccountStatus
 */
public class AccountStatus
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final AccountStatus ACTIVE= new AccountStatus( new java.lang.String("a"));

	/**
	 */
	public static final AccountStatus FORCED_ACTIVE= new AccountStatus( new java.lang.String("FA"));

	/**
	 */
	public static final AccountStatus LOCKED= new AccountStatus( new java.lang.String("l"));

	/**
	 */
	public static final AccountStatus DISABLED= new AccountStatus( new java.lang.String("d"));

	/**
	 */
	public static final AccountStatus FORCED_DISABLED= new AccountStatus( new java.lang.String("FD"));

	/**
	 */
	public static final AccountStatus REMOVED= new AccountStatus( new java.lang.String("r"));

	/**
	 */
	public static final AccountStatus ARCHIVED= new AccountStatus( new java.lang.String("x"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private AccountStatus(java.lang.String value)
	{
		this.value=value;
	}

	protected AccountStatus()
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
	 * Creates an instance of AccountStatus from <code>value</code>.
	 *
	 * @param value the value to create the AccountStatus from.
	 */
	public static AccountStatus fromString(java.lang.String value)
	{
		final AccountStatus typeValue = (AccountStatus) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((AccountStatus)that).getValue());
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
			|| (object instanceof AccountStatus
			    && ((AccountStatus)object).getValue().equals(this.getValue()));
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
		return AccountStatus.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(7, 1);
	private static java.util.List literals = new java.util.ArrayList(7);
	private static java.util.List names = new java.util.ArrayList(7);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(ACTIVE.value, ACTIVE);
		literals.add(ACTIVE.value);
		names.add("ACTIVE");
		values.put(FORCED_ACTIVE.value, FORCED_ACTIVE);
		literals.add(FORCED_ACTIVE.value);
		names.add("FORCED_ACTIVE");
		values.put(LOCKED.value, LOCKED);
		literals.add(LOCKED.value);
		names.add("LOCKED");
		values.put(DISABLED.value, DISABLED);
		literals.add(DISABLED.value);
		names.add("DISABLED");
		values.put(FORCED_DISABLED.value, FORCED_DISABLED);
		literals.add(FORCED_DISABLED.value);
		names.add("FORCED_DISABLED");
		values.put(REMOVED.value, REMOVED);
		literals.add(REMOVED.value);
		names.add("REMOVED");
		values.put(ARCHIVED.value, ARCHIVED);
		literals.add(ARCHIVED.value);
		names.add("ARCHIVED");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
