//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity NetworkAuthorizationEntity
 */

public abstract class NetworkAuthorizationEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
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
	 * Attribute level
	 */
	private java.lang.Integer level;
	/**
	 * Gets value for attribute level
	 */
	public java.lang.Integer getLevel() {
		return this.level;
	}
	/**
	 * Sets value for attribute level
	 */
	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}
	/**
	 * Attribute hostsName
	 */
	private java.lang.String hostsName;
	/**
	 * Gets value for attribute hostsName
	 */
	public java.lang.String getHostsName() {
		return this.hostsName;
	}
	/**
	 * Sets value for attribute hostsName
	 */
	public void setHostsName(java.lang.String hostsName) {
		this.hostsName = hostsName;
	}
	/**
	 * Attribute network
	 */
	private com.soffid.iam.am.model.NetworkEntity network;
	/**
	 * Gets value for attribute network
	 */
	public com.soffid.iam.am.model.NetworkEntity getNetwork() {
		return this.network;
	}
	/**
	 * Sets value for attribute network
	 */
	public void setNetwork(com.soffid.iam.am.model.NetworkEntity network) {
		this.network = network;
	}
	/**
	 * Attribute role
	 */
	private com.soffid.iam.iga.model.RoleEntity role;
	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.model.RoleEntity getRole() {
		return this.role;
	}
	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.model.RoleEntity role) {
		this.role = role;
	}
	/**
	 * Attribute group
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
	}
	/**
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Returns <code>true</code> if the argument is an NetworkAuthorizationEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof NetworkAuthorizationEntity))
		{
			return false;
		}
		final NetworkAuthorizationEntity that = (NetworkAuthorizationEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
