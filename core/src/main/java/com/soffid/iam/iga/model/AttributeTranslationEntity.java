//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity AttributeTranslationEntity
 */

public abstract class AttributeTranslationEntity {

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
	 * Attribute domain
	 */
	private java.lang.String domain;
	/**
	 * Gets value for attribute domain
	 */
	public java.lang.String getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}
	/**
	 * Attribute column1
	 */
	private java.lang.String column1;
	/**
	 * Gets value for attribute column1
	 */
	public java.lang.String getColumn1() {
		return this.column1;
	}
	/**
	 * Sets value for attribute column1
	 */
	public void setColumn1(java.lang.String column1) {
		this.column1 = column1;
	}
	/**
	 * Attribute column2
	 */
	private java.lang.String column2;
	/**
	 * Gets value for attribute column2
	 */
	public java.lang.String getColumn2() {
		return this.column2;
	}
	/**
	 * Sets value for attribute column2
	 */
	public void setColumn2(java.lang.String column2) {
		this.column2 = column2;
	}
	/**
	 * Attribute column3
	 */
	private java.lang.String column3;
	/**
	 * Gets value for attribute column3
	 */
	public java.lang.String getColumn3() {
		return this.column3;
	}
	/**
	 * Sets value for attribute column3
	 */
	public void setColumn3(java.lang.String column3) {
		this.column3 = column3;
	}
	/**
	 * Attribute column4
	 */
	private java.lang.String column4;
	/**
	 * Gets value for attribute column4
	 */
	public java.lang.String getColumn4() {
		return this.column4;
	}
	/**
	 * Sets value for attribute column4
	 */
	public void setColumn4(java.lang.String column4) {
		this.column4 = column4;
	}
	/**
	 * Attribute column5
	 */
	private java.lang.String column5;
	/**
	 * Gets value for attribute column5
	 */
	public java.lang.String getColumn5() {
		return this.column5;
	}
	/**
	 * Sets value for attribute column5
	 */
	public void setColumn5(java.lang.String column5) {
		this.column5 = column5;
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
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}
	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}
	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}
	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	/**
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}
	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * Returns <code>true</code> if the argument is an AttributeTranslationEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AttributeTranslationEntity))
		{
			return false;
		}
		final AttributeTranslationEntity that = (AttributeTranslationEntity)object;
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
