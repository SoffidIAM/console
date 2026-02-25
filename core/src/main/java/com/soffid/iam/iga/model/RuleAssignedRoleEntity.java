//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RuleAssignedRoleEntity
 */

public abstract class RuleAssignedRoleEntity {

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
	 * Attribute bshDomainValueExpression
	 */
	private java.lang.String bshDomainValueExpression;
	/**
	 * Gets value for attribute bshDomainValueExpression
	 */
	public java.lang.String getBshDomainValueExpression() {
		return this.bshDomainValueExpression;
	}
	/**
	 * Sets value for attribute bshDomainValueExpression
	 */
	public void setBshDomainValueExpression(java.lang.String bshDomainValueExpression) {
		this.bshDomainValueExpression = bshDomainValueExpression;
	}
	/**
	 * Attribute rule
	 */
	private com.soffid.iam.iga.model.RuleEntity rule;
	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.iga.model.RuleEntity getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.iga.model.RuleEntity rule) {
		this.rule = rule;
	}
	/**
	 * Attribute role
	 */
	private com.soffid.iam.iga.model.RoleEntity role;
	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.model.RoleEntity getRole() {
		return this.role;
	}
	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.model.RoleEntity role) {
		this.role = role;
	}
	/**
	 * Attribute domainValue
	 */
	private java.lang.String domainValue;
	/**
	 * Gets value for attribute domainValue
	 */
	public java.lang.String getDomainValue() {
		return this.domainValue;
	}
	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(java.lang.String domainValue) {
		this.domainValue = domainValue;
	}
	/**
	 * Returns <code>true</code> if the argument is an RuleAssignedRoleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RuleAssignedRoleEntity))
		{
			return false;
		}
		final RuleAssignedRoleEntity that = (RuleAssignedRoleEntity)object;
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
