//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity PamActionEntity
 */

public abstract class PamActionEntity {

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
	 * Attribute policy
	 */
	private com.soffid.iam.pam.model.PamPolicyEntity policy;
	/**
	 * Gets value for attribute policy
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity getPolicy() {
		return this.policy;
	}
	/**
	 * Sets value for attribute policy
	 */
	public void setPolicy(com.soffid.iam.pam.model.PamPolicyEntity policy) {
		this.policy = policy;
	}
	/**
	 * Attribute rule
	 */
	private com.soffid.iam.pam.model.PamRuleEntity rule;
	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.pam.model.PamRuleEntity getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.pam.model.PamRuleEntity rule) {
		this.rule = rule;
	}
	/**
	 * Attribute author
	 */
	private java.lang.String author;
	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}
	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	/**
	 * Attribute date
	 */
	private java.util.Date date;
	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.pam.model.PamActionType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.pam.model.PamActionType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.pam.model.PamActionType type) {
		this.type = type;
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
	 * Returns <code>true</code> if the argument is an PamActionEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PamActionEntity))
		{
			return false;
		}
		final PamActionEntity that = (PamActionEntity)object;
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
