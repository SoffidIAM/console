//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ServerRegistrationTokenEntity
 */

public abstract class ServerRegistrationTokenEntity {

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
	 * Attribute step
	 */
	private int step;
	/**
	 * Gets value for attribute step
	 */
	public int getStep() {
		return this.step;
	}
	/**
	 * Sets value for attribute step
	 */
	public void setStep(int step) {
		this.step = step;
	}
	/**
	 * Attribute token
	 */
	private java.lang.String token;
	/**
	 * Gets value for attribute token
	 */
	public java.lang.String getToken() {
		return this.token;
	}
	/**
	 * Sets value for attribute token
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
	}
	/**
	 * Attribute expiration
	 */
	private java.util.Date expiration;
	/**
	 * Gets value for attribute expiration
	 */
	public java.util.Date getExpiration() {
		return this.expiration;
	}
	/**
	 * Sets value for attribute expiration
	 */
	public void setExpiration(java.util.Date expiration) {
		this.expiration = expiration;
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
	 * Returns <code>true</code> if the argument is an ServerRegistrationTokenEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ServerRegistrationTokenEntity))
		{
			return false;
		}
		final ServerRegistrationTokenEntity that = (ServerRegistrationTokenEntity)object;
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
