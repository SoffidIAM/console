//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.iga.api;
/**
 * Enumeration AccountProposedAction
 */
public class AccountProposedAction
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final AccountProposedAction IGNORE= new AccountProposedAction( new java.lang.String("I"));

	/**
	 */
	public static final AccountProposedAction CREATE_NEW_USER= new AccountProposedAction( new java.lang.String("N"));

	/**
	 */
	public static final AccountProposedAction BIND_TO_EXISTING_USER= new AccountProposedAction( new java.lang.String("B"));

	/**
	 */
	public static final AccountProposedAction SHARED= new AccountProposedAction( new java.lang.String("S"));

	/**
	 */
	public static final AccountProposedAction UPDATE_ACCOUNT= new AccountProposedAction( new java.lang.String("U"));

	/**
	 */
	public static final AccountProposedAction DELETE_ACCOUNT= new AccountProposedAction( new java.lang.String("D"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private AccountProposedAction(java.lang.String value)
	{
		this.value=value;
	}

	protected AccountProposedAction()
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
	 * Creates an instance of AccountProposedAction from <code>value</code>.
	 *
	 * @param value the value to create the AccountProposedAction from.
	 */
	public static AccountProposedAction fromString(java.lang.String value)
	{
		final AccountProposedAction typeValue = (AccountProposedAction) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((AccountProposedAction)that).getValue());
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
			|| (object instanceof AccountProposedAction
			    && ((AccountProposedAction)object).getValue().equals(this.getValue()));
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
		return AccountProposedAction.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(6, 1);
	private static java.util.List literals = new java.util.ArrayList(6);
	private static java.util.List names = new java.util.ArrayList(6);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(IGNORE.value, IGNORE);
		literals.add(IGNORE.value);
		names.add("IGNORE");
		values.put(CREATE_NEW_USER.value, CREATE_NEW_USER);
		literals.add(CREATE_NEW_USER.value);
		names.add("CREATE_NEW_USER");
		values.put(BIND_TO_EXISTING_USER.value, BIND_TO_EXISTING_USER);
		literals.add(BIND_TO_EXISTING_USER.value);
		names.add("BIND_TO_EXISTING_USER");
		values.put(SHARED.value, SHARED);
		literals.add(SHARED.value);
		names.add("SHARED");
		values.put(UPDATE_ACCOUNT.value, UPDATE_ACCOUNT);
		literals.add(UPDATE_ACCOUNT.value);
		names.add("UPDATE_ACCOUNT");
		values.put(DELETE_ACCOUNT.value, DELETE_ACCOUNT);
		literals.add(DELETE_ACCOUNT.value);
		names.add("DELETE_ACCOUNT");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
