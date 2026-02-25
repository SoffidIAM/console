//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject MailListRoleMember
 **/
public class MailListRoleMember

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute roleName
	 * Name of the role member

	 */
	private java.lang.String roleName;

	/**
	 * Attribute dispatcherName
	 * Managed system where role lives on

	 */
	private java.lang.String dispatcherName;

	/**
	 * Attribute scope
	 * Scope value for scoped roles

	 */
	private java.lang.String scope;

	/**
	 * Attribute roleDescription
	 * Role description

	 */
	private java.lang.String roleDescription;

	public MailListRoleMember()
	{
	}

	public MailListRoleMember(java.lang.String roleName, java.lang.String dispatcherName, java.lang.String scope, java.lang.String roleDescription)
	{
		super();
		this.roleName = roleName;
		this.dispatcherName = dispatcherName;
		this.scope = scope;
		this.roleDescription = roleDescription;
	}

	public MailListRoleMember(java.lang.String roleName, java.lang.String dispatcherName)
	{
		super();
		this.roleName = roleName;
		this.dispatcherName = dispatcherName;
	}

	public MailListRoleMember(MailListRoleMember otherBean)
	{
		this(otherBean.roleName, otherBean.dispatcherName, otherBean.scope, otherBean.roleDescription);
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
	 * Gets value for attribute dispatcherName
	 */
	public java.lang.String getDispatcherName() {
		return this.dispatcherName;
	}

	/**
	 * Sets value for attribute dispatcherName
	 */
	public void setDispatcherName(java.lang.String dispatcherName) {
		this.dispatcherName = dispatcherName;
	}

	/**
	 * Gets value for attribute scope
	 */
	public java.lang.String getScope() {
		return this.scope;
	}

	/**
	 * Sets value for attribute scope
	 */
	public void setScope(java.lang.String scope) {
		this.scope = scope;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[roleName: ");
		b.append (this.roleName);
		b.append (", dispatcherName: ");
		b.append (this.dispatcherName);
		b.append (", scope: ");
		b.append (this.scope);
		b.append (", roleDescription: ");
		b.append (this.roleDescription);
		b.append ("]");
		return b.toString();
	}

}
