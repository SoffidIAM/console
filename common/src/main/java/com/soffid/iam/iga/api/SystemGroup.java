//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject SystemGroup
 **/
public class SystemGroup

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
	 * Attribute systemCode

	 */
	private java.lang.String systemCode;

	/**
	 * Attribute groupCode

	 */
	private java.lang.String groupCode;

	public SystemGroup()
	{
	}

	public SystemGroup(java.lang.Long id, java.lang.String systemCode, java.lang.String groupCode)
	{
		super();
		this.id = id;
		this.systemCode = systemCode;
		this.groupCode = groupCode;
	}

	public SystemGroup(java.lang.String systemCode, java.lang.String groupCode)
	{
		super();
		this.systemCode = systemCode;
		this.groupCode = groupCode;
	}

	public SystemGroup(SystemGroup otherBean)
	{
		this(otherBean.id, otherBean.systemCode, otherBean.groupCode);
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
	 * Gets value for attribute systemCode
	 */
	public java.lang.String getSystemCode() {
		return this.systemCode;
	}

	/**
	 * Sets value for attribute systemCode
	 */
	public void setSystemCode(java.lang.String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * Gets value for attribute groupCode
	 */
	public java.lang.String getGroupCode() {
		return this.groupCode;
	}

	/**
	 * Sets value for attribute groupCode
	 */
	public void setGroupCode(java.lang.String groupCode) {
		this.groupCode = groupCode;
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
		b.append (", systemCode: ");
		b.append (this.systemCode);
		b.append (", groupCode: ");
		b.append (this.groupCode);
		b.append ("]");
		return b.toString();
	}

}
