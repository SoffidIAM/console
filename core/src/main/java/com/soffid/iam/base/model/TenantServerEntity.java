//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity TenantServerEntity
 */

public abstract class TenantServerEntity {

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
	 * Attribute serverTenant
	 */
	private com.soffid.iam.base.model.TenantEntity serverTenant;
	/**
	 * Gets value for attribute serverTenant
	 */
	public com.soffid.iam.base.model.TenantEntity getServerTenant() {
		return this.serverTenant;
	}
	/**
	 * Sets value for attribute serverTenant
	 */
	public void setServerTenant(com.soffid.iam.base.model.TenantEntity serverTenant) {
		this.serverTenant = serverTenant;
	}
	/**
	 * Attribute tenantServer
	 */
	private com.soffid.iam.sync.model.ServerEntity tenantServer;
	/**
	 * Gets value for attribute tenantServer
	 */
	public com.soffid.iam.sync.model.ServerEntity getTenantServer() {
		return this.tenantServer;
	}
	/**
	 * Sets value for attribute tenantServer
	 */
	public void setTenantServer(com.soffid.iam.sync.model.ServerEntity tenantServer) {
		this.tenantServer = tenantServer;
	}
	/**
	 * Returns <code>true</code> if the argument is an TenantServerEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TenantServerEntity))
		{
			return false;
		}
		final TenantServerEntity that = (TenantServerEntity)object;
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
