//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity SoDRuleEntity
 */

public abstract class SoDRuleEntity {

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
	 * Attribute risk
	 */
	private com.soffid.iam.rc.api.SoDRisk risk;
	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}
	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
	}
	/**
	 * Attribute type
	 * Type of SoDRule
	 */
	private com.soffid.iam.rc.api.SodRuleType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.rc.api.SodRuleType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.rc.api.SodRuleType type) {
		this.type = type;
	}
	/**
	 * Attribute number
	 * Number of roles to match to trigger the risk
	 */
	private java.lang.Integer number;
	/**
	 * Gets value for attribute number
	 */
	public java.lang.Integer getNumber() {
		return this.number;
	}
	/**
	 * Sets value for attribute number
	 */
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	/**
	 * Attribute application
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity application;
	/**
	 * Gets value for attribute application
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getApplication() {
		return this.application;
	}
	/**
	 * Sets value for attribute application
	 */
	public void setApplication(com.soffid.iam.iga.model.InformationSystemEntity application) {
		this.application = application;
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
	 * Attribute roles
	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> roles =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRoleEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> roles) {
		this.roles = roles;
	}
	/**
	 * Attribute matrixCells

	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> matrixCells =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRuleMatrixEntity>();
	/**
	 * Gets value for attribute matrixCells
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> getMatrixCells() {
		return this.matrixCells;
	}
	/**
	 * Sets value for attribute matrixCells
	 */
	public void setMatrixCells(java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> matrixCells) {
		this.matrixCells = matrixCells;
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
	 * Returns <code>true</code> if the argument is an SoDRuleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SoDRuleEntity))
		{
			return false;
		}
		final SoDRuleEntity that = (SoDRuleEntity)object;
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
