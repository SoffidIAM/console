//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.iga.api;
/**
 * Enumeration SoffidObjectType
 */
public class SoffidObjectType
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final SoffidObjectType OBJECT_USER= new SoffidObjectType( new java.lang.String("user"));

	/**
	 */
	public static final SoffidObjectType OBJECT_ACCOUNT= new SoffidObjectType( new java.lang.String("account"));

	/**
	 */
	public static final SoffidObjectType OBJECT_ROLE= new SoffidObjectType( new java.lang.String("role"));

	/**
	 */
	public static final SoffidObjectType OBJECT_GRANT= new SoffidObjectType( new java.lang.String("grant"));

	/**
	 */
	public static final SoffidObjectType OBJECT_GROUP= new SoffidObjectType( new java.lang.String("group"));

	/**
	 */
	public static final SoffidObjectType OBJECT_GRANTED_ROLE= new SoffidObjectType( new java.lang.String("grantedRole"));

	/**
	 */
	public static final SoffidObjectType OBJECT_ALL_GRANTED_ROLES= new SoffidObjectType( new java.lang.String("allGrantedRoles"));

	/**
	 */
	public static final SoffidObjectType OBJECT_GRANTED_GROUP= new SoffidObjectType( new java.lang.String("grantedGroup"));

	/**
	 */
	public static final SoffidObjectType OBJECT_ALL_GRANTED_GROUP= new SoffidObjectType( new java.lang.String("allGrantedGroup"));

	/**
	 */
	public static final SoffidObjectType OBJECT_AUTHORITATIVE_CHANGE= new SoffidObjectType( new java.lang.String("authChange"));

	/**
	 */
	public static final SoffidObjectType OBJECT_MAIL_LIST= new SoffidObjectType( new java.lang.String("mailList"));

	/**
	 */
	public static final SoffidObjectType OBJECT_CUSTOM= new SoffidObjectType( new java.lang.String("custom"));

	/**
	 */
	public static final SoffidObjectType OBJECT_HOST= new SoffidObjectType( new java.lang.String("host"));

	/**
	 */
	public static final SoffidObjectType OBJECT_NETWORK= new SoffidObjectType( new java.lang.String("network"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private SoffidObjectType(java.lang.String value)
	{
		this.value=value;
	}

	protected SoffidObjectType()
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
	 * Creates an instance of SoffidObjectType from <code>value</code>.
	 *
	 * @param value the value to create the SoffidObjectType from.
	 */
	public static SoffidObjectType fromString(java.lang.String value)
	{
		final SoffidObjectType typeValue = (SoffidObjectType) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((SoffidObjectType)that).getValue());
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
			|| (object instanceof SoffidObjectType
			    && ((SoffidObjectType)object).getValue().equals(this.getValue()));
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
		return SoffidObjectType.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(14, 1);
	private static java.util.List literals = new java.util.ArrayList(14);
	private static java.util.List names = new java.util.ArrayList(14);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(OBJECT_USER.value, OBJECT_USER);
		literals.add(OBJECT_USER.value);
		names.add("OBJECT_USER");
		values.put(OBJECT_ACCOUNT.value, OBJECT_ACCOUNT);
		literals.add(OBJECT_ACCOUNT.value);
		names.add("OBJECT_ACCOUNT");
		values.put(OBJECT_ROLE.value, OBJECT_ROLE);
		literals.add(OBJECT_ROLE.value);
		names.add("OBJECT_ROLE");
		values.put(OBJECT_GRANT.value, OBJECT_GRANT);
		literals.add(OBJECT_GRANT.value);
		names.add("OBJECT_GRANT");
		values.put(OBJECT_GROUP.value, OBJECT_GROUP);
		literals.add(OBJECT_GROUP.value);
		names.add("OBJECT_GROUP");
		values.put(OBJECT_GRANTED_ROLE.value, OBJECT_GRANTED_ROLE);
		literals.add(OBJECT_GRANTED_ROLE.value);
		names.add("OBJECT_GRANTED_ROLE");
		values.put(OBJECT_ALL_GRANTED_ROLES.value, OBJECT_ALL_GRANTED_ROLES);
		literals.add(OBJECT_ALL_GRANTED_ROLES.value);
		names.add("OBJECT_ALL_GRANTED_ROLES");
		values.put(OBJECT_GRANTED_GROUP.value, OBJECT_GRANTED_GROUP);
		literals.add(OBJECT_GRANTED_GROUP.value);
		names.add("OBJECT_GRANTED_GROUP");
		values.put(OBJECT_ALL_GRANTED_GROUP.value, OBJECT_ALL_GRANTED_GROUP);
		literals.add(OBJECT_ALL_GRANTED_GROUP.value);
		names.add("OBJECT_ALL_GRANTED_GROUP");
		values.put(OBJECT_AUTHORITATIVE_CHANGE.value, OBJECT_AUTHORITATIVE_CHANGE);
		literals.add(OBJECT_AUTHORITATIVE_CHANGE.value);
		names.add("OBJECT_AUTHORITATIVE_CHANGE");
		values.put(OBJECT_MAIL_LIST.value, OBJECT_MAIL_LIST);
		literals.add(OBJECT_MAIL_LIST.value);
		names.add("OBJECT_MAIL_LIST");
		values.put(OBJECT_CUSTOM.value, OBJECT_CUSTOM);
		literals.add(OBJECT_CUSTOM.value);
		names.add("OBJECT_CUSTOM");
		values.put(OBJECT_HOST.value, OBJECT_HOST);
		literals.add(OBJECT_HOST.value);
		names.add("OBJECT_HOST");
		values.put(OBJECT_NETWORK.value, OBJECT_NETWORK);
		literals.add(OBJECT_NETWORK.value);
		names.add("OBJECT_NETWORK");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
