//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.base.api;
/**
 * Enumeration TypeEnumeration
 */
public class TypeEnumeration
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final TypeEnumeration STRING_TYPE= new TypeEnumeration( new java.lang.String("S"));

	/**
	 */
	public static final TypeEnumeration NUMBER_TYPE= new TypeEnumeration( new java.lang.String("9"));

	/**
	 */
	public static final TypeEnumeration PASSWORD_TYPE= new TypeEnumeration( new java.lang.String("Z"));

	/**
	 */
	public static final TypeEnumeration BINARY_TYPE= new TypeEnumeration( new java.lang.String("B"));

	/**
	 */
	public static final TypeEnumeration BOOLEAN_TYPE= new TypeEnumeration( new java.lang.String("O"));

	/**
	 */
	public static final TypeEnumeration PHOTO_TYPE= new TypeEnumeration( new java.lang.String("P"));

	/**
	 */
	public static final TypeEnumeration DATE_TYPE= new TypeEnumeration( new java.lang.String("D"));

	/**
	 */
	public static final TypeEnumeration EMAIL_TYPE= new TypeEnumeration( new java.lang.String("E"));

	/**
	 */
	public static final TypeEnumeration ACCOUNT_TYPE= new TypeEnumeration( new java.lang.String("AC"));

	/**
	 */
	public static final TypeEnumeration USER_TYPE= new TypeEnumeration( new java.lang.String("U"));

	/**
	 */
	public static final TypeEnumeration GROUP_TYPE= new TypeEnumeration( new java.lang.String("G"));

	/**
	 */
	public static final TypeEnumeration GROUP_TYPE_TYPE= new TypeEnumeration( new java.lang.String("GT"));

	/**
	 */
	public static final TypeEnumeration ROLE_TYPE= new TypeEnumeration( new java.lang.String("R"));

	/**
	 */
	public static final TypeEnumeration APPLICATION_TYPE= new TypeEnumeration( new java.lang.String("A"));

	/**
	 */
	public static final TypeEnumeration HOST_TYPE= new TypeEnumeration( new java.lang.String("1"));

	/**
	 */
	public static final TypeEnumeration NETWORK_TYPE= new TypeEnumeration( new java.lang.String("N"));

	/**
	 */
	public static final TypeEnumeration USER_TYPE_TYPE= new TypeEnumeration( new java.lang.String("T"));

	/**
	 */
	public static final TypeEnumeration CUSTOM_OBJECT_TYPE= new TypeEnumeration( new java.lang.String("C"));

	/**
	 */
	public static final TypeEnumeration MAIL_DOMAIN_TYPE= new TypeEnumeration( new java.lang.String("M"));

	/**
	 */
	public static final TypeEnumeration MAIL_LIST_TYPE= new TypeEnumeration( new java.lang.String("ML"));

	/**
	 */
	public static final TypeEnumeration SSO_FORM_TYPE= new TypeEnumeration( new java.lang.String("F"));

	/**
	 */
	public static final TypeEnumeration HTML= new TypeEnumeration( new java.lang.String("H"));

	/**
	 */
	public static final TypeEnumeration SEPARATOR= new TypeEnumeration( new java.lang.String("-"));

	/**
	 */
	public static final TypeEnumeration DATE_TIME_TYPE= new TypeEnumeration( new java.lang.String("DT"));

	/**
	 */
	public static final TypeEnumeration OS_TYPE= new TypeEnumeration( new java.lang.String("1T"));

	/**
	 */
	public static final TypeEnumeration PRINTER_TYPE= new TypeEnumeration( new java.lang.String("PR"));

	/**
	 */
	public static final TypeEnumeration ATTACHMENT_TYPE= new TypeEnumeration( new java.lang.String("AT"));

	/**
	 */
	public static final TypeEnumeration SYSTEM_TYPE= new TypeEnumeration( new java.lang.String("SY"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private TypeEnumeration(java.lang.String value)
	{
		this.value=value;
	}

	protected TypeEnumeration()
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
	 * Creates an instance of TypeEnumeration from <code>value</code>.
	 *
	 * @param value the value to create the TypeEnumeration from.
	 */
	public static TypeEnumeration fromString(java.lang.String value)
	{
		final TypeEnumeration typeValue = (TypeEnumeration) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((TypeEnumeration)that).getValue());
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
			|| (object instanceof TypeEnumeration
			    && ((TypeEnumeration)object).getValue().equals(this.getValue()));
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
		return TypeEnumeration.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(28, 1);
	private static java.util.List literals = new java.util.ArrayList(28);
	private static java.util.List names = new java.util.ArrayList(28);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(STRING_TYPE.value, STRING_TYPE);
		literals.add(STRING_TYPE.value);
		names.add("STRING_TYPE");
		values.put(NUMBER_TYPE.value, NUMBER_TYPE);
		literals.add(NUMBER_TYPE.value);
		names.add("NUMBER_TYPE");
		values.put(PASSWORD_TYPE.value, PASSWORD_TYPE);
		literals.add(PASSWORD_TYPE.value);
		names.add("PASSWORD_TYPE");
		values.put(BINARY_TYPE.value, BINARY_TYPE);
		literals.add(BINARY_TYPE.value);
		names.add("BINARY_TYPE");
		values.put(BOOLEAN_TYPE.value, BOOLEAN_TYPE);
		literals.add(BOOLEAN_TYPE.value);
		names.add("BOOLEAN_TYPE");
		values.put(PHOTO_TYPE.value, PHOTO_TYPE);
		literals.add(PHOTO_TYPE.value);
		names.add("PHOTO_TYPE");
		values.put(DATE_TYPE.value, DATE_TYPE);
		literals.add(DATE_TYPE.value);
		names.add("DATE_TYPE");
		values.put(EMAIL_TYPE.value, EMAIL_TYPE);
		literals.add(EMAIL_TYPE.value);
		names.add("EMAIL_TYPE");
		values.put(ACCOUNT_TYPE.value, ACCOUNT_TYPE);
		literals.add(ACCOUNT_TYPE.value);
		names.add("ACCOUNT_TYPE");
		values.put(USER_TYPE.value, USER_TYPE);
		literals.add(USER_TYPE.value);
		names.add("USER_TYPE");
		values.put(GROUP_TYPE.value, GROUP_TYPE);
		literals.add(GROUP_TYPE.value);
		names.add("GROUP_TYPE");
		values.put(GROUP_TYPE_TYPE.value, GROUP_TYPE_TYPE);
		literals.add(GROUP_TYPE_TYPE.value);
		names.add("GROUP_TYPE_TYPE");
		values.put(ROLE_TYPE.value, ROLE_TYPE);
		literals.add(ROLE_TYPE.value);
		names.add("ROLE_TYPE");
		values.put(APPLICATION_TYPE.value, APPLICATION_TYPE);
		literals.add(APPLICATION_TYPE.value);
		names.add("APPLICATION_TYPE");
		values.put(HOST_TYPE.value, HOST_TYPE);
		literals.add(HOST_TYPE.value);
		names.add("HOST_TYPE");
		values.put(NETWORK_TYPE.value, NETWORK_TYPE);
		literals.add(NETWORK_TYPE.value);
		names.add("NETWORK_TYPE");
		values.put(USER_TYPE_TYPE.value, USER_TYPE_TYPE);
		literals.add(USER_TYPE_TYPE.value);
		names.add("USER_TYPE_TYPE");
		values.put(CUSTOM_OBJECT_TYPE.value, CUSTOM_OBJECT_TYPE);
		literals.add(CUSTOM_OBJECT_TYPE.value);
		names.add("CUSTOM_OBJECT_TYPE");
		values.put(MAIL_DOMAIN_TYPE.value, MAIL_DOMAIN_TYPE);
		literals.add(MAIL_DOMAIN_TYPE.value);
		names.add("MAIL_DOMAIN_TYPE");
		values.put(MAIL_LIST_TYPE.value, MAIL_LIST_TYPE);
		literals.add(MAIL_LIST_TYPE.value);
		names.add("MAIL_LIST_TYPE");
		values.put(SSO_FORM_TYPE.value, SSO_FORM_TYPE);
		literals.add(SSO_FORM_TYPE.value);
		names.add("SSO_FORM_TYPE");
		values.put(HTML.value, HTML);
		literals.add(HTML.value);
		names.add("HTML");
		values.put(SEPARATOR.value, SEPARATOR);
		literals.add(SEPARATOR.value);
		names.add("SEPARATOR");
		values.put(DATE_TIME_TYPE.value, DATE_TIME_TYPE);
		literals.add(DATE_TIME_TYPE.value);
		names.add("DATE_TIME_TYPE");
		values.put(OS_TYPE.value, OS_TYPE);
		literals.add(OS_TYPE.value);
		names.add("OS_TYPE");
		values.put(PRINTER_TYPE.value, PRINTER_TYPE);
		literals.add(PRINTER_TYPE.value);
		names.add("PRINTER_TYPE");
		values.put(ATTACHMENT_TYPE.value, ATTACHMENT_TYPE);
		literals.add(ATTACHMENT_TYPE.value);
		names.add("ATTACHMENT_TYPE");
		values.put(SYSTEM_TYPE.value, SYSTEM_TYPE);
		literals.add(SYSTEM_TYPE.value);
		names.add("SYSTEM_TYPE");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
