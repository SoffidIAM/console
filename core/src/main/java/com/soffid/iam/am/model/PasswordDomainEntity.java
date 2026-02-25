//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity PasswordDomainEntity
 */

public abstract class PasswordDomainEntity {

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
	 * Attribute passwordPolicies
	 */
	private java.util.Collection<com.soffid.iam.am.model.PasswordPolicyEntity> passwordPolicies =  new java.util.HashSet<com.soffid.iam.am.model.PasswordPolicyEntity>();
	/**
	 * Gets value for attribute passwordPolicies
	 */
	public java.util.Collection<com.soffid.iam.am.model.PasswordPolicyEntity> getPasswordPolicies() {
		return this.passwordPolicies;
	}
	/**
	 * Sets value for attribute passwordPolicies
	 */
	public void setPasswordPolicies(java.util.Collection<com.soffid.iam.am.model.PasswordPolicyEntity> passwordPolicies) {
		this.passwordPolicies = passwordPolicies;
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
	 * Returns <code>true</code> if the argument is an PasswordDomainEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PasswordDomainEntity))
		{
			return false;
		}
		final PasswordDomainEntity that = (PasswordDomainEntity)object;
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
