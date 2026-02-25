//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AccessControlList
 **/
public class AccessControlList

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute users

	 */
	private java.util.Set<java.lang.Long> users = new java.util.HashSet<Long>();

	/**
	 * Attribute roles

	 */
	private java.util.Set<java.lang.Long> roles = new java.util.HashSet<Long>();

	/**
	 * Attribute groups

	 */
	private java.util.Set<java.lang.Long> groups = new java.util.HashSet<Long>();

	public AccessControlList()
	{
	}

	public AccessControlList(java.util.Set<java.lang.Long> users, java.util.Set<java.lang.Long> roles, java.util.Set<java.lang.Long> groups)
	{
		super();
		this.users = users;
		this.roles = roles;
		this.groups = groups;
	}

	public AccessControlList(AccessControlList otherBean)
	{
		this(otherBean.users, otherBean.roles, otherBean.groups);
	}

	/**
	 * Gets value for attribute users
	 */
	public java.util.Set<java.lang.Long> getUsers() {
		return this.users;
	}

	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.Set<java.lang.Long> users) {
		this.users = users;
	}

	/**
	 * Gets value for attribute roles
	 */
	public java.util.Set<java.lang.Long> getRoles() {
		return this.roles;
	}

	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Set<java.lang.Long> roles) {
		this.roles = roles;
	}

	/**
	 * Gets value for attribute groups
	 */
	public java.util.Set<java.lang.Long> getGroups() {
		return this.groups;
	}

	/**
	 * Sets value for attribute groups
	 */
	public void setGroups(java.util.Set<java.lang.Long> groups) {
		this.groups = groups;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[users: ");
		b.append (this.users);
		b.append (", roles: ");
		b.append (this.roles);
		b.append (", groups: ");
		b.append (this.groups);
		b.append ("]");
		return b.toString();
	}

}
