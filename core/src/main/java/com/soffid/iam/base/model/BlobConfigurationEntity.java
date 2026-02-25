//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity BlobConfigurationEntity
 */

public abstract class BlobConfigurationEntity {

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
	 * Attribute value
	 */
	private java.sql.Blob value;
	/**
	 * Gets value for attribute value
	 */
	public java.sql.Blob getValue() {
		return this.value;
	}
	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.sql.Blob value) {
		this.value = value;
	}
	/**
	 * Attribute version
	 */
	private java.lang.String version;
	/**
	 * Gets value for attribute version
	 */
	public java.lang.String getVersion() {
		return this.version;
	}
	/**
	 * Sets value for attribute version
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
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
	 * Returns <code>true</code> if the argument is an BlobConfigurationEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof BlobConfigurationEntity))
		{
			return false;
		}
		final BlobConfigurationEntity that = (BlobConfigurationEntity)object;
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
