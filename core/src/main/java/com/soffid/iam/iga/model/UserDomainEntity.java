//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity UserDomainEntity
 */

public abstract class UserDomainEntity {

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
	 * Attribute type
	 */
	private com.soffid.iam.iga.api.UserDomainType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.iga.api.UserDomainType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.iga.api.UserDomainType type) {
		this.type = type;
	}
	/**
	 * Attribute systems
	 */
	private java.util.Collection<com.soffid.iam.iga.model.SystemEntity> systems =  new java.util.HashSet<com.soffid.iam.iga.model.SystemEntity>();
	/**
	 * Gets value for attribute systems
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> getSystems() {
		return this.systems;
	}
	/**
	 * Sets value for attribute systems
	 */
	public void setSystems(java.util.Collection<com.soffid.iam.iga.model.SystemEntity> systems) {
		this.systems = systems;
	}
	/**
	 * Attribute bshExpr
	 */
	private java.lang.String bshExpr;
	/**
	 * Gets value for attribute bshExpr
	 */
	public java.lang.String getBshExpr() {
		return this.bshExpr;
	}
	/**
	 * Sets value for attribute bshExpr
	 */
	public void setBshExpr(java.lang.String bshExpr) {
		this.bshExpr = bshExpr;
	}
	/**
	 * Attribute bshExprCreate
	 */
	private java.lang.String bshExprCreate;
	/**
	 * Gets value for attribute bshExprCreate
	 */
	public java.lang.String getBshExprCreate() {
		return this.bshExprCreate;
	}
	/**
	 * Sets value for attribute bshExprCreate
	 */
	public void setBshExprCreate(java.lang.String bshExprCreate) {
		this.bshExprCreate = bshExprCreate;
	}
	/**
	 * Attribute beanGenerator
	 */
	private java.lang.String beanGenerator;
	/**
	 * Gets value for attribute beanGenerator
	 */
	public java.lang.String getBeanGenerator() {
		return this.beanGenerator;
	}
	/**
	 * Sets value for attribute beanGenerator
	 */
	public void setBeanGenerator(java.lang.String beanGenerator) {
		this.beanGenerator = beanGenerator;
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
	 * Returns <code>true</code> if the argument is an UserDomainEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserDomainEntity))
		{
			return false;
		}
		final UserDomainEntity that = (UserDomainEntity)object;
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
