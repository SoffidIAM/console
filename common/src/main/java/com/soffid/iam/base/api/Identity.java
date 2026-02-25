//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject Identity
 **/
public class Identity

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute userCode

	 */
	private java.lang.String userCode;

	/**
	 * Attribute groupCode

	 */
	private java.lang.String groupCode;

	/**
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute identityCode

	 */
	private java.lang.String identityCode;

	public Identity()
	{
	}

	public Identity(java.lang.String userCode, java.lang.String groupCode, java.lang.String roleName, java.lang.String description, java.lang.String identityCode)
	{
		super();
		this.userCode = userCode;
		this.groupCode = groupCode;
		this.roleName = roleName;
		this.description = description;
		this.identityCode = identityCode;
	}

	public Identity(java.lang.String description, java.lang.String identityCode)
	{
		super();
		this.description = description;
		this.identityCode = identityCode;
	}

	public Identity(Identity otherBean)
	{
		this(otherBean.userCode, otherBean.groupCode, otherBean.roleName, otherBean.description, otherBean.identityCode);
	}

	/**
	 * Gets value for attribute userCode
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}

	/**
	 * Sets value for attribute userCode
	 */
	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
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
	 * Gets value for attribute roleName
	 */
	public java.lang.String getRoleName() {
		return this.roleName;
	}

	/**
	 * Sets value for attribute roleName
	 */
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute identityCode
	 */
	public java.lang.String getIdentityCode() {
		return this.identityCode;
	}

	/**
	 * Sets value for attribute identityCode
	 */
	public void setIdentityCode(java.lang.String identityCode) {
		this.identityCode = identityCode;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[userCode: ");
		b.append (this.userCode);
		b.append (", groupCode: ");
		b.append (this.groupCode);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", identityCode: ");
		b.append (this.identityCode);
		b.append ("]");
		return b.toString();
	}

}
