//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.base.api;
/**
 * Enumeration MetadataScope
 */
public class MetadataScope
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final MetadataScope ROLE= new MetadataScope( new java.lang.String("role"));

	/**
	 */
	public static final MetadataScope USER= new MetadataScope( new java.lang.String("user"));

	/**
	 */
	public static final MetadataScope APPLICATION= new MetadataScope( new java.lang.String("application"));

	/**
	 */
	public static final MetadataScope GROUP= new MetadataScope( new java.lang.String("group"));

	/**
	 */
	public static final MetadataScope ACCOUNT= new MetadataScope( new java.lang.String("account"));

	/**
	 */
	public static final MetadataScope CUSTOM= new MetadataScope( new java.lang.String("custom"));

	/**
	 */
	public static final MetadataScope MAIL_LIST= new MetadataScope( new java.lang.String("mail_list"));

	/**
	 */
	public static final MetadataScope GROUP_MEMBERSHIP= new MetadataScope( new java.lang.String("group_membership"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private MetadataScope(java.lang.String value)
	{
		this.value=value;
	}

	protected MetadataScope()
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
	 * Creates an instance of MetadataScope from <code>value</code>.
	 *
	 * @param value the value to create the MetadataScope from.
	 */
	public static MetadataScope fromString(java.lang.String value)
	{
		final MetadataScope typeValue = (MetadataScope) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((MetadataScope)that).getValue());
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
			|| (object instanceof MetadataScope
			    && ((MetadataScope)object).getValue().equals(this.getValue()));
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
		return MetadataScope.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(8, 1);
	private static java.util.List literals = new java.util.ArrayList(8);
	private static java.util.List names = new java.util.ArrayList(8);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(ROLE.value, ROLE);
		literals.add(ROLE.value);
		names.add("ROLE");
		values.put(USER.value, USER);
		literals.add(USER.value);
		names.add("USER");
		values.put(APPLICATION.value, APPLICATION);
		literals.add(APPLICATION.value);
		names.add("APPLICATION");
		values.put(GROUP.value, GROUP);
		literals.add(GROUP.value);
		names.add("GROUP");
		values.put(ACCOUNT.value, ACCOUNT);
		literals.add(ACCOUNT.value);
		names.add("ACCOUNT");
		values.put(CUSTOM.value, CUSTOM);
		literals.add(CUSTOM.value);
		names.add("CUSTOM");
		values.put(MAIL_LIST.value, MAIL_LIST);
		literals.add(MAIL_LIST.value);
		names.add("MAIL_LIST");
		values.put(GROUP_MEMBERSHIP.value, GROUP_MEMBERSHIP);
		literals.add(GROUP_MEMBERSHIP.value);
		names.add("GROUP_MEMBERSHIP");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
