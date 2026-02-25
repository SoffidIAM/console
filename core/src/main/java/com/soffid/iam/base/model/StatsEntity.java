//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity StatsEntity
 */

public abstract class StatsEntity {

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
	 * Attribute date
	 * Date format is YYYYmmDDHHMM
	 */
	private java.lang.String date;
	/**
	 * Gets value for attribute date
	 */
	public java.lang.String getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.lang.String date) {
		this.date = date;
	}
	/**
	 * Attribute serie
	 */
	private java.lang.String serie;
	/**
	 * Gets value for attribute serie
	 */
	public java.lang.String getSerie() {
		return this.serie;
	}
	/**
	 * Sets value for attribute serie
	 */
	public void setSerie(java.lang.String serie) {
		this.serie = serie;
	}
	/**
	 * Attribute value
	 */
	private java.lang.Long value = 0L;
	/**
	 * Gets value for attribute value
	 */
	public java.lang.Long getValue() {
		return this.value;
	}
	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.Long value) {
		this.value = value;
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
	 * Returns <code>true</code> if the argument is an StatsEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof StatsEntity))
		{
			return false;
		}
		final StatsEntity that = (StatsEntity)object;
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
