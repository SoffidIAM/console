//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity SamlRequestEntity
 */

public abstract class SamlRequestEntity {

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
	 * Attribute externalId
	 */
	private java.lang.String externalId;
	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}
	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}
	/**
	 * Attribute date
	 */
	private java.util.Date date;
	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * Attribute expirationDate
	 */
	private java.util.Date expirationDate;
	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * Attribute finished
	 */
	private boolean finished;
	/**
	 * Gets value for attribute finished
	 */
	public boolean isFinished() {
		return this.finished;
	}
	/**
	 * Sets value for attribute finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	/**
	 * Attribute hostName
	 */
	private java.lang.String hostName;
	/**
	 * Gets value for attribute hostName
	 */
	public java.lang.String getHostName() {
		return this.hostName;
	}
	/**
	 * Sets value for attribute hostName
	 */
	public void setHostName(java.lang.String hostName) {
		this.hostName = hostName;
	}
	/**
	 * Attribute user
	 */
	private java.lang.String user;
	/**
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}
	/**
	 * Attribute key
	 */
	private java.lang.String key;
	/**
	 * Gets value for attribute key
	 */
	public java.lang.String getKey() {
		return this.key;
	}
	/**
	 * Sets value for attribute key
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
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
	 * Returns <code>true</code> if the argument is an SamlRequestEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SamlRequestEntity))
		{
			return false;
		}
		final SamlRequestEntity that = (SamlRequestEntity)object;
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
