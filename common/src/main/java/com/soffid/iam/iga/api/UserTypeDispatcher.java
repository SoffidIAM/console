//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject UserTypeDispatcher
 **/
public class UserTypeDispatcher

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute dispatcherCode

	 */
	private java.lang.String dispatcherCode;

	public UserTypeDispatcher()
	{
	}

	public UserTypeDispatcher(java.lang.Long id, java.lang.String type, java.lang.String dispatcherCode)
	{
		super();
		this.id = id;
		this.type = type;
		this.dispatcherCode = dispatcherCode;
	}

	public UserTypeDispatcher(java.lang.String type, java.lang.String dispatcherCode)
	{
		super();
		this.type = type;
		this.dispatcherCode = dispatcherCode;
	}

	public UserTypeDispatcher(UserTypeDispatcher otherBean)
	{
		this(otherBean.id, otherBean.type, otherBean.dispatcherCode);
	}

	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute dispatcherCode
	 */
	public java.lang.String getDispatcherCode() {
		return this.dispatcherCode;
	}

	/**
	 * Sets value for attribute dispatcherCode
	 */
	public void setDispatcherCode(java.lang.String dispatcherCode) {
		this.dispatcherCode = dispatcherCode;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", type: ");
		b.append (this.type);
		b.append (", dispatcherCode: ");
		b.append (this.dispatcherCode);
		b.append ("]");
		return b.toString();
	}

}
