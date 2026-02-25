//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject GroupRoles
 **/
public class GroupRoles

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
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute roleDescription

	 */
	private java.lang.String roleDescription;

	/**
	 * Attribute roleDatabases

	 */
	private java.lang.String roleDatabases;

	/**
	 * Attribute applicationCode

	 */
	private java.lang.String applicationCode;

	/**
	 * Attribute groupCode

	 */
	private java.lang.String groupCode;

	/**
	 * Attribute groupDescription

	 */
	private java.lang.String groupDescription;

	/**
	 * Attribute domainValue

	 */
	private com.soffid.iam.iga.api.DomainValue domainValue;

	public GroupRoles()
	{
	}

	public GroupRoles(java.lang.Long id, java.lang.String roleName, java.lang.String roleDescription, java.lang.String roleDatabases, java.lang.String applicationCode, java.lang.String groupCode, java.lang.String groupDescription, com.soffid.iam.iga.api.DomainValue domainValue)
	{
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleDatabases = roleDatabases;
		this.applicationCode = applicationCode;
		this.groupCode = groupCode;
		this.groupDescription = groupDescription;
		this.domainValue = domainValue;
	}

	public GroupRoles(java.lang.String roleName, java.lang.String roleDescription, java.lang.String roleDatabases, java.lang.String applicationCode, java.lang.String groupCode, java.lang.String groupDescription, com.soffid.iam.iga.api.DomainValue domainValue)
	{
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleDatabases = roleDatabases;
		this.applicationCode = applicationCode;
		this.groupCode = groupCode;
		this.groupDescription = groupDescription;
		this.domainValue = domainValue;
	}

	public GroupRoles(GroupRoles otherBean)
	{
		this(otherBean.id, otherBean.roleName, otherBean.roleDescription, otherBean.roleDatabases, otherBean.applicationCode, otherBean.groupCode, otherBean.groupDescription, otherBean.domainValue);
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
	 * Gets value for attribute roleDescription
	 */
	public java.lang.String getRoleDescription() {
		return this.roleDescription;
	}

	/**
	 * Sets value for attribute roleDescription
	 */
	public void setRoleDescription(java.lang.String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * Gets value for attribute roleDatabases
	 */
	public java.lang.String getRoleDatabases() {
		return this.roleDatabases;
	}

	/**
	 * Sets value for attribute roleDatabases
	 */
	public void setRoleDatabases(java.lang.String roleDatabases) {
		this.roleDatabases = roleDatabases;
	}

	/**
	 * Gets value for attribute applicationCode
	 */
	public java.lang.String getApplicationCode() {
		return this.applicationCode;
	}

	/**
	 * Sets value for attribute applicationCode
	 */
	public void setApplicationCode(java.lang.String applicationCode) {
		this.applicationCode = applicationCode;
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
	 * Gets value for attribute groupDescription
	 */
	public java.lang.String getGroupDescription() {
		return this.groupDescription;
	}

	/**
	 * Sets value for attribute groupDescription
	 */
	public void setGroupDescription(java.lang.String groupDescription) {
		this.groupDescription = groupDescription;
	}

	/**
	 * Gets value for attribute domainValue
	 */
	public com.soffid.iam.iga.api.DomainValue getDomainValue() {
		return this.domainValue;
	}

	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(com.soffid.iam.iga.api.DomainValue domainValue) {
		this.domainValue = domainValue;
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
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", roleDescription: ");
		b.append (this.roleDescription);
		b.append (", roleDatabases: ");
		b.append (this.roleDatabases);
		b.append (", applicationCode: ");
		b.append (this.applicationCode);
		b.append (", groupCode: ");
		b.append (this.groupCode);
		b.append (", groupDescription: ");
		b.append (this.groupDescription);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append ("]");
		return b.toString();
	}

}
