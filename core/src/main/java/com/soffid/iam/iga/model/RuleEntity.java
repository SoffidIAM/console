//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RuleEntity
 */

public abstract class RuleEntity {

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
	 * Attribute bshExpression
	 */
	private java.lang.String bshExpression;
	/**
	 * Gets value for attribute bshExpression
	 */
	public java.lang.String getBshExpression() {
		return this.bshExpression;
	}
	/**
	 * Sets value for attribute bshExpression
	 */
	public void setBshExpression(java.lang.String bshExpression) {
		this.bshExpression = bshExpression;
	}
	/**
	 * Attribute bshRoles
	 */
	private java.lang.String bshRoles;
	/**
	 * Gets value for attribute bshRoles
	 */
	public java.lang.String getBshRoles() {
		return this.bshRoles;
	}
	/**
	 * Sets value for attribute bshRoles
	 */
	public void setBshRoles(java.lang.String bshRoles) {
		this.bshRoles = bshRoles;
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
	private java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> roles =  new java.util.HashSet<com.soffid.iam.iga.model.RuleAssignedRoleEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> roles) {
		this.roles = roles;
	}
	/**
	 * Attribute generated
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> generated =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute generated
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getGenerated() {
		return this.generated;
	}
	/**
	 * Sets value for attribute generated
	 */
	public void setGenerated(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> generated) {
		this.generated = generated;
	}
	/**
	 * Returns <code>true</code> if the argument is an RuleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RuleEntity))
		{
			return false;
		}
		final RuleEntity that = (RuleEntity)object;
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
