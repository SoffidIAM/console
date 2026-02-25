//
// (C) 2020 Soffid
//
//
// Attention: Generated code! Do not modify by hand!
//
package com.soffid.iam.rc.api;
/**
 * Enumeration RequestedObligationEnum
 */
public class RequestedObligationEnum
		implements java.io.Serializable
 {

	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 */
	public static final RequestedObligationEnum OTP= new RequestedObligationEnum( new java.lang.String("urn:soffid:obligation:otp"));

	/**
	 */
	public static final RequestedObligationEnum MESSAGE= new RequestedObligationEnum( new java.lang.String("urn:soffid:obligation:message"));

	/**
	 */
	public static final RequestedObligationEnum WORKFLOW= new RequestedObligationEnum( new java.lang.String("urn:soffid:obligation:bpm"));

	/**
	 */
	public static final RequestedObligationEnum SESSION_RECORDING= new RequestedObligationEnum( new java.lang.String("urn:soffid:obligation:session-recording"));

	/**
	 */
	public static final RequestedObligationEnum NOTIFY_OWNER= new RequestedObligationEnum( new java.lang.String("urn:soffid:obligation:notify-owner"));

	/**
	 * The default constructor, allowing super classes to access it
	 */
	private java.lang.String value;

	private RequestedObligationEnum(java.lang.String value)
	{
		this.value=value;
	}

	protected RequestedObligationEnum()
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
	 * Creates an instance of RequestedObligationEnum from <code>value</code>.
	 *
	 * @param value the value to create the RequestedObligationEnum from.
	 */
	public static RequestedObligationEnum fromString(java.lang.String value)
	{
		final RequestedObligationEnum typeValue = (RequestedObligationEnum) values.get(value);
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
		return (this == that) ? 0 : this.getValue().compareTo(((RequestedObligationEnum)that).getValue());
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
			|| (object instanceof RequestedObligationEnum
			    && ((RequestedObligationEnum)object).getValue().equals(this.getValue()));
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
		return RequestedObligationEnum.fromString(this.value);
	}

	private static final java.util.Map values = new java.util.HashMap(5, 1);
	private static java.util.List literals = new java.util.ArrayList(5);
	private static java.util.List names = new java.util.ArrayList(5);

	/**
	 * Initializes the values.
	 */
	static
	{
		values.put(OTP.value, OTP);
		literals.add(OTP.value);
		names.add("OTP");
		values.put(MESSAGE.value, MESSAGE);
		literals.add(MESSAGE.value);
		names.add("MESSAGE");
		values.put(WORKFLOW.value, WORKFLOW);
		literals.add(WORKFLOW.value);
		names.add("WORKFLOW");
		values.put(SESSION_RECORDING.value, SESSION_RECORDING);
		literals.add(SESSION_RECORDING.value);
		names.add("SESSION_RECORDING");
		values.put(NOTIFY_OWNER.value, NOTIFY_OWNER);
		literals.add(NOTIFY_OWNER.value);
		names.add("NOTIFY_OWNER");
		literals = java.util.Collections.unmodifiableList(literals);
		names = java.util.Collections.unmodifiableList(names);
	}
}
