//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity ForbiddenWordEntity
 */

public abstract class ForbiddenWordEntity {

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
	 * Attribute forbiddenWord
	 */
	private java.lang.String forbiddenWord;
	/**
	 * Gets value for attribute forbiddenWord
	 */
	public java.lang.String getForbiddenWord() {
		return this.forbiddenWord;
	}
	/**
	 * Sets value for attribute forbiddenWord
	 */
	public void setForbiddenWord(java.lang.String forbiddenWord) {
		this.forbiddenWord = forbiddenWord;
	}
	/**
	 * Attribute policies
	 */
	private java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> policies =  new java.util.HashSet<com.soffid.iam.am.model.PolicyForbiddenWordEntity>();
	/**
	 * Gets value for attribute policies
	 */
	public java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> getPolicies() {
		return this.policies;
	}
	/**
	 * Sets value for attribute policies
	 */
	public void setPolicies(java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> policies) {
		this.policies = policies;
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
	 * Returns <code>true</code> if the argument is an ForbiddenWordEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ForbiddenWordEntity))
		{
			return false;
		}
		final ForbiddenWordEntity that = (ForbiddenWordEntity)object;
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
