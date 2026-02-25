//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ApplicationDomainEntity
 */

public abstract class ApplicationDomainEntity {

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
	 * Attribute values
	 */
	private java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> values =  new java.util.HashSet<com.soffid.iam.iga.model.DomainValueEntity>();
	/**
	 * Gets value for attribute values
	 */
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> getValues() {
		return this.values;
	}
	/**
	 * Sets value for attribute values
	 */
	public void setValues(java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> values) {
		this.values = values;
	}
	/**
	 * Attribute roles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleEntity> roles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.iga.model.RoleEntity> roles) {
		this.roles = roles;
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
	 * Attribute informationSystem
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity informationSystem;
	/**
	 * Gets value for attribute informationSystem
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getInformationSystem() {
		return this.informationSystem;
	}
	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity informationSystem) {
		this.informationSystem = informationSystem;
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
	 * Returns <code>true</code> if the argument is an ApplicationDomainEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ApplicationDomainEntity))
		{
			return false;
		}
		final ApplicationDomainEntity that = (ApplicationDomainEntity)object;
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
