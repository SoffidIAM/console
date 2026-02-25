//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity SoffidLicenseEntity
 */

public abstract class SoffidLicenseEntity {

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
	 * Attribute license
	 */
	private java.lang.String license;
	/**
	 * Gets value for attribute license
	 */
	public java.lang.String getLicense() {
		return this.license;
	}
	/**
	 * Sets value for attribute license
	 */
	public void setLicense(java.lang.String license) {
		this.license = license;
	}
	/**
	 * Returns <code>true</code> if the argument is an SoffidLicenseEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SoffidLicenseEntity))
		{
			return false;
		}
		final SoffidLicenseEntity that = (SoffidLicenseEntity)object;
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
