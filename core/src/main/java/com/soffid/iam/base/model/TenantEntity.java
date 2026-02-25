//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity TenantEntity
 */

public abstract class TenantEntity {

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
	 * Attribute enabled
	 */
	private boolean enabled = true;
	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * Attribute disabledPermissions

	 */
	private java.util.Collection<com.soffid.iam.base.model.TenantDisabledPermissionEntity> disabledPermissions =  new java.util.HashSet<com.soffid.iam.base.model.TenantDisabledPermissionEntity>();
	/**
	 * Gets value for attribute disabledPermissions
	 */
	public java.util.Collection<com.soffid.iam.base.model.TenantDisabledPermissionEntity> getDisabledPermissions() {
		return this.disabledPermissions;
	}
	/**
	 * Sets value for attribute disabledPermissions
	 */
	public void setDisabledPermissions(java.util.Collection<com.soffid.iam.base.model.TenantDisabledPermissionEntity> disabledPermissions) {
		this.disabledPermissions = disabledPermissions;
	}
	/**
	 * Attribute servers

	 */
	private java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> servers =  new java.util.HashSet<com.soffid.iam.base.model.TenantServerEntity>();
	/**
	 * Gets value for attribute servers
	 */
	public java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> getServers() {
		return this.servers;
	}
	/**
	 * Sets value for attribute servers
	 */
	public void setServers(java.util.Collection<com.soffid.iam.base.model.TenantServerEntity> servers) {
		this.servers = servers;
	}
	/**
	 * Returns <code>true</code> if the argument is an TenantEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TenantEntity))
		{
			return false;
		}
		final TenantEntity that = (TenantEntity)object;
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
