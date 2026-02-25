//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.iga.api;
/**
 * Enumeration SoffidObjectTrigger
 */
public class SoffidObjectTrigger
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final SoffidObjectTrigger PRE_INSERT= new SoffidObjectTrigger( new java.lang.String("preInsert"));

	/**
	 */
	public static final SoffidObjectTrigger PRE_UPDATE= new SoffidObjectTrigger( new java.lang.String("preUpdate"));

	/**
	 */
	public static final SoffidObjectTrigger PRE_DELETE= new SoffidObjectTrigger( new java.lang.String("preDelete"));

	/**
	 */
	public static final SoffidObjectTrigger POST_INSERT= new SoffidObjectTrigger( new java.lang.String("postInsert"));

	/**
	 */
	public static final SoffidObjectTrigger POST_UPDATE= new SoffidObjectTrigger( new java.lang.String("postUpdate"));

	/**
	 */
	public static final SoffidObjectTrigger POST_DELETE= new SoffidObjectTrigger( new java.lang.String("postDelete"));

	/**
	 */
	public static final SoffidObjectTrigger PRE_SET_PASSWORD= new SoffidObjectTrigger( new java.lang.String("preSetPassword"));

	/**
	 */
	public static final SoffidObjectTrigger POST_SET_PASSWORD= new SoffidObjectTrigger( new java.lang.String("postSetPassword"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private SoffidObjectTrigger(java.lang.String value)
	{
		this.value=value;
	}

	protected SoffidObjectTrigger()
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
	 * Creates an instance of SoffidObjectTrigger from <code>value</code>.
	 *
	 * @param value the value to create the SoffidObjectTrigger from.
	 */
	public static SoffidObjectTrigger fromString(java.lang.String value)
	{
		final SoffidObjectTrigger typeValue = (SoffidObjectTrigger) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((SoffidObjectTrigger)that).getValue());
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
			|| (object instanceof SoffidObjectTrigger
			    && ((SoffidObjectTrigger)object).getValue().equals(this.getValue()));
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
		return SoffidObjectTrigger.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(8, 1);
	private static java.util.List literals = new java.util.ArrayList(8);
	private static java.util.List names = new java.util.ArrayList(8);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(PRE_INSERT.value, PRE_INSERT);
		literals.add(PRE_INSERT.value);
		names.add("PRE_INSERT");
		values.put(PRE_UPDATE.value, PRE_UPDATE);
		literals.add(PRE_UPDATE.value);
		names.add("PRE_UPDATE");
		values.put(PRE_DELETE.value, PRE_DELETE);
		literals.add(PRE_DELETE.value);
		names.add("PRE_DELETE");
		values.put(POST_INSERT.value, POST_INSERT);
		literals.add(POST_INSERT.value);
		names.add("POST_INSERT");
		values.put(POST_UPDATE.value, POST_UPDATE);
		literals.add(POST_UPDATE.value);
		names.add("POST_UPDATE");
		values.put(POST_DELETE.value, POST_DELETE);
		literals.add(POST_DELETE.value);
		names.add("POST_DELETE");
		values.put(PRE_SET_PASSWORD.value, PRE_SET_PASSWORD);
		literals.add(PRE_SET_PASSWORD.value);
		names.add("PRE_SET_PASSWORD");
		values.put(POST_SET_PASSWORD.value, POST_SET_PASSWORD);
		literals.add(POST_SET_PASSWORD.value);
		names.add("POST_SET_PASSWORD");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
