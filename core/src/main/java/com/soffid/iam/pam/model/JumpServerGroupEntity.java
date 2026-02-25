//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity JumpServerGroupEntity
 */

public abstract class JumpServerGroupEntity {

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
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * Attribute description
	 */
	private java.lang.String description;
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
	 * Attribute storeUrl
	 */
	private java.lang.String storeUrl;
	/**
	 * Gets value for attribute storeUrl
	 */
	public java.lang.String getStoreUrl() {
		return this.storeUrl;
	}
	/**
	 * Sets value for attribute storeUrl
	 */
	public void setStoreUrl(java.lang.String storeUrl) {
		this.storeUrl = storeUrl;
	}
	/**
	 * Attribute storeUserName
	 */
	private java.lang.String storeUserName;
	/**
	 * Gets value for attribute storeUserName
	 */
	public java.lang.String getStoreUserName() {
		return this.storeUserName;
	}
	/**
	 * Sets value for attribute storeUserName
	 */
	public void setStoreUserName(java.lang.String storeUserName) {
		this.storeUserName = storeUserName;
	}
	/**
	 * Attribute password
	 */
	private java.lang.String password;
	/**
	 * Gets value for attribute password
	 */
	public java.lang.String getPassword() {
		return this.password;
	}
	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	/**
	 * Attribute jumpServers

	 */
	private java.util.Collection<com.soffid.iam.pam.model.JumpServerEntity> jumpServers =  new java.util.HashSet<com.soffid.iam.pam.model.JumpServerEntity>();
	/**
	 * Gets value for attribute jumpServers
	 */
	public java.util.Collection<com.soffid.iam.pam.model.JumpServerEntity> getJumpServers() {
		return this.jumpServers;
	}
	/**
	 * Sets value for attribute jumpServers
	 */
	public void setJumpServers(java.util.Collection<com.soffid.iam.pam.model.JumpServerEntity> jumpServers) {
		this.jumpServers = jumpServers;
	}
	/**
	 * Returns <code>true</code> if the argument is an JumpServerGroupEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof JumpServerGroupEntity))
		{
			return false;
		}
		final JumpServerGroupEntity that = (JumpServerGroupEntity)object;
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
