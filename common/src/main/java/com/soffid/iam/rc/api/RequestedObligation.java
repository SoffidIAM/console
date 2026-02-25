//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject RequestedObligation
 **/
public class RequestedObligation

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute obligation

	 */
	private java.lang.String obligation;

	/**
	 * Attribute attributes

	 */
	private java.util.Map<java.lang.String,java.lang.String> attributes;

	public RequestedObligation()
	{
	}

	public RequestedObligation(java.lang.String obligation, java.util.Map<java.lang.String,java.lang.String> attributes)
	{
		super();
		this.obligation = obligation;
		this.attributes = attributes;
	}

	public RequestedObligation(java.lang.String obligation)
	{
		super();
		this.obligation = obligation;
	}

	public RequestedObligation(RequestedObligation otherBean)
	{
		this(otherBean.obligation, otherBean.attributes);
	}

	/**
	 * Gets value for attribute obligation
	 */
	public java.lang.String getObligation() {
		return this.obligation;
	}

	/**
	 * Sets value for attribute obligation
	 */
	public void setObligation(java.lang.String obligation) {
		this.obligation = obligation;
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.String> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[obligation: ");
		b.append (this.obligation);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append ("]");
		return b.toString();
	}

}
