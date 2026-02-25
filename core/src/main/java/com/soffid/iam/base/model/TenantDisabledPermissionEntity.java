//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity TenantDisabledPermissionEntity
 */

public abstract class TenantDisabledPermissionEntity {

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
	 * Attribute appliesTo
	 */
	private com.soffid.iam.base.model.TenantEntity appliesTo;
	/**
	 * Gets value for attribute appliesTo
	 */
	public com.soffid.iam.base.model.TenantEntity getAppliesTo() {
		return this.appliesTo;
	}
	/**
	 * Sets value for attribute appliesTo
	 */
	public void setAppliesTo(com.soffid.iam.base.model.TenantEntity appliesTo) {
		this.appliesTo = appliesTo;
	}
	/**
	 * Attribute permission
	 */
	private java.lang.String permission;
	/**
	 * Gets value for attribute permission
	 */
	public java.lang.String getPermission() {
		return this.permission;
	}
	/**
	 * Sets value for attribute permission
	 */
	public void setPermission(java.lang.String permission) {
		this.permission = permission;
	}
	/**
	 * Returns <code>true</code> if the argument is an TenantDisabledPermissionEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TenantDisabledPermissionEntity))
		{
			return false;
		}
		final TenantDisabledPermissionEntity that = (TenantDisabledPermissionEntity)object;
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
